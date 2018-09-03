/**
 * @description HelloWorld实现类
 */
package com.spring.configuration.bean.instance;

public class HelloWorldImpl implements HelloWorld {

    private String message;

    /**
     * @description 空构造器
     */
    public HelloWorldImpl() {
        this.message = "Hello World!";
    }

    /**
     * @param message
     * @description 带参构造器
     */
    public HelloWorldImpl(String message) {
        this.message = message;
    }

    public void sayHello() {
        System.out.println(message);
    }

}
