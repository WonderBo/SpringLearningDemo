/**
 * 
 */
package com.cqu.wb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqu.wb.bean.Car;
import com.cqu.wb.bean.Result;
import com.cqu.wb.conf.CarConf;
import com.cqu.wb.exception.CarException;
import com.cqu.wb.repo.CarRepository;
import com.cqu.wb.service.CarService;
import com.cqu.wb.util.ResultUtil;

/**
 * @author 汪波
 *
 */
@RestController
public class CarController {
	@Autowired
	private CarConf car;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CarService carService;
	
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public String printCar() {
		return car.getColor() + car.getPrice();
	}
	
	/**
	 * 
	 * @return List
	 * @description 查询数据库表中所有数据
	 */
	@GetMapping(value = "/cars")
	public List<Car> getCarList() {
		return carRepository.findAll();
	}	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @description 查询数据库表中单条数据
	 */
	@GetMapping(value = "/car/{id}")
	public Optional<Car> getCar(@PathVariable("id") Integer id) {
		return carRepository.findById(id);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 * @description 按照指定条件查找数据库多条记录
	 */
	@GetMapping(value = "/cars/color/{color}")
	public List<Car> getCarList(@PathVariable("color") String color) {
		return carRepository.findByColor(color);
	}
	
	/**
	 * 
	 * @param color
	 * @param price
	 * @return
	 * @description 在数据库表中添加单条数据
	 */
	@PostMapping(value = "/addcar")
	public Car addCar(@RequestParam("color") String color, @RequestParam("price") Integer price) {
		Car car = new Car();
		car.setColor(color);
		car.setPrice(price);
		
		return carRepository.save(car);
	}
	
	/**
	 * 
	 * @param car
	 * @param bindingResult
	 * @return
	 * @description 在数据库表中添加单条数据
	 */
	@PostMapping(value = "/addcar2")
	// 为简化参数绑定，利用对象整体接受请求参数
	// @Valid的参数后必须紧挨着一个BindingResult 参数，否则spring会在校验不通过时直接抛出异常
	public Result<Object> addCar2(@Valid Car car, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		
		return ResultUtil.success(carRepository.save(car));
	}
	
	/**
	 * 
	 * @param id
	 * @param color
	 * @param price
	 * @return
	 * @description 更新数据库表中单条数据
	 */
	@PutMapping(value = "/car/{id}")
	public Car updateCar(@PathVariable("id") Integer id, @RequestParam("color") String color, @RequestParam("price") Integer price) {
		Car car = new Car();
		car.setId(id);
		car.setColor(color);
		car.setPrice(price);
		
		return carRepository.save(car);
	}
	
	/**
	 * 
	 * @param id
	 * @description 删除数据库表中单条数据
	 */
	@DeleteMapping(value = "/car/{id}")
	public void deleteCar(@PathVariable("id") Integer id) {
		carRepository.deleteById(id);
	}
	
	/**
	 * @description 数据库事务管理
	 */
	@PostMapping(value = "/cars/two")
	public void transactionTest() {
		carService.insertAtomic();
	}
	
	@GetMapping(value = "/findcar/{id}")
	public void findCar(@PathVariable("id") Integer id) throws CarException {
		carService.getCar(id);
	}
}
