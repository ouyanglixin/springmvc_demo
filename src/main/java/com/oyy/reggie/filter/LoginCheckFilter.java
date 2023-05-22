package com.oyy.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.oyy.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求url

        String url = request.getRequestURI();

        //配置不需要拦截url 地址数组
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //2.判断是否需要处理
        boolean check = this.check(urls, url);
        if (check) {
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //3.判断登入状态 如果已经登入 直接放行
        if (request.getSession().getAttribute("employee") != null ) {
            //如果不为空说明已经登入
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //4.如果未登入 返回未登入的结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配方法
     * @return
     */
    public boolean check (String[] urls ,String requestUrl) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestUrl);
            if (match) {
                return true;
            }
        }
        return false;
    }

}
