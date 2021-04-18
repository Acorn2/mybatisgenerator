package ${package}.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.msdn.generator.common.dto.PageSortInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ${pascalName}QueryPageDTO {

    @JsonUnwrapped
    private PageSortInfo pageSortInfo;
}
