package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.entities.Goods;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chahongjing on 2017/1/2.
 */
public class SolrHelperTest extends BaseTestCase {
    private SolrHelper solrHelper = new SolrHelper();

    //region solr测试
    @Test
    public void add() throws Exception {
        Goods good = new Goods();
        good.setId("1");
        good.setName("计算机科学");
        good.setWeight(23.2f);
        good.setPrice(30.4f);
        List<String> title = new ArrayList<String>() {{
            add("语言");
            add("IT");
        }};
        good.setTitle(title);
        solrHelper.add(good);
    }

    @Test
    public void addList() throws Exception {
        List<Goods> list = new ArrayList<>();
        Goods good1 = new Goods();
        good1.setId("2");
        good1.setName("C++");
        good1.setWeight(34.2f);
        good1.setPrice(33.4f);
        list.add(good1);

        Goods good2 = new Goods();
        good2.setId("3");
        good2.setName("Java");
        good2.setWeight(62.4f);
        good2.setPrice(43.6f);
        list.add(good2);

        Goods good3 = new Goods();
        good3.setId("4");
        good3.setName("C#");
        good3.setWeight(66.2f);
        good3.setPrice(43.4f);
        list.add(good3);
        solrHelper.addList(list);
    }

    @Test
    public void delete() throws Exception {
//        solrHelper.delete(1);
        solrHelper.delete(2);
//        solrHelper.delete(3);
//        solrHelper.delete(4);
    }

    @Test
    public void find() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "3");
        List<Goods> list = solrHelper.find(map);
        logger.info("solor测试: {}", JSON.toJSONString(list));
    }
    //endregion
}