package cn.zhangjie.spring001.controller;


import cn.zhangjie.spring001.common.Result;
import cn.zhangjie.spring001.entity.SimulationEquipment;
import cn.zhangjie.spring001.service.SimulationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/simulationEquipment")
public class SimulationEquipmentController {
    @Autowired
    SimulationEquipmentService simulationEquipmentService;

    //根据编号、名称查询虚拟仿真设备
    @GetMapping("/selectSimulationEquipmentList")
    public Result selectSimulationEquipmentList(String number,String name) {
        List<SimulationEquipment> simulationEquipment = simulationEquipmentService.selectSimulationEquipmentList(number, name);
        return Result.success(simulationEquipment);
    }
    //新增虚拟仿真设备
    @PostMapping("/insertSimulationEquipment")
    public Result add(@RequestBody SimulationEquipment simulationEquipment){
        simulationEquipmentService.insertSimulationEquipment(simulationEquipment);
        return Result.success();
    }
//    //根据id查询虚拟仿真设备
//    @GetMapping("/selectSimulationEquipmentById/{id}")
//    public Result selectSimulationEquipmentList(@PathVariable Integer id) {
//        List<SimulationEquipment> simulationEquipment = simulationEquipmentService.selectSimulationEquipmentById(id);
//        return Result.success(simulationEquipment);
//    }
    //修改虚拟仿真设备
    @PostMapping("/updateSimulationEquipment")
    public Result updateSimulationEquipment(@RequestBody SimulationEquipment simulationEquipment) {
        simulationEquipmentService.updateSimulationEquipment(simulationEquipment);
        return Result.success();
    }
    //删除虚拟仿真设备
    @DeleteMapping("/deleteSimulationEquipmentById/{id}")
    public Result deleteSimulationEquipment(@PathVariable Integer id) {
        simulationEquipmentService.deleteSimulationEquipment(id);
        return Result.success();
    }
    //批量删除
    @GetMapping("/del/{ids}")
    public Result deleteByIds(@PathVariable int[] ids) {
        Integer i = simulationEquipmentService.deleteByIds(ids);
        if (i == 0) {
            return Result.error();
        }
        return Result.success();
    }
}
