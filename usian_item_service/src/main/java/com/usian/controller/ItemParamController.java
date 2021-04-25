package com.usian.controller;

import com.usian.pojo.TbItemParam;
import com.usian.service.ItemParamService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类 ID 查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    @RequestMapping("selectItemParamAll")
    public PageResult selectItemParamAll() {
        return itemParamService.selectItemParamAll();
    }

    @RequestMapping("insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam) {
        return itemParamService.insertItemParam(tbItemParam);
    }

    @RequestMapping("deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id) {
        return itemParamService.deleteItemParamById(id);
    }

}