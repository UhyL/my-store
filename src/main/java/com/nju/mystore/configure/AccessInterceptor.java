package com.nju.mystore.configure;

import com.nju.mystore.enums.RoleEnum;
import com.nju.mystore.exception.BlueWhaleException;
import com.nju.mystore.util.AccessUtil;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessUtil accessUtil = handlerMethod.getMethodAnnotation(AccessUtil.class);
            if (accessUtil == null) {
                accessUtil = handlerMethod.getBeanType().getAnnotation(AccessUtil.class);
            }
            if (accessUtil != null) {
                String userRole = request.getHeader("UserRole");
                if (userRole == null || !Arrays.asList(accessUtil.roles()).contains(RoleEnum.valueOf(userRole))) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    throw BlueWhaleException.accessDenied();
                }
            }
        }
        return true;
    }
}
