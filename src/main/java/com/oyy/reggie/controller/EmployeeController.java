package com.oyy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyy.reggie.common.R;
import com.oyy.reggie.pojo.Employee;
import com.oyy.reggie.service.EmployeeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登入请求控制
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //进行逻辑处理
        R<Employee> employeeR =  employeeService.login(employee);
        return employeeR;
    }

    /**
     * 退出控制器
     */

    @PostMapping("/logout")
    public R<String> logout (HttpServletRequest request ) {
        //1.清除当前session 中存储的当前用户的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
      //  employee.setCreateTime(LocalDateTime.now());
       // employee.setUpdateTime(LocalDateTime.now());
      //  employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
      //  employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        log.info("新增员工员工信息为:{}",employee);

        employeeService.save(employee);

        return R.success("添加成功");
    }

    /**
     * 分页查询
     */

    @GetMapping("/page")
    public R<Page> page (int page , int pageSize , String name) {
        log.info("pag:{},pagSize:{},name:{}",page,pageSize,name);

        //1，构建分页条件
        Page pagInfo = new Page(page , pageSize);

        //2.创建条件构造器
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper();
        //模糊查询
        wrapper.like(name != null, Employee::getName, name);
        //添加排序条件
        wrapper.orderByDesc(Employee::getUpdateTime);

        //执行分页
        employeeService.page(pagInfo,wrapper);

        return R.success(pagInfo);
    }

    /**
     * 员工账号状态设置
     */
    @PutMapping
    public R<String> update (HttpServletRequest request , @RequestBody Employee employee) {
        log.info("被修改的用户id为:{}",employee.getId());

       // employee.setUpdateTime(LocalDateTime.now());
       // employee.setUpdateUser((Long)request.getSession().getAttribute("employee"));

        employeeService.updateById(employee);

        return R.success("修改成功");
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public R<Employee> getById (@PathVariable Long id) {
        log.info("根据id查询，id为:{}",id);
        Employee byId = employeeService.getById(id);

        return R.success(byId);
    }
}
