/**
 * 
 */
package com.cqu.wb.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqu.wb.bean.Car;
import com.cqu.wb.exception.CarException;
import com.cqu.wb.exception.ExceptionEnum;
import com.cqu.wb.repo.CarRepository;

/**
 * @author 汪波
 *
 */
@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	@Transactional
	public void insertAtomic() {
		Car car1 = new Car();
		car1.setColor("brown");
		car1.setPrice(333);
		carRepository.save(car1);
		
		Car car2 = new Car();
		car2.setColor("white");
		car2.setPrice(777);
		carRepository.save(car2);
	}
	
	// 统一异常处理触发方法
	public void getCar(Integer id) throws CarException {
		Car car = carRepository.getOne(id);
		Integer price = car.getPrice();
		if(price < 200) {
			throw new CarException(ExceptionEnum.LOW_LEVEL_CAR);
		} else if(price < 500) {
			throw new CarException(ExceptionEnum.MIDDLE_LEVEL_CAR);
		} else {
			throw new CarException(ExceptionEnum.HIGH_LEVEL_CAR);
		}
	}
}
