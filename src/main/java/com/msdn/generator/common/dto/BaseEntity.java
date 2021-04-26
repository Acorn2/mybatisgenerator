package com.msdn.generator.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hresh
 * @date 2021/4/26 22:24
 * @description
 */
@Data
@ApiModel("核心基础实体类")
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "删除标记")
    @TableField("del_flag")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建人代码")
    @TableField("create_user_code")
    private String createUserCode;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("create_user_name")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty(value = "修改人代码")
    @TableField("update_user_code")
    private String updateUserCode;

    @ApiModelProperty(value = "修改人姓名")
    @TableField("update_user_name")
    private String updateUserName;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_date")
    private Date updateDate;
}
