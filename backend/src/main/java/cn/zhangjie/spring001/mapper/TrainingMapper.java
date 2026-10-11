package cn.zhangjie.spring001.mapper;

import cn.zhangjie.spring001.entity.Training;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TrainingMapper {
    List<Training> selectTraining(String name);

    void insertTraining(Training training);

    void updateTraining(Training training);

    void deleteTraining(Integer id);

    void updateTrainingByLaboId(Integer id);

    boolean selectTrainingByLaboId(Integer id);
}
