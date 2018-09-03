/**
 * 
 */
package com.cqu.wb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqu.wb.bean.Car;

/**
 * @author 汪波
 * @description 只需要继承JpaRepository<bean, 主键>接口即可调用其DAO方法
 */
public interface CarRepository extends JpaRepository<Car, Integer> {
	// 通过颜色查找（按照指定格式扩展API而不用实现）
	public List<Car> findByColor(String color);
}
