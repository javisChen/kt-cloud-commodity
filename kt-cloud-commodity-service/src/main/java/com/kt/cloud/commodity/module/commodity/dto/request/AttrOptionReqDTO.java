package com.kt.cloud.commodity.module.commodity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 属性选项值
 * @author jc
 */
@Data
public class AttrOptionReqDTO {

    @ApiModelProperty(value = "属性Id", required = false)
    private Long attrId;
    @ApiModelProperty(value = "属性项值", required = false)
    private List<String> valueList;
}
