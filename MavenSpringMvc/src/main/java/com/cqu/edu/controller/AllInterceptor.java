/**
 * @description 拦截器的第二种实现---WebRequestInterceptor 接口
 * 				与第一种方式实现不同的是，这里的三个返回值类型都是void。即我们不能通过此拦截器进行请求拦截。
 * 				因此，我们推荐使用功能较为完整的第一种方式(按照自身的需求来选择即可)
 */
package com.cqu.edu.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class AllInterceptor implements WebRequestInterceptor {

/**
 * WebRequest：WebRequest 是Spring 定义的一个接口，它里面的方法定义都基本跟HttpServletRequest 一样，
 * 在WebRequestInterceptor 中对WebRequest 进行的所有操作都将同步到HttpServletRequest 中，
 * 然后在当前请求中一直传递。
 */
	/**  
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中  
     */  
	public void preHandle(WebRequest request) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------AllInterceptor：2---------");
		System.out.println("preHandle2---------");
		
		//这个是放到request范围内的，所以只能在当前请求中的request中获取到
		request.setAttribute("requestAttr", "requestPreAttr2", WebRequest.SCOPE_REQUEST);
		//这个是放到session范围内的，如果环境允许的话它只能在局部的隔离的会话中访问，否则就是在普通的当前会话中可以访问
		request.setAttribute("sessionAttr", "sessionPreAttr2", WebRequest.SCOPE_SESSION);
		//如果环境允许的话，它能在全局共享的会话中访问，否则就是在普通的当前会话中访问
		request.setAttribute("globalSessionAttr", "globalSessionPreAttr2", WebRequest.SCOPE_GLOBAL_SESSION);
	}
	
	 /**  
      * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象,
      * 所以可以在这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。(会出现空指针异常，原因不清)  
      */
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle2---------");
		model.addAttribute("postAttr2", "postAttr2");
	}

	/**  
     * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放  
     */   
	public void afterCompletion(WebRequest request, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion2---------");
		System.out.println("exception: " + exception);
	}

}
