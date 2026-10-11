package cn.zhangjie.spring001.controller;

import cn.zhangjie.spring001.common.Result;
import cn.zhangjie.spring001.entity.Laboratory;
import cn.zhangjie.spring001.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;

    //根据编号、名称查询课程
    @GetMapping("/selectLaborytory")
    public Result selectLaboratory(String name) {
        List<Laboratory> laborytory = laboratoryService.selectLaborytory(name);
        return Result.success(laborytory);
    }
    //新增虚拟仿真设备
    @PostMapping("/insertLaboratory")
    public Result insertLaboratory(@RequestBody Laboratory laboratory){
        laboratoryService.insertLaboratory(laboratory);
        return Result.success();
    }
    //修改
    @PostMapping("/updateLaboratory")
    public Result updateLaboratory(@RequestBody Laboratory laboratory) {
        laboratoryService.updateLaboratory(laboratory);
        return Result.success();
    }
    //删除
    @DeleteMapping("/deleteLaboratoryById/{id}")
    public Result deleteLaboratory(@PathVariable Integer id) {
        laboratoryService.deleteLaboratory(id);
        return Result.success();
    }
}
