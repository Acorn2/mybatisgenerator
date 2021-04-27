package com.msdn.generator.service;

import cn.hutool.core.date.DateUtil;
import com.msdn.generator.entity.Column;
import com.msdn.generator.entity.Config;
import com.msdn.generator.entity.GenerateParameter;
import com.msdn.generator.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * 获取表字段信息，
 */
@Service
public class GenerateService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateService.class);
    @Autowired
    private FreemarkerService freemarkerService;

    private String[] commonColumns;

    /**
     * @param tableName 数据库表名
     * @param parameter 模块名
     * @param uuid
     * @throws Exception
     */
    public void generate(String tableName, GenerateParameter parameter, String uuid) throws Exception {

        // 各模块包名，比如 com.msdn.sale 或 com.msdn.finance
        String packagePrefix = "com.msdn." + parameter.getModule();
        // 分组
        if (!StringUtils.isEmpty(parameter.getGroup())) {
            packagePrefix = packagePrefix + "." + parameter.getGroup();
        }

        // 根据项目设计的表名获取到表名，比如表名叫做：t_sale_contract_detail
        int index = tableName.indexOf("_", 2);
        // 驼峰命名，首字母小写,比如：contractDetail
        String camelName = StringUtils.underscoreToCamel(tableName.substring(index + 1));

        // 排除指定字段
        this.commonColumns = Config.COMMON_COLUMNS;

        Map<String, Object> dataModel = new HashMap<>();
        //获取表中字段的具体信息，包括字段名，字段类型，备注等
        List<Column> columns = getColumns(tableName, parameter);
        Column primaryColumn = columns.stream().filter(Column::getIsPrimaryKey).findFirst().orElse(null);
        dataModel.put("package", packagePrefix);
        dataModel.put("camelName", camelName);
        // 首字母转大写，作为实体类名称等
        dataModel.put("pascalName", StringUtils.capitalize(camelName));
        dataModel.put("moduleName", parameter.getModule());
        dataModel.put("tableName", tableName);
        // 表描述
        dataModel.put("tableComment", getTableComment(tableName, parameter));
        dataModel.put("columns", columns);
        dataModel.put("primaryColumn", primaryColumn);
        dataModel.put("tempId", uuid);
        dataModel.put("author", Config.Author);
        dataModel.put("date", DateUtil.now());
        dataModel.put("type", parameter.getType());

        logger.info("准备生成模板代码的表名为：" + tableName + ",表描述为：" + dataModel.get("tableComment"));

        // 生成模板代码
        logger.info("**********开始生成Model模板文件**********");
        generateModel(dataModel, parameter);
        logger.info("**********开始生成VO视图模板文件**********");
        generateVO(dataModel, parameter);
        logger.info("**********开始生成DTO模板文件**********");
        generateDTO(dataModel, parameter);
        logger.info("**********开始生成Struct模板文件**********");
        generateStruct(dataModel, parameter);
        logger.info("**********开始生成Mapper模板文件**********");
        generateMapper(dataModel, parameter);
        logger.info("**********开始生成Service模板文件**********");
        generateService(dataModel, parameter);
        logger.info("**********开始生成Controller模板文件**********");
        generateController(dataModel, parameter);
    }

    private String getUrl(GenerateParameter generateParameter) {
        return "jdbc:mysql://" + generateParameter.getHost() + ":" + generateParameter.getPort() + "/" + generateParameter.getDatabase() + "?useSSL=false&characterEncoding=UTF-8";
    }

    // 数据库连接，类似于：DriverManager.getConnection("jdbc:mysql://localhost:3306/test_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","password");
    public Connection getConnection(GenerateParameter generateParameter) throws Exception {
        return DriverManager.getConnection(getUrl(generateParameter), generateParameter.getUsername(), generateParameter.getPassword());
    }

    /**
     * 根据表具体位置，获取表中字段的具体信息，包括字段名，字段类型，备注等
     *
     * @param tableName
     * @param parameter
     * @return
     * @throws Exception
     */
    private List<Column> getColumns(String tableName, GenerateParameter parameter) throws Exception {
        // 数据库连接
        Connection connection = getConnection(parameter);
        // 获取表定义的字段信息
        ResultSet resultSet = connection.createStatement().executeQuery("SHOW FULL COLUMNS FROM " + tableName);
        List<Column> columnList = new ArrayList<>();
        while (resultSet.next()) {
            String fieldName = resultSet.getString("Field");
            Column column = new Column();
            // 判断是否是主键
            column.setIsPrimaryKey("PRI".equals(resultSet.getString("Key")));
            // 获取字段名称
            column.setFieldName(fieldName);

            // Mybatis Plus特定字段从核心类里获取
            if (Objects.nonNull(this.commonColumns) && Arrays.asList(this.commonColumns).contains(fieldName)) {
                column.setIsCommonField(true);
            } else {
                column.setIsCommonField(false);
            }
            // 获取字段类型
            column.setFieldType(resultSet.getString("Type").replaceAll("\\(.*\\)", ""));
            switch (column.getFieldType()) {
                case "json":
                case "longtext":
                case "char":
                case "varchar":
                case "text":
                    column.setJavaType("String");
                    column.setIsNumber(false);
                    break;
                case "date":
                case "datetime":
                    column.setJavaType("Date");
                    column.setIsNumber(false);
                    break;
                case "bit":
                    column.setJavaType("Boolean");
                    column.setIsNumber(false);
                    break;
                case "int":
                case "tinyint":
                    column.setJavaType("Integer");
                    column.setIsNumber(true);
                    break;
                case "bigint":
                    column.setJavaType("Long");
                    column.setIsNumber(true);
                    break;
                case "decimal":
                    column.setJavaType("BigDecimal");
                    column.setIsNumber(true);
                    break;
                default:
                    throw new Exception(tableName + " " + column.getFieldName() + " " + column.getFieldType() + "类型没有解析");
            }
            // 转换字段名称,receipt_sign_name字段改为 receiptSignName
            column.setCamelName(StringUtils.underscoreToCamel(column.getFieldName()));
            // 首字母大写
            column.setPascalName(StringUtils.capitalize(column.getCamelName()));
            // 字段在数据库的注释
            column.setComment(resultSet.getString("Comment"));
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 获取表的描述
     *
     * @param tableName
     * @param parameter
     * @return
     * @throws Exception
     */
    private String getTableComment(String tableName, GenerateParameter parameter) throws Exception {
        Connection connection = getConnection(parameter);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT table_comment FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + parameter.getDatabase()
                + "' AND table_name = '" + tableName + "'");
        String tableComment = "";
        while (resultSet.next()) {
            tableComment = resultSet.getString("table_comment");
        }
        return tableComment;
    }

    /**
     * 生成 controller 模板代码
     *
     * @param dataModel
     * @param generateParameter
     * @throws Exception
     */
    private void generateController(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "controller" + File.separator + dataModel.get("pascalName") + "Controller.java";
        freemarkerService.write("controller", dataModel, path, generateParameter);
    }

    private void generateDTO(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "dto" + File.separator + dataModel.get("pascalName");
        freemarkerService.write("dto", dataModel, path + "DTO.java", generateParameter);
        freemarkerService.write("dto-page", dataModel, path + "QueryPageDTO.java", generateParameter);
    }

    //
    private void generateModel(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "model" + File.separator + dataModel.get("pascalName") + ".java";
        freemarkerService.write("model", dataModel, path, generateParameter);
    }

    private void generateStruct(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "struct" + File.separator + dataModel.get("pascalName") + "Struct.java";
        freemarkerService.write("struct", dataModel, path, generateParameter);
    }

    private void generateMapper(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "mapper" + File.separator + dataModel.get("pascalName") + "Mapper.java";
        freemarkerService.write("mapper", dataModel, path, generateParameter);

        path = "resources" + File.separator + dataModel.get("pascalName") + "Mapper.xml";
        freemarkerService.write("mapper-xml", dataModel, path, generateParameter);
    }

    private void generateService(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "service" + File.separator + dataModel.get("pascalName") + "Service.java";
        freemarkerService.write("service", dataModel, path, generateParameter);

        path = "java" + File.separator + "service" + File.separator + "impl" + File.separator + dataModel.get("pascalName") + "ServiceImpl.java";
        freemarkerService.write("service-impl", dataModel, path, generateParameter);
    }

    private void generateVO(Map<String, Object> dataModel, GenerateParameter generateParameter) throws Exception {
        String path = "java" + File.separator + "vo" + File.separator + dataModel.get("pascalName") + "VO.java";
        freemarkerService.write("vo", dataModel, path, generateParameter);
    }
}
