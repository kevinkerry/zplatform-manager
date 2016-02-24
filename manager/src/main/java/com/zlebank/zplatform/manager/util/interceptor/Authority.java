package com.zlebank.zplatform.manager.util.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 用于识别在进行action调用的时候，标注该方法调用是否需要权限控制，需要什么样的权限的注解类。 
 *  
 *  该注解类一般会包括两个属性，
 *  privilege   权限ID，ID为0时用于日志的记录，不会判断当前用户是否有权限
 *  actionName  action名称。 
 *  operContent 操作内容
 *  functId     操作模块ID
 *  @author GUOJIA * 
 * */
//表示在什么级别保存该注解信息
@Retention(RetentionPolicy.RUNTIME)
//表示该注解用于什么地方
@Target(ElementType.METHOD)
public @interface Authority {
	String actionName() default "";    
	String privilege() default "0";
	String operContent() default "";
	String functId() default "";
}
