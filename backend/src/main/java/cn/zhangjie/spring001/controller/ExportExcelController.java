package cn.zhangjie.spring001.controller;

import cn.zhangjie.spring001.entity.SimulationEquipment;
import cn.zhangjie.spring001.service.ExportExcelService;
import cn.zhangjie.spring001.service.SimulationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/export")
public class ExportExcelController {
    @Autowired
    SimulationEquipmentService simulationEquipmentService;

    @Autowired
    ExportExcelService exportExcelService;

    @GetMapping("/exportToExcel/{ids}")
    public void exportToExcel(@PathVariable int[] ids) {
        // 从数据库查询数据
        List<SimulationEquipment> simulationEquipmentList = simulationEquipmentService.selectSimulationEquipmentListByids(ids);
        for (SimulationEquipment simulationEquipment : simulationEquipmentList) {
            System.out.println(simulationEquipment);
        }
        System.out.println(); // 添加一个额外的换行符
        // 导出数据
        exportExcelService.exportToExcel(simulationEquipmentList);
    }
//    @GetMapping("/exportToExcel")
//    public void exportToExcel(HttpServletResponse response,String number,String name) {
//        // 从数据库查询数据
//        List<SimulationEquipment> simulationEquipmentList = simulationEquipmentService.selectSimulationEquipmentList(number,name);
//
//        // 导出数据
//        exportExcelService.exportToExcel(response, simulationEquipmentList);
//    }
}
