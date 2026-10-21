package cn.zhangjuntao.spring001.service;


import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    //Excel 批量导入：返回成功条数、失败条数、失败明细
    Map<String, Object> importFromExcel(MultipartFile file) throws IOException;


//    SimulationEquipment selectOneByNumber(String number);
    //批量删除
    Integer deleteByIds(int[] ids);

//    List<SimulationEquipment> selectSimulationEquipmentById(Integer id);

}
