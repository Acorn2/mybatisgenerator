package ${package}.model;

import lombok.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.msdn.generator.common.model.CoreBase;

import java.math.BigDecimal;

/**
*
${tableComment}
*
* @author ${author}
* @since ${date}
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("${tableName}")
@ApiModel(value="${tableName}对象", description="${tableComment}")
public class ${pascalName} extends CoreBase{

    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as column>
        <#if column.isPrimaryKey>
    @TableId(value = "${column.fieldName}", type = IdType.${column.primaryKeyType})
        <#else>
            <#if !column.isCommonField>
    @TableField("${column.fieldName}")
            </#if>
        </#if>
        <#if !column.isCommonField>
    @ApiModelProperty(value = "${column.comment}")
    private ${column.javaType} ${column.camelName};

        </#if>
</#list>
}
