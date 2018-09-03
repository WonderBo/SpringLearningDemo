/**
 * @description 对Car类根据构造函数进行实例化
 */
package com.spring.reflection.oper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.spring.reflection.bean.Car;

public class CarReflection {

	/**
	 * 
	 * @return Car
	 * @throws Exception 
	 * @description 默认构造方法返回Car对象
	 */
	public static Car initByDefaultConst() throws Exception{
		//1.通过类装载器获取Car Class对象
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.spring.reflection.bean.Car");
		
		//2.获取类的默认构造器对象并实例化Car
		Constructor cons = clazz.getDeclaredConstructor((Class[])null);
		Car car = (Car) cons.newInstance();
		
		//3.通过反射方法设置属性
		Method setBrand = clazz.getMethod("setBrand", String.class);
		setBrand.invoke(car, "奔驰");
		Method setColor = clazz.getMethod("setColor", String.class);
		setColor.invoke(car, "黑色");
		Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
		setMaxSpeed.invoke(car,200);	
		
		return car;
	}
	
	/**
	 * 
	 * @return Car
	 * @throws Exception
	 * @description 带参构造方法返回Car对象
	 */
	public static Car initByParamConst() throws Exception{
		//1.通过类装载器获取Car Class对象
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.spring.reflection.bean.Car");
		
		//2.获取类的带有参数的构造器对象
		Constructor cons = clazz.getDeclaredConstructor(new Class[]{String.class, String.class, int.class});
		
		//3.使带有参数的构造器对象实例化Car
		Car car =  (Car) cons.newInstance(new Object[]{"宝马", "红色", 180});
		
		return car;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Car car_1 = initByDefaultConst();
		Car car_2 = initByParamConst();
		car_1.introduce();
		car_2.introduce();
	}

}
