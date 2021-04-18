package com.msdn.generator.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hresh
 * @date 2021/4/18 11:45
 * @description
 */
@Data
@ApiModel("核心基础实体类")
@NoArgsConstructor
@AllArgsConstructor
public class CoreBase implements Serializable {

    @ApiModelProperty(value = "是否删除 0未删除（默认），1已删除")
    @TableField(value = "is_deleted")
    @TableLogic(delval = "1", value = "0")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建人名称")
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    @ApiModelProperty(value = "注册时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    @ApiModelProperty(value = "修改人名称")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "update_name", fill = FieldFill.INSERT_UPDATE)
    private String updateName;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;

}
