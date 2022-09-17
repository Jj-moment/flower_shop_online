package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
@Getter
@Setter
@ApiModel(value = "Menu对象", description = "菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父菜单id，默认为0表示无父菜单")
    private Long parentId;

    @ApiModelProperty("组件地址")
    private String component;

    @ApiModelProperty("访问权限")
    private String perms;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("是否显示，0表示显示，1表示不显示")
    private Integer visible;

    @ApiModelProperty("菜单状态")
    private Integer status;

    @ApiModelProperty("显示顺序")
    private Integer orderNum;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("菜单备注")
    private String remark;

    @TableField(exist = false)
    @ApiModelProperty("子菜单")
    List<Menu> children;
}
