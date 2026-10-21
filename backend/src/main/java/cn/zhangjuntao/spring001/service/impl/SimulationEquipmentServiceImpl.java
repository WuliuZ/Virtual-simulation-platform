package cn.zhangjuntao.spring001.service.impl;


import cn.zhangjuntao.spring001.entity.Laboratory;
import cn.zhangjuntao.spring001.entity.SimulationEquipment;
import cn.zhangjuntao.spring001.mapper.SimulationEquipmentMapper;
import cn.zhangjuntao.spring001.service.LaboratoryService;
import cn.zhangjuntao.spring001.service.SimulationEquipmentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimulationEquipmentServiceImpl implements SimulationEquipmentService {

    /** 统一用它把单元格转成字符串，数字/日期也能正确显示 */
    private static final DataFormatter FORMATTER = new DataFormatter();

    @Autowired
    SimulationEquipmentMapper simulationEquipmentMapper;

    @Autowired
    LaboratoryService laboratoryService;


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

    /**
     * Excel 批量导入
     * 表头必须与导出的 Excel 一致：
     * id | 设备名称 | 设备编号 | 类型 | 软件系统 | 设备供应商 | 状态 | 设备用途 | 所在实验室 | 创建时间 | 最后更新时间
     *
     * 规则：
     *  - id 列忽略（由数据库自增）
     *  - 创建时间/最后更新时间忽略（用当前时间）
     *  - 类型只能填「个人电脑」或「公用电脑」（也兼容数字 1/2）
     *  - 状态只能填「正常」或「损坏」（也兼容数字 1/2）
     *  - 所在实验室按名称去 laboratory 表查 id，查不到该行失败
     *  - 单行失败不影响其它行，全部失败原因一起返回
     */
    @Override
    public Map<String, Object> importFromExcel(MultipartFile file) throws IOException {
        List<String> errors = new ArrayList<>();
        int success = 0;

        try (InputStream in = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(in)) {

            Sheet sheet = workbook.getSheetAt(0);

            // ① 表头校验：一列一列比，不对就整体拒绝
            Row header = sheet.getRow(0);
            if (header == null) {
                throw new IllegalArgumentException("Excel 第一行必须是表头");
            }
            String[] expect = {"id", "设备名称", "设备编号", "类型", "软件系统",
                    "设备供应商", "状态", "设备用途", "所在实验室", "创建时间", "最后更新时间"};
            for (int i = 0; i < expect.length; i++) {
                String actual = cellText(header.getCell(i));
                if (!expect[i].equals(actual)) {
                    throw new IllegalArgumentException(
                            "表头不匹配：第 " + (i + 1) + " 列应为「" + expect[i] + "」，实际为「" + actual + "」");
                }
            }

            // ② 逐行解析，单行出错只记录、不中断
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                int excelRowNo = r + 1;      // Excel 里看到的行号

                String name = cellText(row.getCell(1));
                String number = cellText(row.getCell(2));
                if (name.isEmpty() || number.isEmpty()) {
                    errors.add("第 " + excelRowNo + " 行：设备名称、设备编号均不能为空");
                    continue;
                }

                String typeText = cellText(row.getCell(3));
                Integer type = parseType(typeText);
                if (type == null) {
                    errors.add("第 " + excelRowNo + " 行：类型「" + typeText + "」无法识别（只能填「个人电脑」或「公用电脑」）");
                    continue;
                }

                String statusText = cellText(row.getCell(6));
                Integer status = parseStatus(statusText);
                if (status == null) {
                    errors.add("第 " + excelRowNo + " 行：状态「" + statusText + "」无法识别（只能填「正常」或「损坏」）");
                    continue;
                }

                String labName = cellText(row.getCell(8));
                Integer laboratoryId = null;
                if (!labName.isEmpty()) {
                    List<Laboratory> labs = laboratoryService.selectLaborytory(labName);
                    if (labs == null || labs.isEmpty()) {
                        errors.add("第 " + excelRowNo + " 行：找不到名为「" + labName + "」的实验室");
                        continue;
                    }
                    laboratoryId = labs.get(0).getId();
                }

                SimulationEquipment equipment = new SimulationEquipment();
                equipment.setName(name);
                equipment.setNumber(number);
                equipment.setType(type);
                equipment.setSoft_system(cellText(row.getCell(4)));
                equipment.setSupplier(cellText(row.getCell(5)));
                equipment.setStatus(status);
                equipment.setPurpose(cellText(row.getCell(7)));
                equipment.setLaboratory_id(laboratoryId);
                equipment.setCreate_time(LocalDateTime.now());
                equipment.setUpdate_time(LocalDateTime.now());

                simulationEquipmentMapper.insert(equipment);
                success++;
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("successCount", success);
        data.put("failCount", errors.size());
        data.put("errors", errors);
        return data;
    }

    /** 把单元格读成字符串；空单元格返回空串 */
    private String cellText(Cell cell) {
        if (cell == null) {
            return "";
        }
        return FORMATTER.formatCellValue(cell).trim();
    }

    /** 「个人电脑」→1，「公用电脑」→2，识别不了返回 null（也兼容直接填 1/2） */
    private Integer parseType(String text) {
        if ("个人电脑".equals(text) || "1".equals(text)) {
            return 1;
        }
        if ("公用电脑".equals(text) || "2".equals(text)) {
            return 2;
        }
        return null;
    }

    /** 「正常」→1，「损坏」→2，识别不了返回 null（也兼容直接填 1/2） */
    private Integer parseStatus(String text) {
        if ("正常".equals(text) || "1".equals(text)) {
            return 1;
        }
        if ("损坏".equals(text) || "2".equals(text)) {
            return 2;
        }
        return null;
    }

}
