package com.usian.controller;

import com.fasterxml.jackson.databind.node.LongNode;
import com.usian.feign.ContentFeign;
import com.usian.pojo.TbContent;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentFeign contentFeign;

    @RequestMapping("selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(Long categoryId) {
        PageResult pageResult = contentFeign.selectTbContentAllByCategoryId(categoryId);
        if (pageResult != null) {
            return Result.ok(pageResult);
        }
        return Result.error("查询失败，请联系管理员。。。");
    }

    /*
    根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent) {
        Integer num = contentFeign.insertTbContent(tbContent);
        if (num != null) {
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids) {
        Integer num = contentFeign.deleteContentByIds(ids);
        if (num != null) {
            return Result.ok();
        }
        return Result.error("修改失败");
    }
}
