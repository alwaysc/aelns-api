package com.aelns.interceptor;

import com.aelns.core.utils.security.Signature;
import com.aelns.service.SignatureService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 进行签名验证
 */
@Configuration
public class SignatureInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SignatureService signatureService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Signature annotation = method.getAnnotation(Signature.class);
            if (null != annotation) {
                if (null == signatureService) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    signatureService = (SignatureService) factory.getBean("signatureService");
                }
                signatureService.validSignature(request);
            }
        }
        return true;
    }
}