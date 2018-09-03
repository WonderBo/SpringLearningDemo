/**
 * 
 */
package com.cqu.wb.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 汪波
 * @description 接受配置文件中的配置项
 */
@Component
@ConfigurationProperties(prefix = "car")
public class CarConf {
	private String color;
	private Integer price;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
