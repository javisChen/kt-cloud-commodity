package com.kt.cloud.commodity.module.category.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author EOP
 * @since 2022-03-03
 */
@Data
@ApiModel(value = "树对象", description = "树型对象")
public class TreeDTO<T> implements Serializable {

    @ApiModelProperty(value = "分类id", required = true)
    private List<Node<T>> nodes;

    @Data
    public static class Node<T> {
        private Long id;
        private String name;
        private Long pid;
        private List<Node<T>> nodes;
    }
}
