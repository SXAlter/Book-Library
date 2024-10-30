package com.sxalter.library.loginRegister.filter;

import com.sxalter.library.domain.LoginUser;
import com.sxalter.library.domain.User;
import com.sxalter.library.util.JwtUtil;
import com.sxalter.library.util.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

//@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

//    @Autowired
    private RedisUtil redisUtil;

    public JwtAuthenticationTokenFilter(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        log.info("JwtAuthenticationTokenFilter执行！{}",token);
        if (!StringUtils.hasText(token)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new LoginUser(new User(),new ArrayList<String>()),null,null));
            filterChain.doFilter(request,response);
            return;
        }
        String userID;

        try {
            Claims claims = JwtUtil.parseJWT(token);
            userID = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }


        log.info("userID:{}",userID);
        LoginUser loginUser = redisUtil.getCacheObject("LoggedIn:" + userID);
        if(Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        // 设置登录态
        request.setAttribute("userId", userID);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
