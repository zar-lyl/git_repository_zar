package com.usian.feign;

import com.usian.pojo.*;
import com.usian.utils.CatResult;
import com.usian.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;

@FeignClient("usian-item-service")
public interface ItemServiceFeign {
    @RequestMapping("service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

    @GetMapping("service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam Integer page, @RequestParam Integer rows);

    //添加商品
    @GetMapping("service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem, @RequestParam String desc, @RequestParam String itemParams);

    //查类目
    @RequestMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);

    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    //回显
    @RequestMapping("/service/item/preUpdateItem")
    Map<String, Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    //修改
    @RequestMapping("/service/item/updateTbItem")
    Integer updateTbItem(@RequestBody TbItem tbItem, @RequestParam String desc, @RequestParam String itemParams);

    //删除
    @RequestMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestParam Long itemId);

    @RequestMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll();

    @RequestMapping("/service/itemParam/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    @RequestMapping("/service/itemParam/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam Long id);

    @RequestMapping("service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    @RequestMapping("service/item/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam Long itemId);

    @RequestMapping("service/item/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId);
}
