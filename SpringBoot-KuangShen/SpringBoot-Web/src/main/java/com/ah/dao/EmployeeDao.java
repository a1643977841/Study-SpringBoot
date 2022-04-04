package com.ah.dao;

import com.ah.entity.Department;
import com.ah.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 3:18 PM
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        // 模拟数据
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "张三", "1643977841@qq.com", 0, new Department(101, ""), new Date()));
        employees.put(1002, new Employee(1002, "王武", "24124214@qq.com", 0, new Department(102, ""), new Date()));
        employees.put(1003, new Employee(1003, "李四", "112421423@qq.com", 1, new Department(103, ""), new Date()));
        employees.put(1004, new Employee(1004, "tom", "1244242431@qq.com", 1, new Department(104, ""), new Date()));
        employees.put(1005, new Employee(1005, "赵七", "24423341@qq.com", 0, new Department(105, ""), new Date()));
    }

    /**
     * 主键自增
     */
    private static Integer initId = 1006;

    /**
     * 添加员工
     * @param employee 员工对象
     */
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        return employees.put(employee.getId(), employee);
    }

    /**
     * 获取所有员工信息
     * @return 所有员工信息
     */
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    /**
     * 根据id获取单个员工信息
     * @param id
     * @return 单个员工信息
     */
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    /**
     * 根据id删除员工信息
     * @param id 员工id
     */
    public Employee deleteEmployeeById(Integer id) {
        return employees.remove(id);
    }
}
