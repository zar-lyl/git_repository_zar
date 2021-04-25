package com.usian.service;

import com.usian.config.RedisClient;
import com.usian.mapper.TbItemCatMapper;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemCatExample;
import com.usian.utils.CatNode;
import com.usian.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategoryService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private RedisClient redisClient;

    @Value("${PROTAL_CATRESULT_KEY}")
    private String portal_catresult_redis_key;

    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
        return list;
    }

    public CatResult selectItemCategoryAll() {
        // 从redis中获取数据， 如果获取不到数据则从数据库中查询
        CatResult redisCatResult = (CatResult)redisClient.get(portal_catresult_redis_key);
        if(redisCatResult != null){
            System.out.println("从redis中获取的数据");
            return redisCatResult;
        }
        System.out.println("从mysql中获取的数据");
        CatResult catResult = new CatResult();
        catResult.setData(this.getItemCatByParentId(0L));
        // 从数据库中获取数据需要放入redis中
        redisClient.set(portal_catresult_redis_key, catResult);
        return catResult;
    }

    private List<TbItemCat> getItemCatByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList<>();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getItemCatByParentId(tbItemCat.getId()));//查询子节点
                resultList.add(catNode);
                count++;
                //只取商品分类中的 18 条数据
                if (count == 18) {
                    break;
                }
            } else {
                resultList.add(tbItemCat.getName());
            }
        }
        return resultList;
    }
}
