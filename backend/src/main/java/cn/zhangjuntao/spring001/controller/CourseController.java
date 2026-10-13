package cn.zhangjuntao.spring001.controller;

import cn.zhangjuntao.spring001.common.Result;
import cn.zhangjuntao.spring001.entity.Course;
import cn.zhangjuntao.spring001.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    //根据编号、名称查询课程
    @GetMapping("/selectCourse")
    public Result selectCourse(String name) {
        List<Course> course = courseService.selectCourse(name);
        return Result.success(course);
    }
    //新增虚拟仿真设备
    @PostMapping("/insertCourse")
    public Result insertCourse(@RequestBody Course course){
        // 打印课程信息
        System.out.println("Controller Inserting course:");
        System.out.println("Create_time: " + course.getCreate_time());
        System.out.println("Update_time " + course.getUpdate_time());


        try {
            courseService.insertCourse(course);
            return Result.success();
        } catch (Exception e) {
            // 处理数据库异常
            return Result.error("Insert course failed: " + e.getMessage());
        }
    }
    //修改
    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody Course course) {
        // 打印课程信息
        System.out.println("Controller Update course:");
        System.out.println("Create_time: " + course.getCreate_time());
        System.out.println("Update_time " + course.getUpdate_time());

        try {
            courseService.updateCourse(course);
            return Result.success();
        } catch (Exception e) {
            // 处理数据库异常
            return Result.error("Update course failed: " + e.getMessage());
        }
    }
    //删除
    @DeleteMapping("/deleteCourseById/{id}")
    public Result deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return Result.success();
    }
}
