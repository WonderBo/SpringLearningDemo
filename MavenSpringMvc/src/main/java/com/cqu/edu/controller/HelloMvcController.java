package com.cqu.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//	告诉DispatcherServlet相关的容器， 这是一个Controller， 管理好这个bean
@Controller
//	类级别的RequestMapping，告诉DispatcherServlet由这个类负责处理以根URL。
//	HandlerMapping依靠这个标签来工作
@RequestMapping(value="/hello", method={RequestMethod.GET,RequestMethod.POST})
public class HelloMvcController {
	
//	方法级别的RequestMapping， 限制并缩小了URL路径匹配，同类级别的标签协同工作，最终确定拦截到的URL由那个方法处理
	//host:8080/hello/mvc
	@RequestMapping(value="/mvc")
	public String hello(){
//	视图渲染，/WEB-INF/page/hello.jsp
		System.out.println("---------controller方法被调用---------");
		return "hello";	
	}
}
