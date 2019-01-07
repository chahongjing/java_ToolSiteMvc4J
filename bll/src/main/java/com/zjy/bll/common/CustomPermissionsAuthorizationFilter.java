package com.zjy.bll.common;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/7.
 */
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    /**
     * 权限CustomPermissionsAuthorizationFilter
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
//        BaseResult baseResult = new BaseResult(ResultStatus.UNAUTHORIZED);
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        httpServletResponse.getWriter().write(JSONObject.toJSONString(baseResult));
        return super.onAccessDenied(request, response);
    }
}
