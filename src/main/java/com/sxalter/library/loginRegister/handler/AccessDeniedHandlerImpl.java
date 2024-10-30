package com.sxalter.library.loginRegister.handler;

import com.alibaba.fastjson.JSON;
import com.sxalter.library.domain.ResponseResult;
import com.sxalter.library.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义授权失败异常处理类
 */
@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"用户授权失败，请联系管理员！");

        log.info("AccessDeniedHandler执行！");
        String jsonString = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,jsonString);
    }
}
