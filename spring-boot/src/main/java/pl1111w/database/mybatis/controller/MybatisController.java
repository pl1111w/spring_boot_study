package pl1111w.database.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl1111w.database.mybatis.entity.Employee;
import pl1111w.database.mybatis.mapper.EmployeeMapper;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/24 18:13
 */
@RestController
public class MybatisController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp_ById")
    public Employee test() {
        Employee employee = employeeMapper.selectEmp(1);
        return employee;
    }

}
