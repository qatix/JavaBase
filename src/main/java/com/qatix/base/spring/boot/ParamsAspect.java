package com.qatix.base.spring.boot;

//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * ParamsAspect
// *
// * @Author: Logan.Tang
// * @Date: 2018/12/5 9:20 PM
// */
//@Slf4j
//@Aspect
//@Component
//public class ParamsAspect {
//
//
//    @Before("execution(* com.kika.terminal.controller.*.*(..))")
//    public void before(JoinPoint joinPoint) {
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//
//        //保存参数中的language和country
//        Map<String, String[]> map = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : map.entrySet()) {
//            if (entry.getKey().equalsIgnoreCase("language")) {
//                if (entry.getValue().length > 0) {
//                    ContextLocal.setLanguage(entry.getValue()[0]);
//                }
//            }
//        }
//    }
//}


