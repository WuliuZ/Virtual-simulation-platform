package cn.zhangjie.spring001.service.impl;


import cn.zhangjie.spring001.entity.SimulationEquipment;
import cn.zhangjie.spring001.mapper.SimulationEquipmentMapper;
import cn.zhangjie.spring001.service.SimulationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimulationEquipmentServiceImpl implements SimulationEquipmentService {

    @Autowired
    SimulationEquipmentMapper simulationEquipmentMapper;


    @Override
    //动态查询
    public List<SimulationEquipment> selectSimulationEquipmentList(String number,String name){
        List<SimulationEquipment> list =simulationEquipmentMapper.selectList(number ,name);
        return list;
    };
    //新增
    public void insertSimulationEquipment(SimulationEquipment simulationEquipment){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间（如果需要，您可以指定时区）
        simulationEquipment.setCreate_time(now);
        // 设置更新时间（如果需要，您可以指定时区）
        simulationEquipment.setUpdate_time(now);
        // 调用 mapper 方法插入数据

        simulationEquipmentMapper.insert(simulationEquipment);
    };
    //编辑
    public void updateSimulationEquipment(SimulationEquipment simulationEquipment){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置更新时间（如果需要，您可以指定时区）
        simulationEquipment.setUpdate_time(now);
        // 调用 mapper 方法更新数据
        simulationEquipmentMapper.update(simulationEquipment);
    };
    //删除
    public void deleteSimulationEquipment(Integer id){
        simulationEquipmentMapper.delete(id);
    }
    //导出
    @Override
    public List<SimulationEquipment> selectSimulationEquipmentListByids(int[] ids) {
        return simulationEquipmentMapper.selectSimulationEquipmentListByids(ids);
    }

   //批量删除
    public Integer deleteByIds(int[] ids) {
        return simulationEquipmentMapper.deleteByIds(ids);
    }


}
