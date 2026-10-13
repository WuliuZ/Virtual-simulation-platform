package cn.zhangjuntao.spring001.service;

import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExportExcelService {

    void exportToExcel(List<SimulationEquipment> simulationEquipmentList);

}
