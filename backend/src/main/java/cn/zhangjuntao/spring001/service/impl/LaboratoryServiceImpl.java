package cn.zhangjuntao.spring001.service.impl;

import cn.zhangjuntao.spring001.entity.Laboratory;
import cn.zhangjuntao.spring001.entity.User;
import cn.zhangjuntao.spring001.mapper.LaboratoryMapper;
import cn.zhangjuntao.spring001.mapper.SimulationEquipmentMapper;
import cn.zhangjuntao.spring001.mapper.TrainingMapper;
import cn.zhangjuntao.spring001.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    @Autowired
    LaboratoryMapper laboratoryMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    SimulationEquipmentMapper simulationEquipmentMapper;
    @Override
    public List<Laboratory> selectLaborytory(String name){
        List<Laboratory> list = laboratoryMapper.selectLaboratory(name);
        return list;
    };

    public void insertLaboratory(Laboratory laboratory){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间（如果需要，您可以指定时区）
        laboratory.setCreate_time(now);
        // 设置更新时间（如果需要，您可以指定时区）
        laboratory.setUpdate_time(now);
        // 调用 mapper 方法插入数据
        laboratoryMapper.insertLaboratory(laboratory);
    };

    public void updateLaboratory(Laboratory laboratory){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 设置更新时间（如果需要，您可以指定时区）
        laboratory.setUpdate_time(now);

        laboratoryMapper.updateLaboratory(laboratory);
    };

    public void deleteLaboratory(Integer id) {
        // 获取与该实验室关联的实验实训记录是否存在
        boolean hasTraining = trainingMapper.selectTrainingByLaboId(id);

        // 如果存在与该实验室关联的实训记录，则先更新记录的实验室 ID 为 NULL
        if (hasTraining) {
            trainingMapper.updateTrainingByLaboId(id);
        }

        // 获取与该实验室关联的实验设备数量
        // 获取与该实验室关联的实验设备是否存在
        boolean hasEquipment = simulationEquipmentMapper.selectSimulationEquipmentListByLaboId(id);

        // 如果存在与该实验室关联的设备，则先更新记录的实验室 ID 为 NULL
        if (hasEquipment) {
            simulationEquipmentMapper.updateSimulationByLaboId(id);
        }

        // 删除实验室
        laboratoryMapper.deleteLaboratory(id);
    }




}
