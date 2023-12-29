package com.ah.mybatis.controller;

import com.ah.mybatis.service.ITbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2023/11/10 23:56
 */
@RestController
@RequestMapping("/tbUser")
@RequiredArgsConstructor
public class TbUserController {


    private final ITbUserService tbUserService;

    @RequestMapping("/save")
    public void save(){
        System.out.println("test");
        tbUserService.save();
    }

    @RequestMapping("/updateById/{id}")
    public void updateById(@PathVariable Long id) {
        tbUserService.updateById(id);
    }


}
