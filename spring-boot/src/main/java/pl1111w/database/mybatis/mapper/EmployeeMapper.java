package pl1111w.database.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import pl1111w.database.mybatis.entity.Employee;

import java.util.Map;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/2/23 21:09
 */
@Mapper
public interface EmployeeMapper {

    Employee selectEmp(int id);

    int insertEmp(Employee employee);

    boolean addEmp(Employee employee);

    int updateEmp(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmp(int id);

    int deleteEmployment(String name);

    Employee selectEmpInfo(Map<String, String> hashMap);

}
