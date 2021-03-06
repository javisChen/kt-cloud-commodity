package com.kt.cloud.commodity.module.attr.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.kt.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author EOP
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AttrPageQueryReqDTO对象", description = "商品属性")
public class AttrPageQueryReqDTO extends PagingQuery {

    @ApiModelProperty(value = "属性ID")
    private Long id;

    @ApiModelProperty(value = "属性名称")
    private String name;

    @ApiModelProperty(value = "属性录入方式，enums[INPUT,手工录入,1;SELECT,从选项列表选取,2]")
    private Integer inputType;

    @ApiModelProperty(value = "属性的类型，enums[SPEC,规格,1;PARAM,参数,2]")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "模板ID")
    private Long attrTemplateId;

    @ApiModelProperty(value = "是否支持手动新增，enums[NO,不支持,0;YES,支持,1]")
    private Integer canManualAdd;

    @ApiModelProperty(value = "商品类别ID")
    private Long categoryId;

    @ApiModelProperty(value = "是否查询属性可选项列表，false=否 true=是")
    private Boolean withOptions = false;

    @ApiModelProperty(value = "商品ID")
    private Long spuId;

}
