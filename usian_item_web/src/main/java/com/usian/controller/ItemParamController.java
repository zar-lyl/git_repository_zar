package com.usian.controller;

import com.usian.feign.ItemServiceFeign;
import com.usian.pojo.TbItemParam;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemServiceFeign itemServiceFeign;

    /**
     * 根据商品分类 ID 查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {

        TbItemParam tbItemParam = itemServiceFeign.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam != null) {
            return Result.ok(tbItemParam);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("selectItemParamAll")
    public Result selectItemParamAll() {
        PageResult page = itemServiceFeign.selectItemParamAll();
        if (page != null) {
            return Result.ok(page);
        }
        return Result.error("展示失败,好好动动脑子。ok？");
    }

    @RequestMapping("insertItemParam")
    public Result insertItemParam(TbItemParam tbItemParam) {
        Integer integer = itemServiceFeign.insertItemParam(tbItemParam);
        if (integer > 0) {
            return Result.ok();
        }
        return Result.error("添加失败：该类目已有规格模板");
    }

    @RequestMapping("deleteItemParamById")
    public Result deleteItemParamById(@RequestParam(defaultValue = "0") Long id) {
        Integer count = itemServiceFeign.deleteItemParamById(id);
        if (count > 0) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}