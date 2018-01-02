package com.zjy.bll.common;

import com.zjy.entities.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-25 12:33
 */
public class SolrHelper {
    // solr服务器地址 cores为核心名称
    private String url = "http://127.0.0.1:8080/solr/cores";

    public void add(Goods good) throws IOException, SolrServerException {
        HttpSolrClient solr = new HttpSolrClient.Builder(url).build();
        // SolrInputDocument   solr.add();
        UpdateResponse response = solr.addBean(good);
        solr.commit();
        solr.close();
    }

    public void addList(List<Goods> list) throws IOException, SolrServerException {
        HttpSolrClient solr = new HttpSolrClient.Builder(url).build();
        UpdateResponse response = solr.addBeans(list);
        solr.commit();
        solr.close();
    }

    public void delete(int id) throws Exception {
        SolrClient solr = new HttpSolrClient.Builder(url).build();
        UpdateResponse response = solr.deleteById(String.valueOf(id));
        solr.commit();
        solr.close();
    }

    public List<Goods> find(Map<String, String> map) throws SolrServerException, IOException {
        SolrClient solr = new HttpSolrClient.Builder(url).build();

        SolrQuery solrQuery = new SolrQuery();
        List<String> q = new ArrayList<>();
        if (map.isEmpty()) {
            q.add("*:*");
        } else {
            for (Map.Entry<String, String> item : map.entrySet()) {
                q.add(item.getKey() + ":" + item.getValue());
            }
        }
        solrQuery.set("q", String.join(" AND ", q));
        // 设置fq属性  weight:23.2     q=name:5&fq=weight:[20 TO 30.2] 找名称为5， 过滤范围为重量20~30.2
        // solrQuery.set("fq", "weight:62.41");
        //solrQuery.set("fq", "weight:62.4");

        // 第二种方法设置查询
//        Map<String, String> map = new HashMap<>();
//        map.put("q", "*:*");
//        SolrParams params = new MapSolrParams(map);
//        QueryResponse resp = solr.query(params);

        // 设置分页的相关属性
        solrQuery.setStart(0);
        solrQuery.setRows(5);

        QueryResponse queryResponse = solr.query(solrQuery);
        return queryResponse.getBeans(Goods.class);
    }

    /**
     * 将SolrDocument转换成Bean
     *
     * @param record
     * @param clazz
     * @return
     */
    public static Object toBean(SolrDocument record, Class clazz) {
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Object value = record.get(field.getName());
            try {
                BeanUtils.setProperty(obj, field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 将SolrDocumentList转换成BeanList
     *
     * @param records
     * @param clazz
     * @return
     */
    public static Object toBeanList(SolrDocumentList records, Class clazz) {
        List list = new ArrayList();
        for (SolrDocument record : records) {
            list.add(toBean(record, clazz));
        }
        return list;
    }
}
