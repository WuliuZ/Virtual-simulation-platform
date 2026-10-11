package cn.zhangjie.spring001;

import cn.zhangjie.spring001.controller.ExportExcelController;
import cn.zhangjie.spring001.entity.SimulationEquipment;
import cn.zhangjie.spring001.entity.Training;
import cn.zhangjie.spring001.mapper.TrainingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(ExportExcelController.class)
@SpringBootTest
class Spring001ApplicationTests {
//    @Autowired
//    TrainingMapper trainingMapper;
//
//   @Test
//    public void contextLoads() {
//       for (Training training : trainingMapper.selectTraining("")) {
//           System.out.println(training);
//       }
//       System.out.println(); // 添加一个额外的换行符
//    }
////    public class ExportExcelControllerTest {
//
////        @Autowired
////        private MockMvc mockMvc;
//
////        @Test
////        public void exportToExcel() throws Exception {
////            // 创建一个 MockHttpServletResponse 对象
////            MockHttpServletResponse response = mockMvc.perform(get("/export/exportToExcel"))
////                    .andExpect(status().isOk()) // 期望返回状态码为 200
////                    .andReturn() // 返回整个请求和响应的信息
////                    .getResponse(); // 获取响应对象
////
////            // 你可以使用 response 对象来验证响应内容或者执行其他操作
////            // 例如，你可以检查响应类型是否为 application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
////            // 或者你可以将响应内容写入文件进行验证
////
////            // 不要关闭 response 对象，因为它是只读的
////
////            // 测试完成后，关闭 MockMvc 实例
////            //mockMvc.close();
////        }
////    }
//
//


}
