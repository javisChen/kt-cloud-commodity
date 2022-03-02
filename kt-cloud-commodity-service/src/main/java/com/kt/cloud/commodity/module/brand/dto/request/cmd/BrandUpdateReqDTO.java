package com.kt.cloud.commodity.module.brand.dto.request.cmd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BrandUpdateReqDTO {

    @ApiModelProperty(value = "品牌id", required = true)
    @NotEmpty(message = "品牌id不能为空")
    private Long id;

    @ApiModelProperty(value = "品牌名称", required = false)
    @NotEmpty(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "品牌图片地址", required = false)
    @NotEmpty(message = "品牌图片地址不能为空")
    private String imageUrl;

    @ApiModelProperty(value = "首字母", required = false)
    @NotEmpty(message = "首字母不能为空")
    private String letter;

}
