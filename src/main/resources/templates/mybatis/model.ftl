package ${package}.model;

import com.msdn.generator.utils.IdUtils;
import com.msdn.generator.common.dto.BaseEntity;
import lombok.*;
import tk.mybatis.mapper.annotation.KeySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "${tableName}")
@ApiModel(value="${tableName}对象", description="${tableComment}")
public class ${pascalName} extends BaseEntity{

    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as column>
<#if column.isPrimaryKey>
    @Id
    @KeySql(genId = IdUtils.class)
</#if>
<#if !column.isCommonField>
    @ApiModelProperty(value = "${column.comment}")
    @Column(name = "${column.fieldName}")
    private ${column.javaType} ${column.camelName};

</#if>
</#list>
}
