package cn.zhangjie.spring001.service;

import cn.zhangjie.spring001.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {
    //查询
    List<Course> selectCourse(String name);
    //添加
    void insertCourse(Course course);
    //修改
    void updateCourse(Course course);
    //删除
    void deleteCourse(Integer id);
}
