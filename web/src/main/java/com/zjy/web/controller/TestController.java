package com.zjy.web.controller;

import com.zjy.bll.common.SolrHelper;
import com.zjy.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chahongjing on 2017/1/2.
 */
@Controller
public class TestController {
    //region 自动装配
    private SolrHelper solrHelper = new SolrHelper();
    //endregion

    //region solr测试
    public void solrAdd() throws Exception {
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

    public void solrAddList() throws Exception {
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

    public void solrDelete() throws Exception {
//        solrHelper.delete(1);
        solrHelper.delete(2);
//        solrHelper.delete(3);
//        solrHelper.delete(4);
    }

    public void solrFind() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "3");
        List<Goods> list = solrHelper.find(map);
    }
    //endregion
}
