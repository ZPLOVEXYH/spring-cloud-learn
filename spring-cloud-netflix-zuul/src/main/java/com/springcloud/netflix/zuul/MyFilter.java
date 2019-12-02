package com.springcloud.netflix.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，
     * 是否要过滤，本文true，永远过滤。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：过滤器的具体逻辑。可用很复杂，
     * 包括查sql，nosql去判断该请求到底有没有权限访问。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest hsr = requestContext.getRequest();
        String token = hsr.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            requestContext.setResponseStatusCode(401);
            requestContext.setSendZuulResponse(false);

            try {
                requestContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                return null;
            }
        }

        return null;
    }
}
