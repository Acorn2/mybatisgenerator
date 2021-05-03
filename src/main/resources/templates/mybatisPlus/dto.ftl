package ${package}.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ${pascalName}DTO {

<#list columns as column>
	@ApiModelProperty("${column.comment}")
	private ${column.javaType} ${column.camelName};

</#list>
}
