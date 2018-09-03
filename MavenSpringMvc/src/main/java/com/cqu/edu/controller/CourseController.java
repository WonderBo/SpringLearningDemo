package com.cqu.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cqu.edu.model.Course;
import com.cqu.edu.service.CourseService;
/*
 * RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。RequestMapping六个属性如下：
 基本属性：
(1)value：指定请求的实际地址，指定的地址可以是URI Template 模式可以为普通的具体值；含有某变量的一类值；含正则表达式的一类值
(2)method：指定请求的method类型， GET、POST、PUT、DELETE等
 入出内容类型属性：
(3)consumes(消费)：指定所处理请求的提交内容类型（Content-Type），例如application/json, text/html
(4)produces(生产)：指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
 请求过滤条件属性：
(5)params：指定请求中必须包含某些参数值，才能让该方法处理请求
(6)headers：指定请求中必须包含某些指定的header值，才能让该方法处理请求
 * 
 */

// /course/**下所有路径将被此Controller所拦截，为类级别的RequestMapping
@Controller
@RequestMapping("/course")
public class CourseController {
	private static Logger log = LoggerFactory.getLogger(CourseController.class);
	
	private CourseService courseService;

	public CourseService getCourseService() {
		return courseService;
	}

	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

//	本方法将处理/course/one?courseId=1形式的url
//	类级别和方法级别的RequestMapping组成请求url,并且该请求是由get方法传递过来的
/*	@RequestParam：1.常用来处理简单类型的绑定，通过Request.getParameter() 获取的String可直接转换为简单类型的情况；
 * 				   2.用来处理Content-Type 为 application/x-www-form-urlencoded编码的内容；
 * 				   3.注解有两个属性： value、required； value用来指定要传入值的id名称，required用来指示参数是否必须绑定*/
	@RequestMapping(value="/one",method=RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId") Integer courseId, Model model){
		log.info("In viewCourse,courseId={}",courseId);
		Course course = courseService.getCoursebyId(courseId);
		model.addAttribute(course);
		return "viewCourse";
	}
	
//	本方法将处理/course/two/{courseId}形式的url。
/*	@PathVariable：用于@RequestMapping URI template 样式映射，若方法参数名称和需要绑定的uri template中
 * 				     变量名称不一致，需要在@PathVariable("name")指定uri template中的名称*/
	@RequestMapping(value="/two/{courseId}",method=RequestMethod.GET)
	public String viewCourse_2(@PathVariable("courseId") Integer courseId,Map<String,Object> model){
		log.info("In viewCourse_2,courseId={}",courseId);
		Course course = courseService.getCoursebyId(courseId);
		model.put("course", course);
		return "viewCourse";
	}
	
//	本方法将处理/course/three形式的url	(1，2为现代方式，3为传统方式)
	@RequestMapping(value="/three")
	public String viewCourse_3(HttpServletRequest request){
		Integer courseId = Integer.valueOf(request.getParameter("courseId"));
		log.info("In viewCourse_3,courseId={}",courseId);
		Course course = courseService.getCoursebyId(courseId);
		request.setAttribute("course", course);
		return "viewCourse";
	}
	
//	起到连接作用，有请求url通过方法跳转到指定的页面	处理请求url：/course/add?add
	@RequestMapping(value="/add",method=RequestMethod.GET,params="add")
	public String toCreateCoursePage(){
		return "course_admin/addCourse";
	}

//	实现页面参数与业务模型的绑定
/*	@ModelAttribute：用于参数上时，用来通过名称对应，把相应名称的值绑定到注解的参数bean上；要绑定的值来源于：
 * 					 (1)@SessionAttributes 启用的attribute 对象上(@SessionAttributes 只能声明在类上，而不能声明在方法上);
 * 					 (2)@ModelAttribute 用于方法上时指定的model 对象;
 * 					 (3)上述两种情况都没有时，反射实例化一个需要绑定的bean对象(一定要有没有参数的构造函数)，然后把request中按名称对应的方式把值绑定(set)到bean中
 * 					 用于方法上时，通常用来在处理@RequestMapping之前，为请求绑定需要从后台查询的model(即在该Controller的所有方法在调用前，先执行此@ModelAttribute方法)*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveCourse(@ModelAttribute Course course){	// @ModelAttribute为方法参数级别的annotation（此处若没有也能实现绑定参数功能）
		log.info("Info of Course:");
		log.info(ReflectionToStringBuilder.toString(course));	//ReflectionToStringBuilder.toString(对象)：以键值对形式输出对象信息
		
		//在此进行业务操作，比如数据库持久化
		
		return "redirect:two/"+course.getCourseId();			//请求重定向
	}
	
//	连接作用
	@RequestMapping(value="/toUpload",method=RequestMethod.GET)
	public String toUploadFilePage(){
		return "course_admin/uploadFile";
	}
	
//	上传文件功能，方法内参数的转换（页面参数file转换为MultipartFile并实现操作）
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException{	//F3可以查看相关类详细信息
		if(!file.isEmpty()){
			log.info("Process file:{}",file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C:\\Users\\13160\\Desktop\\", System.currentTimeMillis()+file.getOriginalFilename()));
		}
		return "success";
	}
	
//	@ResponseBody方式并不是唯一的将数据模型表现为json数据格式的方式，但可以说是最简洁的方式
/*	@ResponseBody：返回值通常解析为跳转路径，加上@ResponseBody后返回结果不会被解析为跳转路径，
				      而是直接写入HTTP response body中。比如异步获取json数据，加上@ResponseBody注解后，会直接返回json数据 */
	@RequestMapping(value="/{courseId}",method=RequestMethod.GET)
	public @ResponseBody Course getCourseInJson(@PathVariable Integer courseId){
		return courseService.getCoursebyId(courseId);
	}

//	引用ResponseEntity泛型形式来包裹所要表达的对象（们），最后返回该泛型的实例，包括实际的业务模型数据，以及http状态
	@RequestMapping(value="/jsontype/{courseId}",method=RequestMethod.GET)
	public ResponseEntity<Course> getCourseInJson_2(@PathVariable Integer courseId){
		Course course = courseService.getCoursebyId(courseId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
}
