/**
 * @description Spring中IoC与反射机制的关联（简易）
 */
package com.spring.reflection.oper;

import java.beans.Introspector;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.spring.reflection.bean.JavaBean;

public class BeanFactory {

	private Map<String, Object> beanMap = new HashMap<String, Object>();

	/**
	 * 
	 * @param xml配置文件
	 * @description bean工厂的初始化
	 */
	public void init(String xml){
		try {
			//1.获取当前线程中的类装载器对象
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//2.从classpath目录下获取指定的xml文件
			InputStream inputStream = classLoader.getResourceAsStream(xml);
			//3.创建读取配置文件的reader对象
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputStream);
			Element rootElement = document.getRootElement();
			Element foo;
			
			//4.遍历xml文件当中的Bean实例
			for(Iterator rootIterator = rootElement.elementIterator("bean"); rootIterator.hasNext();){
				foo = (Element) rootIterator.next();
				
				//5.针对每个一个Bean实例，获取bean的属性id和class
				Attribute id = foo.attribute("id");
				Attribute clazz = foo.attribute("class");
				
				//6.利用Java反射机制，通过class的名称获取Class对象
				Class bean = Class.forName(clazz.getText());
				//7.获取对应class的信息
				java.beans.BeanInfo info = Introspector.getBeanInfo(bean);
				//8.获取其属性描述器数组:数组大小 = class（1个）+ setX,getY方法取出的X与Y（|X|+|Y|个），属性描述(键值对) = name + propertyType + readMethod + writeMethod（readMethod与writeMethod都存在或存在其中一个）
				java.beans.PropertyDescriptor[] pd = info.getPropertyDescriptors();
				
				//测试PropertyDescriptor的内容
				//由测试得到，PropertyDescriptor数组中的PropertyDescriptor是由所有的setX方法和getY方法分别提取X和Y来决定的，与各个属性（属性只是起到get与set的中介作用）无关
				for(int i = 0; i < pd.length; i++){
					System.out.println(pd[i].toString());
				}
				
				//9.创建一个对象，并在接下来的代码中为对象的属性赋值
				Object obj = bean.newInstance();
				
				//10.遍历该bean的property属性
				for(Iterator foo2Iterator = foo.elementIterator("property"); foo2Iterator.hasNext();){
					Element foo_2 = (Element) foo2Iterator.next();
					
					//11.获取该property的name属性
					Attribute name = foo_2.attribute("name");
					String value = null;
					
					//12.获取该property的子元素value的值
					for(Iterator foo3Iterator = foo_2.elementIterator("value"); foo3Iterator.hasNext();){
						Element foo_3 = (Element) foo3Iterator.next();
						value = foo_3.getText();
						break;
					}
					
					//13.利用Java的反射机制调用对象的某个set方法，并将值设置进去
					for(int k = 0; k < pd.length; k++){
						if(pd[k].getName().equalsIgnoreCase(name.getText())){
							Method setMethod = null;
							setMethod = pd[k].getWriteMethod();
							setMethod.invoke(obj, value);
						}
					}
				}
				//14.将对象放入beanMap中，其中key为id值，value为对象
				beanMap.put(id.getText(), obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param beanName bean的id
	 * @return Object
	 * @description 通过bean的id获取bean的对象
	 */
	public Object getBean(String beanName){
		Object object = beanMap.get(beanName);
		return object;
	}
	
	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeanFactory beanFactory = new BeanFactory();
		beanFactory.init("com/spring/reflection/conf/config.xml");
		JavaBean javaBean = (JavaBean) beanFactory.getBean("javaBean");
		System.out.println("userName = " + javaBean.getUserName());
		System.out.println("passWord = " + javaBean.getPassword());
	}

}
