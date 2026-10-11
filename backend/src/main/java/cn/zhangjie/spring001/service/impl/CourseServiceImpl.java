package cn.zhangjie.spring001.service.impl;

import cn.zhangjie.spring001.entity.Course;
import cn.zhangjie.spring001.mapper.CourseMapper;
import cn.zhangjie.spring001.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Override
    //查询
    public List<Course> selectCourse(String name){
        List<Course> list =courseMapper.selectCourse(name);
        return list;
    };
    //添加
    public void insertCourse(Course course){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间（如果需要，您可以指定时区）
        course.setCreate_time(now);
        // 设置更新时间（如果需要，您可以指定时区）
        course.setUpdate_time(now);
        // 调用 mapper 方法插入数据
        System.out.println("ServiceImpl Inserting course:");
        System.out.println("Create_time: " + course.getCreate_time());
        System.out.println("Update_time " + course.getUpdate_time());
        courseMapper.insertCourse(course);
    };
    //修改
    public void updateCourse(Course course){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 设置更新时间（如果需要，您可以指定时区）
        course.setUpdate_time(now);
        // 打印课程信息
        System.out.println("ServiceImpl Update course:");
        System.out.println("Create_time: " + course.getCreate_time());
        System.out.println("Update_time " + course.getUpdate_time());
        // 调用 mapper 方法插入数据
        courseMapper.updateCourse(course);
    };
    //删除
    public void deleteCourse(Integer id){
        courseMapper.deleteCourse(id);
    };
}
