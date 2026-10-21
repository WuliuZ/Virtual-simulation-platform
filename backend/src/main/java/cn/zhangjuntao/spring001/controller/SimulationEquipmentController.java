package cn.zhangjuntao.spring001.controller;


import cn.zhangjuntao.spring001.common.Result;
import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import cn.zhangjuntao.spring001.service.SimulationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    /**
     * Excel 批量导入
     * 完整路径：POST /api/simulationEquipment/import   （/api 前缀由 WebConfig 统一加）
     * 表单字段名：file
     * 返回：{ code, data: { successCount, failCount, errors: [...] } }
     */
    @PostMapping("/import")
    public Result importExcel(@RequestParam("file") MultipartFile file) {
        // ① 空文件校验
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的 Excel 文件");
        }
        // ② 后缀校验：只收 .xlsx（.xls 是另一套 POI 实现）
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".xlsx")) {
            return Result.error("只支持 .xlsx 格式，请用 Excel 另存为 .xlsx 后再上传");
        }
        // ③ 交给 Service 解析，异常统一转成错误响应
        try {
            Map<String, Object> data = simulationEquipmentService.importFromExcel(file);
            return Result.success(data);
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            return Result.error("导入失败：" + e.getMessage());
        }
    }
}
