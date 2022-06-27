package com.wenxing.community;

import com.wenxing.community.config.AlphaConfig;
import com.wenxing.community.dao.AlphaDao;
import com.wenxing.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//为了引用原版的配置类
public class CommunityApplicationTests implements ApplicationContextAware { //哪个类想得到容器就实现这个接口，ApplicationContextAware：bean接口

	private ApplicationContext applicationContext; //设置一个变量，用来记录spring容器

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { //ApplicationContext就是容器，是一个子接口，比父接口扩展了更多的方法，更强
		this.applicationContext = applicationContext; //记录下来
	}

	//此段不用改变，只需要创建一个新的bean,在bean里面加个@primary即可，非常高效，且bean之间耦合度低
	//依赖的是接口，而不是方法   面向接口的思想
	@Test
	//从容器中获取bean的基本方式
	public void testApplicationContext() { //测试spring容器
		System.out.println(applicationContext);  //检测容器里是否有值，结果证明容器存在且可见

		//按照类型获取bean，满足AlphaDao.class的有两个（mybatis和hibernate）
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//从容器里获取alphaDaoHibernateImpl自动装配的bean
		System.out.println(alphaDao.select());//调用查询方法，并打印结果   最后输出Hibernate或MyBatis

		//指定bean的名字并获取方法
		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
	}
	@Test
	//测试bean的管理方式
	public void testBeanManagement(){//被spring容器管理的bean默认单个使用单例
		//scope改为prototype后，每次使用getbean都会实例化一次
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService); //看bean是否存在

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService); //看bean是否存在
	}

	@Test
	//使用第三方的bean
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat =
			applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired //使用依赖注入的方法
	@Qualifier("alphaHibernate")  //spring容器会把这里写的bean注入
	private AlphaDao alphaDao;//希望spring 容器将AlphaDao注入给alphaDao属性，便于直接使用alphaDao属性

	@Autowired //使用依赖注入的方法
	private AlphaService alphaService;

	@Autowired //使用依赖注入的方法
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
