package cn.zhangjie.spring001.service;

import cn.zhangjie.spring001.entity.Training;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TrainingService {
    List<Training> selectTraining(String name);

    void insertTraining(Training training);

    void updateTraining(Training training);

    void deleteTraining(Integer id);
}
