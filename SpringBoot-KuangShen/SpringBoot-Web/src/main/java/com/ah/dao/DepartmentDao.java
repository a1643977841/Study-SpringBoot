package com.ah.dao;

import com.ah.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 3:07 PM
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments;

    static {
        // 模拟数据
        departments = new HashMap<Integer, Department>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"市场部"));
        departments.put(103,new Department(103,"教研部"));
        departments.put(104,new Department(104,"运营部"));
        departments.put(105,new Department(105,"后勤部"));
    }

    /**
     * 获取所有部门信息
     * @return 所有部门对象
     */
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    /**
     * 通过部门id获取部门信息
     * @param id
     * @return 单个部门对象
     */
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}
