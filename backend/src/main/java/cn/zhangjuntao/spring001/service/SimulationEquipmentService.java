package cn.zhangjuntao.spring001.service;


import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface SimulationEquipmentService {
    //编辑
    void updateSimulationEquipment(SimulationEquipment simulationEquipment);
    //动态查询
    List<SimulationEquipment> selectSimulationEquipmentList(String number,String name);
    //新增
    void insertSimulationEquipment(SimulationEquipment simulationEquipment);
    //根据id删除
    void deleteSimulationEquipment(Integer id);
    //批量导出的查询语句
    List<SimulationEquipment> selectSimulationEquipmentListByids(int[] ids);


//    SimulationEquipment selectOneByNumber(String number);
    //批量删除
    Integer deleteByIds(int[] ids);

//    List<SimulationEquipment> selectSimulationEquipmentById(Integer id);

}
