package com.qatix.base.spring.boot;

/**
 * ThreadLocal类
 *
 * @Author: Logan.Tang
 */
public class ContextLocal {
    /**
     * 记录参数中的language
     */
    private static ThreadLocal<String> currentLanguage = new ThreadLocal<>();

    public ContextLocal() {
    }

    public static String getLanguage() {
        return currentLanguage.get();
    }

    public static void setLanguage(String language) {
        currentLanguage.set(language);
    }
}
