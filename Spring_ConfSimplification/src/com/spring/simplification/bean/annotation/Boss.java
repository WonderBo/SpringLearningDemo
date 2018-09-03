/**
 * @description boss类
 */
package com.spring.simplification.bean.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component 有一个可选的入参，用于指定 Bean 的名称.
//一般情况下，Bean 都是 singleton 的，需要注入 Bean 的地方仅需要通过 byType 策略就可以自动注入了，所以大可不必指定 Bean 的名称
//在使用 @Component 注释后，Spring 容器必须启用类扫描机制以启用注释驱动 Bean 定义和注释驱动 Bean 自动注入的策略。Spring 2.5 对 context 命名空间进行了扩展，提供了这一功能
@Scope("prototype")
//从 Spring 容器中获取 boss Bean 时，每次返回的都是新的实例
@Component("boss")
public class Boss {
	//Spring 2.5 引入了 @Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
	//当同时对类成员变量、setter方法进行标注时，取setter方法注入时的bean值
	//Spring 将直接采用 Java 反射机制对 Boss 中的 car 和 office 这两个私有成员变量进行自动注入。所以对成员变量使用@Autowired后，变量对应的 setter 方法（setCar() 和 setOffice()）可从 Boss 中删除，无关紧要
	//@Resource(JSR-250 规范定义的注释)的作用相当于 @Autowired，只不过 @Autowired 按 byType 自动注入，而@Resource 默认按 byName 自动注入,详情见网页
	
	//此处对类成员变量进行标注，完成自动装配
//	@Autowired
	private Car car;
	@Autowired
	@Qualifier("office")
	private Office office;
	
	public Car getCar() {
		return car;
	}
	
	//此处对类方法进行标注，完成入参自动装配（对构造函数进行标注类似）
	//@Autowired 将查找被标注的方法的入参类型的 Bean，并调用方法自动注入这些 Bean。
	@Autowired
	public void setCar(Car car) {
		this.car = car;
	}
	
	public Office getOffice() {
		return office;
	}
	
	@Autowired
	public void setOffice(@Qualifier("office")Office office) {		//office 是 Bean 的名称
		this.office = office;
	}
	
	//JSR-250 为初始化之后/销毁之前方法的指定定义了两个注释类，分别是 @PostConstruct 和 @PreDestroy，
	//这两个注释只能应用于方法上。标注了 @PostConstruct 注释的方法将在类实例化后调用，而标注了 @PreDestroy 的方法将在类销毁之前调用
	//不管是通过实现 InitializingBean/DisposableBean 接口，还是通过 <bean> 元素的init-method/destroy-method 属性进行配置，都只能为 Bean 指定一个初始化/销毁的方法。但是使用 @PostConstruct 和 @PreDestroy 注释却可以指定多个初始化/销毁方法
	@PostConstruct  
    public void postConstruct1(){  
        System.out.println("postConstruct1");  
    }  
  
    @PreDestroy  
    public void preDestroy1(){  
        System.out.println("preDestroy1");   
    } 
	
	@Override
	public String toString(){
		return "car:" + car + "\n" + "office:" + office;
	}
}
