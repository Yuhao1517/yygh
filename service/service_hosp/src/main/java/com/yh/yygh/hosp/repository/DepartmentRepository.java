package com.yh.yygh.hosp.repository;

import com.yh.yygh.hosp.pojo.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);

    List<Department> getDepartmentByHoscode(String hoscode);
}
