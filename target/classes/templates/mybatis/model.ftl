package ${package}.model;

import com.msdn.common.annotation.CreatedName;
import com.msdn.common.annotation.LastModifiedName;
import com.msdn.common.util.IdUtils;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.LogicDelete;
import tk.mybatis.mapper.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

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
public class ${pascalName} implements Serializable{

    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as column>
    @ApiModelProperty(value = "${column.comment}")
<#if column.isPrimaryKey>
    @Id
    @KeySql(genId = IdUtils.class)
</#if>
<#switch column.fieldName>
    <#case "create_user_code">
    @CreatedBy
        <#break>
    <#case "create_user_name">
    @CreatedName
        <#break>
    <#case "create_date">
    @CreatedDate
        <#break>
    <#case "update_user_code">
    @LastModifiedBy
        <#break>
    <#case "update_user_name">
    @LastModifiedName
        <#break>
    <#case "update_date">
    @LastModifiedDate
        <#break>
    <#case "del_flag">
    @LogicDelete
        <#break>
    <#case "version">
    @Version
        <#break>
    <#default>
        <#break>
</#switch>
    @Column(name = "${column.fieldName}")
    private ${column.javaType} ${column.camelName};

</#list>
}
