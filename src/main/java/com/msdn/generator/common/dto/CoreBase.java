package com.msdn.generator.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hresh
 * @date 2021/4/18 11:45
 * @description 表中的公共字段根据设计来更改，这里将公共字段封装到一个基础实体类中
 * 
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
    @TableField(value = "create_user_code", fill = FieldFill.INSERT)
    private String createUserCode;

    @ApiModelProperty(value = "创建人名称")
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createDate;

    @ApiModelProperty(value = "修改人代码")
    @TableField(value = "update_user_code", fill = FieldFill.INSERT_UPDATE)
    private String updateUserCode;

    @ApiModelProperty(value = "修改人名称")
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateDate;

    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(value = "version")
    private String version;
}
