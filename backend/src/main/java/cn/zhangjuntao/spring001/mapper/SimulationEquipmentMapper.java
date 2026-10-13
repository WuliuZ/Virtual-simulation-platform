package cn.zhangjuntao.spring001.mapper;

import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SimulationEquipmentMapper {
    //动态查询
    List<SimulationEquipment> selectList(String number,String name);
    //新增
    void insert( SimulationEquipment simulationEquipment);
    //    @Param("simulationEquipment")
    //编辑
    void update(SimulationEquipment simulationEquipment);
    //删除@Param("simulationEquipment")
    void delete(Integer id);
    //批量导出查询
    List<SimulationEquipment> selectSimulationEquipmentListByids(int[] ids);


    //List<SimulationEquipment> selectById(Integer id);
    Integer deleteByIds(int[] ids);

    boolean selectSimulationEquipmentListByLaboId(Integer id);

    void updateSimulationByLaboId(Integer id);
    //SimulationEquipment selectOneByNumber(String number);
}
