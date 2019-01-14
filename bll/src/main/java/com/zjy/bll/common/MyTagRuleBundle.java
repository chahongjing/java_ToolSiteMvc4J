package com.zjy.bll.common;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Created by Administrator on 2018/5/18.
 */
public class MyTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("jsSection", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("jsSection"), false));
        defaultState.addRule("cssSection", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("cssSection"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        // 不作处理
    }
}