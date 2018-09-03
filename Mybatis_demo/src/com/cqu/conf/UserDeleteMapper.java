/**
 * @description 以接口+注解的方式实现映射配置文件(SQL语句)
 */
package com.cqu.conf;

import org.apache.ibatis.annotations.Delete;

public interface UserDeleteMapper {
	/**
	 * @description 不推荐使用接口方式实现映射配置
	 * @param id
	 */
	@Delete("delete from user where id=#{id}")
	public void deleteUser(Integer id);
}
