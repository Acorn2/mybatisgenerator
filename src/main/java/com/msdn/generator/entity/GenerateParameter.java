package com.msdn.generator.entity;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 解析命令行参数
 */
@Getter
@Setter
@ApiModel("使用帮助")
@Parameters(commandDescription = "使用帮助")
public class GenerateParameter {

    @ApiModelProperty("mysql主机名")
    @Parameter(names = {"--host", "-h"}, description = "mysql主机名")
    private String host;

    @ApiModelProperty("mysql端口")
    @Parameter(names = {"--port", "-P"}, description = "mysql端口")
    private Integer port;

    @ApiModelProperty("mysql用户名")
    @Parameter(names = {"--username", "-u"}, description = "mysql用户名")
    private String username;

    @ApiModelProperty("mysql密码")
    @Parameter(names = {"--password", "-p"}, description = "mysql密码")
    private String password;

    @ApiModelProperty("mysql数据库名")
    @Parameter(names = {"--database", "-d"}, description = "mysql数据库名")
    private String database;

    @ApiModelProperty("mysql数据库表")
    @Parameter(names = {"--table", "-t"}, description = "mysql数据库表")
    private List<String> table;

    @ApiModelProperty("业务模块名")
    @Parameter(names = {"--module", "-m"}, description = "业务模块名")
    private String module;

    @ApiModelProperty("业务分组，目前是base和business")
    @Parameter(names = {"--group", "-g"}, description = "业务分组，目前是base和business")
    private String group;

    @ApiModelProperty("是否按表名分隔目录")
    @Parameter(names = {"--flat"}, description = "是否按表名分隔目录")
    private boolean flat;

    @ApiModelProperty("orm框架选择")
    @Parameter(names = {"--type"}, description = "orm框架选择")
    private String type;

    @ApiModelProperty("查看帮助")
    @Parameter(names = "--help", help = true, description = "查看帮助")
    private boolean help;

    @ApiModelProperty("表名截取起始索引，比如表名叫做t_sale_contract_detail，生成的实体类为ContractDetail，则该字段为7")
    @Parameter(names = {"--tableStartIndex", "-tsi"}, description = "表名截取起始索引")
    private String tableStartIndex;
}
