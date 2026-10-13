package cn.zhangjuntao.spring001.mapper;

import cn.zhangjuntao.spring001.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CourseMapper {
    List<Course> selectCourse(String name);

    void insertCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Integer id);

    void updateCourseByTrainId(Integer id);

    boolean selectCourseByPteId(Integer id);
}
