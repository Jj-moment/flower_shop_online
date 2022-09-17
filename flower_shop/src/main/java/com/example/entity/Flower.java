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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 鲜花
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
@Getter
@Setter
@ApiModel(value = "Flower对象", description = "鲜花")
public class Flower implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("鲜花id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("鲜花名称")
    private String flowerName;

    @ApiModelProperty("鲜花类型")
    private String type;

    @ApiModelProperty("花语")
    private String wish;

    @ApiModelProperty("鲜花销售价格")
    private BigDecimal price;

    @ApiModelProperty("鲜花市场价格")
    private BigDecimal marketPrice;

    @ApiModelProperty("鲜花库存数量")
    private Long stock;

    @ApiModelProperty("单个商品总花数")
    private Integer amount;

    @ApiModelProperty("材料")
    private String material;

    @ApiModelProperty("包装描述")
    private String packaging;

    @ApiModelProperty("鲜花描述")
    private String flowerDesc;

    @ApiModelProperty("鲜花图片")
    private String img;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
