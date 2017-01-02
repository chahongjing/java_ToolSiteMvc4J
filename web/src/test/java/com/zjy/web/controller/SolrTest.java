package com.zjy.web.controller;

import com.zjy.bll.common.SolrHelper;
import com.zjy.common.BaseTestCase;
import com.zjy.entities.Goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author chahongjing
 * @create 2017-01-02 11:06
 */
public class SolrTest extends BaseTestCase {
    private SolrHelper solrHelper;

    public void setUp() throws Exception {
        super.setUp();
        solrHelper = new SolrHelper();
    }

    public void testAdd() throws Exception {
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
        //solrHelper.add(good);
    }

    public void testAddList() throws Exception {
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
        //solrHelper.addList(list);
    }

    public void testDelete() throws Exception {
//        solrHelper.delete(1);
        //solrHelper.delete(2);
//        solrHelper.delete(3);
//        solrHelper.delete(4);
    }

    public void testFind() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "3");
        List<Goods> list = solrHelper.find(map);
    }
}
