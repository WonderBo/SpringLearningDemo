/**
 * 
 */
package com.cqu.wb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 汪波
 *
 */
//@RestController: Spring4之后新加的注解，原来返回json需要@ResponseBody配合@Controller
@RestController
public class ParamController {

	// @GetMapping: 为@RequestMapping(method = RequestMethod.GET)的组合注解，同理有@PostMapping等
	@GetMapping("/print/{id}")
	// @PathVariable: 用于接收RESTful风格的URL中的参数到功能处理方法的参数上
	public String print(@PathVariable("id") Integer id) {
		return "ID: " + id;
	}
	
	@RequestMapping(value = "/print2", method = RequestMethod.GET)
	// @RequestParam: 用于将get/post方法的请求参数区数据映射到功能处理方法的参数上
	public String print2(@RequestParam(value = "id", required = false, defaultValue = "888") Integer myId) {
		return "ID: " + myId;
	}
}
