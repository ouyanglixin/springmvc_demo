package com.oyy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyy.reggie.common.R;
import com.oyy.reggie.pojo.Category;
import com.oyy.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加菜品分类功能
     * @param category
     * @return
     */
    @PostMapping
    public R<String> insertCategory (@RequestBody Category category) {
        log.info("请求参数为:{}",category);

        //调用mybatispulas中的插入方法添加一条数据到数据库中
        categoryService.save(category);

        return R.success("添加成功");
    }
    /**
     * 分页查询菜品功能
     */
    @GetMapping("/page")
    public R<Page> page (Integer page,Integer pageSize) {

        Page<Category> categoryPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Category::getSort);

        categoryService.page(categoryPage,wrapper);

        return R.success(categoryPage);
    }

    /**
     * 分类修改功能
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update (@RequestBody Category category) {
        log.info("修改分类操作，请求参数:{}",category);
        categoryService.updateById(category);
        return R.success("修改成功");
    }
    /**
     * 删除分类
     */
    @DeleteMapping
    public R<String> delete (Long ids) {

    }
}
