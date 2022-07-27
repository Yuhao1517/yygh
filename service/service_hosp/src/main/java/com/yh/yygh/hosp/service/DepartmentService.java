package com.yh.yygh.hosp.service;

import com.yh.yygh.hosp.pojo.Department;
import com.yh.yygh.hosp.pojo.vo.DepartmentQueryVo;
import com.yh.yygh.hosp.pojo.vo.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void save(Map<String, Object> paramMap);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);

    List<DepartmentVo> findDeptTree(String hoscode);

    String getDepName(String hoscode, String depcode);

    Department getDepartment(String hoscode, String depcode);
}
