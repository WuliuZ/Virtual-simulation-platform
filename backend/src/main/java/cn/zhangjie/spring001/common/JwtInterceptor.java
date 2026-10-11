package cn.zhangjie.spring001.common;

import cn.hutool.core.util.StrUtil;
import cn.zhangjie.spring001.exception.CustomException;
import cn.zhangjie.spring001.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import cn.zhangjie.spring001.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * jwt拦截器 - 在请求访问后台时先进行验证token
 *
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CustomException {
        //1. 从请求头中获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        //2.判断token是否存在
        if (StrUtil.isBlank(token)) {
            throw new CustomException("登录失效,请重新登录");
        }
        //3.获取token中的载荷=userid
        String userId;
        User user;
        try {
            //解码 如果传入的token不合法，被篡改
            userId = JWT.decode(token).getAudience().get(0);
            //根据token中的adminId查询数据库中的用户信息
            user = userService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "登录认证token失败,请重新登录";
            log.error(errMsg +",token="+token,e);
            throw new CustomException(errMsg);
        }
        if (user == null) {
            throw new CustomException("用户不存在");
        }
        try {
            //用户密码+签名 验证token
            JWTVerifier build = JWT.require(Algorithm.HMAC512(user.getPassword())).build();
            //验证token
            build.verify(token);
        } catch (JWTVerificationException e) {
            throw new CustomException("token认证失败,请重新登录");
        }
        return true;
    }
}

