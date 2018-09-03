/**
 * 
 */
package com.cqu.wb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 汪波
 *
 */
@RestController	
public class HelloController {
	@Value("${introduction}")
	private String introduction;
	@Value("${farewell}")
	private String farewell;
	
	@RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
	public String hello() {
		return "Hello Spring Boot! " + introduction;
	}
	
	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public String bye() {
		return farewell;
	}
}
