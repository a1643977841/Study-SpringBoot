package com.ah.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问静态资源使用Controller注解
 * @author LiuHao
 * @date 2022年3月10日下午11:13:48
 */
@Controller
public class PageController {
	/**
	 * 测试thymeleaf模版引擎
	 * @return
	 * @return String
	 * @author LiuHao
	 * @date 2022年3月10日下午11:14:12
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("name", "阿浩");
		return "index";
	}
	/**
	 * 测试thymeleaf模版引擎
	 * 多条数据
	 * @return
	 * @return String
	 * @author LiuHao
	 * @date 2022年3月10日下午11:14:12
	 */
	@RequestMapping("/indexList")
	public String indexList(Model model) {
		List<String> list = new ArrayList<String>();
		list.add("aaabbb");
		list.add("bbbccc");
		list.add("cccddd");
		model.addAttribute("name", "阿浩");
		model.addAttribute("list",list);
		model.addAttribute("phone","15696017043");
		return "indexList";
	}
}
