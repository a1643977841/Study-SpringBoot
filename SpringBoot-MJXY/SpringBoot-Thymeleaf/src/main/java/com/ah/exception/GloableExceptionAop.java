package com.ah.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * @author LiuHao
 * @date 2022年3月10日下午10:57:52
 */
@ControllerAdvice
public class GloableExceptionAop {
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String runtimeException() {
		return "捕获全局异常";
	}
}
