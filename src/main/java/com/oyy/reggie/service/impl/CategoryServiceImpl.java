package com.oyy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oyy.reggie.mapper.CategoryMapper;
import com.oyy.reggie.mapper.EmployeeMapper;
import com.oyy.reggie.pojo.Category;
import com.oyy.reggie.pojo.Employee;
import com.oyy.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
