package cn.zhangjie.spring001.controller;

import cn.zhangjie.spring001.common.Result;
import cn.zhangjie.spring001.entity.Training;
import cn.zhangjie.spring001.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    //根据编号、名称查询课程
    @GetMapping("/selectTraining")
    public Result selectTraining(String name) {
        List<Training> training = trainingService.selectTraining(name);
        return Result.success(training);
    }
    //新增虚拟仿真设备
    @PostMapping("/insertTraining")
    public Result insertTraining(@RequestBody Training training){
        trainingService.insertTraining(training);
        return Result.success();
    }
    //修改
    @PostMapping("/updateTraining")
    public Result updateTraining(@RequestBody Training training) {
        trainingService.updateTraining(training);
        return Result.success();
    }
    //删除
    @DeleteMapping("/deleteTrainingById/{id}")
    public Result deleteTraining(@PathVariable Integer id) {
        trainingService.deleteTraining(id);
        return Result.success();
    }
}
