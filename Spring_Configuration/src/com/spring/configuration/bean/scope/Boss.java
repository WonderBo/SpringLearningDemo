/**
 * @description 老板类
 */
package com.spring.configuration.bean.scope;

public class Boss {
    private String name ;
    private Car car;
    
    public Boss(){
    	
    }
    
    public Car getCar() {
		return car;
	}
    
    //属性car的关联注入方法（关键）
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
    public String toString() {
    	return "name:"+name+"\n car:"+car;
    }
}
