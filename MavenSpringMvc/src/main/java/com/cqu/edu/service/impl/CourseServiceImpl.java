package com.cqu.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqu.edu.model.Course;
import com.cqu.edu.service.CourseService;

public class CourseServiceImpl implements CourseService {
	private Course course;
	
	
	public Course getCourse() {
		return course;
	}

	@Autowired
	public void setCourse(Course course) {
		this.course = course;
	}


	public Course getCoursebyId(Integer courseId) {
		course.setCourseId(courseId);
		course.setCourseName("Chinese");
		course.setLevel(new Integer(5));
		return course;
	}

}
