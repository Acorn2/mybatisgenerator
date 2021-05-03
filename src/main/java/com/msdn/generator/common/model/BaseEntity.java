package com.msdn.generator.common.model;

import com.msdn.generator.common.annotation.CreatedName;
import com.msdn.generator.common.annotation.LastModifiedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import tk.mybatis.mapper.annotation.LogicDelete;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
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

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "删除标记")
    @LogicDelete
    @Column(name = "del_flag")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建人代码")
    @CreatedBy
    @Column(name = "create_user_code")
    private String createUserCode;

    @ApiModelProperty(value = "创建人姓名")
    @CreatedName
    @Column(name = "create_user_name")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "修改人代码")
    @LastModifiedBy
    @Column(name = "update_user_code")
    private String updateUserCode;

    @ApiModelProperty(value = "修改人姓名")
    @LastModifiedName
    @Column(name = "update_user_name")
    private String updateUserName;

    @ApiModelProperty(value = "修改时间")
    @LastModifiedDate
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "版本号")
    @Version
    @Column(name = "version")
    private Integer version;
}
