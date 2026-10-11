package cn.zhangjie.spring001.service.impl;

import cn.zhangjie.spring001.entity.SimulationEquipment;
import cn.zhangjie.spring001.service.ExportExcelService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {
    public void exportToExcel(List<SimulationEquipment> simulationEquipmentList){
        // 创建一个UUID作为文件名的一部分
        String uuid = UUID.randomUUID().toString();

        // 获取当前时间作为文件名的一部分
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String currentTime = dateFormat.format(date);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedTime = currentTime.format(dateTimeFormatter);
        new File("D:/exportData/").mkdirs();


        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SimulationEquipment");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            String[] headers = {"id","设备名称", "设备编号", "类型", "软件系统", "设备供应商","状态", "设备用途", "所在实验室", "创建时间", "最后更新时间"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }




            // 填充数据
            int rowNum = 1;
            for (SimulationEquipment simulationEquipment : simulationEquipmentList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(simulationEquipment.getId());
                row.createCell(1).setCellValue(simulationEquipment.getName());
                row.createCell(2).setCellValue(simulationEquipment.getNumber());
                row.createCell(3).setCellValue(simulationEquipment.getType()==1?"个人电脑":"公用电脑");
                row.createCell(4).setCellValue(simulationEquipment.getSoft_system());
                row.createCell(5).setCellValue(simulationEquipment.getSupplier());
                row.createCell(6).setCellValue(simulationEquipment.getStatus() == 1 ?"正常" : "损坏");
                row.createCell(7).setCellValue(simulationEquipment.getPurpose());
                row.createCell(8).setCellValue(simulationEquipment.getLaboratory_name());
                row.createCell(9).setCellValue(simulationEquipment.getCreate_time().format(dateFormat));
                row.createCell(10).setCellValue(simulationEquipment.getUpdate_time().format(dateFormat));
            }
            // 写出数据到文件系统或任何其他存储介质
            // 例如，写入到文件系统的文件
            FileOutputStream outputStream = new FileOutputStream("D:/exportData/"+"simulationEquipment_" + formattedTime + "_" + uuid + ".xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {

        }
        //            // 设置响应头
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition", "attachment; filename=simulationEquipment.xlsx");
//
//            // 写出数据到响应的输出流
//            ServletOutputStream outputStream = response.getOutputStream();
//            workbook.write(outputStream);
//            outputStream.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    };


}
