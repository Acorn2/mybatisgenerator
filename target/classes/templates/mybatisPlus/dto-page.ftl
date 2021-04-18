package ${package}.dto;

import lombok.Getter;
import lombok.Setter;
import com.mountslink.components.dto.MbpPage;
import com.msdn.generator.common.dto.PageSortInfo;

@Getter
@Setter
public class ${pascalName}QueryPageDTO extends MbpPage<${pascalName}>{

    @JsonUnwrapped
    private PageSortInfo pageSortInfo;
}
