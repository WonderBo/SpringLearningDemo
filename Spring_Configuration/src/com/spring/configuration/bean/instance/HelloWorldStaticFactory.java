/**
 * @description HelloWorld静态工厂
 */
package com.spring.configuration.bean.instance;

public class HelloWorldStaticFactory {
	 /**
     * @param message
     * @return
     * @description 工厂方法
     */
    public static HelloWorld newInstance(String message) {
        // 返回需要的Bean实例
        return new HelloWorldImpl(message);
    }

}
