package com.kt.cloud.commodity.module.commodity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 规格属性DTO
 * @author jc
 */
@Data
public class AttrRespDTO {

    @ApiModelProperty(value = "属性Id", required = false)
    private Long attrId;
    @ApiModelProperty(value = "属性名称", required = false)
    private String attrName;
    @ApiModelProperty(value = "属性值", required = false)
    private String attrValue;
}
