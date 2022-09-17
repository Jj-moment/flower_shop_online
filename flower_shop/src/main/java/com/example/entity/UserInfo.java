package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Jj
 * @since 2022-08-30 04:52:51
 */
@Getter
@Setter
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "用户信息表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("user_login表的自增ID")
    private Long userId;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("证件类型：1 身份证，2 军官证，3 护照")
    private Integer identityCardType;

    @ApiModelProperty("证件号码")
    private String identityCardNo;

    @ApiModelProperty("手机号")
    private String mobilePhone;

    @ApiModelProperty("邮箱")
    private String userEmail;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("注册时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
