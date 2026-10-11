package cn.zhangjie.spring001.service.impl;

import cn.zhangjie.spring001.entity.Training;
import cn.zhangjie.spring001.mapper.CourseMapper;
import cn.zhangjie.spring001.mapper.TrainingMapper;
import cn.zhangjie.spring001.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<Training> selectTraining(String name){
        List<Training> list=trainingMapper.selectTraining(name);
        return list;
    };

    public void insertTraining(Training training){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间（如果需要，您可以指定时区）
        training.setCreate_time(now);
        // 设置更新时间（如果需要，您可以指定时区）
        training.setUpdate_time(now);

        trainingMapper.insertTraining(training);
    };

    public void updateTraining(Training training) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 设置更新时间（如果需要，您可以指定时区）
        training.setUpdate_time(now);
        // 调用 mapper 方法插入数据

        trainingMapper.updateTraining(training);
    };

    public void deleteTraining(Integer id){

        //删除实训实验前检查是否还有未置空的课程
        boolean hasEquipment = courseMapper.selectCourseByPteId(id);

        // 如果存在与该实训实验关联的课程，则先更新课程的实训实验 为 NULL
        if (hasEquipment) {
            //更新课程数据：将对应的实验实训的id设为null
            courseMapper.updateCourseByTrainId(id);
        }
        //删除实验实训
        trainingMapper.deleteTraining(id);

    };
}
