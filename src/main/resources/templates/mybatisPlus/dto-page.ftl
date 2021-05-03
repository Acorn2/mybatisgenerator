package ${package}.dto;

import lombok.Getter;
import lombok.Setter;
import com.msdn.generator.common.dto.MbpPage;
import ${package}.model.${pascalName};

@Getter
@Setter
public class ${pascalName}QueryPageDTO extends MbpPage<${pascalName}>{

<#--    @JsonUnwrapped-->
<#--    private PageSortInfo pageSortInfo;-->
}
