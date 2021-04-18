package com.msdn.generator.common.dto;

import com.msdn.generator.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hresh
 * @date 2021/4/17 18:12
 * @description
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PageSortInfo extends SimplePageInfo {

    private String sort;

    public String parseSort() {
        if (sort == null) {
            return null;
        }
        String[] fragments = sort.split("(?=[+\\-]\\w+)");
        StringBuilder sb = new StringBuilder();
        for (String fragment : fragments) {
            char direct = fragment.charAt(0);
            String name = fragment.substring(1);
            name = StringUtils.camelToUnderscore(name);
            sb.append(name).append(direct == '-' ? " DESC," : " ASC,");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @ApiModelProperty(
            hidden = true
    )
    private String ascs;

    @ApiModelProperty(
            hidden = true
    )
    private String descs;
}
