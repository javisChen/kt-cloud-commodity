package com.kt.cloud.commodity.module.commodity.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kt.cloud.commodity.dao.entity.SkuAttrDO;
import com.kt.cloud.commodity.dao.entity.SkuDO;
import com.kt.cloud.commodity.dao.mapper.SkuMapper;
import com.kt.cloud.commodity.module.commodity.dto.request.AttrReqDTO;
import com.kt.cloud.commodity.module.commodity.dto.request.CommodityUpdateReqDTO;
import com.kt.cloud.commodity.module.commodity.dto.request.SkuUpdateReqDTO;
import com.kt.cloud.commodity.module.commodity.support.CommodityHelper;
import com.kt.component.orm.mybatis.base.BaseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * sku 服务实现类
 * </p>
 *
 * @author EOP
 * @since 2022-03-05
 */
@Service
public class SkuService extends ServiceImpl<SkuMapper, SkuDO> implements IService<SkuDO> {

    private final SkuAttrService skuAttrService;

    public SkuService(SkuAttrService skuAttrService) {
        this.skuAttrService = skuAttrService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSku(Long spuId, CommodityUpdateReqDTO reqDTO) {
        if (CommodityHelper.isUpdateAction(reqDTO) && reqDTO.getFlushSku()) {
            // todo 把当前的SKU记录到历史表里面

            removeSku(spuId);
            removeSkuAttrs(spuId);
        }
        List<SkuUpdateReqDTO> skuList = reqDTO.getSkuList();
        for (SkuUpdateReqDTO skuUpdateReqDTO : skuList) {
            SkuDO skuDO = assembleSkuDTO(spuId, skuUpdateReqDTO);
            if (CommodityHelper.isUpdateAction(reqDTO) && !reqDTO.getFlushSku()) {
                updateById(skuDO);
            } else {
                save(skuDO);
            }
            saveSkuAttrs(skuDO.getId(), skuUpdateReqDTO.getSpecList());
        }
    }

    private void removeSku(Long spuId) {
        remove(new LambdaUpdateWrapper<SkuDO>().eq(SkuDO::getSpuId, spuId));
    }

    private void removeSkuAttrs(Long spuId) {
        List<SkuDO> skuDOList = listBySpuId(spuId);
        List<Long> skuIds = skuDOList.stream().map(BaseEntity::getId).collect(Collectors.toList());
        skuAttrService.removeByIds(skuIds);
    }

    private void saveSkuAttrs(Long skuId, List<AttrReqDTO> skuAttrList) {
        List<SkuAttrDO> doList = skuAttrList.stream().map(item -> {
            SkuAttrDO skuAttrDO = new SkuAttrDO();
            skuAttrDO.setSkuId(skuId);
            skuAttrDO.setAttrValue(item.getAttrValue());
            return skuAttrDO;
        }).collect(Collectors.toList());
        skuAttrService.saveBatch(doList);
    }

    private SkuDO assembleSkuDTO(Long spuId, SkuUpdateReqDTO entity) {
        SkuDO skuDO = new SkuDO();
        if (entity.getId() != null && entity.getId() > 0) {
            skuDO.setId(entity.getId());
        }
        skuDO.setSpuId(spuId);
        skuDO.setCode(entity.getCode());
        skuDO.setSalesPrice(entity.getSalesPrice());
        skuDO.setCostPrice(entity.getCostPrice());
        skuDO.setStock(entity.getStock());
        skuDO.setWarnStock(entity.getWarnStock());
        skuDO.setSpecData(JSONObject.toJSONString(entity.getSpecList()));
        return skuDO;
    }

    public List<SkuDO> listBySpuId(Long spuId) {
        return lambdaQuery()
                .eq(SkuDO::getSpuId, spuId)
                .list();
    }
}
