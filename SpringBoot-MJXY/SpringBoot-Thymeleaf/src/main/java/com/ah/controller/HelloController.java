package com.ah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author LiuHao
 * @date 2022年3月10日下午2:23:38
 */
@RestController
public class HelloController {
	/**
	 * 测试访问接口
	 * @return
	 * @return String
	 * @author LiuHao
	 * @date 2022年3月10日下午10:53:33
	 */
	@RequestMapping("/hello")
	public String heelo() {
		return "hello 阿浩";
	}
	/**
	 * 测试全局异常拦截
	 * @return
	 * @return String
	 * @author LiuHao
	 * @date 2022年3月10日下午10:53:14
	 */
	@RequestMapping("/excep")
	public String excep() {
		int a = 2 / 0;
		return "异常了" + a;
	}
}
