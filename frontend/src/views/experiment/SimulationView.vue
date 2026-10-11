<template>
  <el-container style="height: 100%">
    <el-aside style="width: auto"> </el-aside>
    <el-container>
      <el-main>
        <!-- 查询栏 -->
        <div>
          <el-form ref="searchForm" :inline="true" :model="searchForm">
            <!-- <el-form-item label="名称">
              <el-input v-model="searchForm.name" placeholder="名称"></el-input>
            </el-form-item> -->
            <el-form-item label="编号">
              <el-input
                v-model="searchForm.number"
                placeholder="编号"
              ></el-input>
            </el-form-item>
            <el-form-item label="设备名称">
              <el-input
                v-model="searchForm.name"
                placeholder="设备名称"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getData" icon="el-icon-search"
                >查询</el-button
              >
            </el-form-item>
          </el-form>
          <el-row>
            <el-button
              @click="addFormVisible = true"
              type="primary"
              size="medium"
              icon="el-icon-edit"
            >
              添加</el-button
            >
            <el-button
              class="user-btn"
              size="medium"
              icon="el-icon-plus"
              type="primary"
              @click="exp"
            >
              批量导出</el-button
            >
            <el-button type="danger" size="medium" @click="deleteByIds"
              >- 批量删除</el-button
            >
            <!-- <el-upload
              action="http://localhost:8080/api/user/upload"
              :show-file-list="false"
              :on-success="successUpload"
              style="display: inline-block"
            >
              <el-button class="user-btn" type="primary">批量导⼊</el-button>
            </el-upload> -->
          </el-row>

          <!-- 新增设备 -->
          <el-dialog
            title="新增设备"
            :visible.sync="addFormVisible"
            width="30%"
            :before-close="addClose"
          >
            <el-form
              ref="addForm"
              :model="addForm"
              :rules="rules"
              label-width="auto"
              size="mini"
            >
              <el-form-item label="设备编号" prop="number">
                <el-input v-model="addForm.number"></el-input>
              </el-form-item>
              <el-form-item label="设备名称" prop="name">
                <el-input v-model="addForm.name"></el-input>
              </el-form-item>
              <el-form-item label="类别" prop="type">
                <el-select v-model="addForm.type" placeholder="请选择类别">
                  <el-option label="个人电脑" value="1"></el-option>
                  <el-option label="公共电脑" value="2"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="软件系统" prop="soft_system">
                <el-select
                  v-model="addForm.soft_system"
                  placeholder="请选择软件系统"
                >
                  <el-option label="windows" value="1"></el-option>
                  <el-option label="mac" value="2"></el-option>
                  <el-option label="其他" value="3"></el-option>
                </el-select>
              </el-form-item>
              <!-- <el-form-item label="版本号" prop="version">
                <el-input v-model="addForm.version"></el-input>
              </el-form-item> -->
              <el-form-item label="设备供应商" prop="supplier">
                <el-select
                  v-model="addForm.supplier"
                  placeholder="请选择设备供应商"
                >
                  <el-option label="华迪" value="1"></el-option>
                  <el-option label="软通" value="2"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="用途" prop="purpose">
                <el-input v-model="addForm.purpose"></el-input>
              </el-form-item>
              <el-form-item label="地址" prop="purpose">
                <el-input v-model="addForm.thumbnail"></el-input>
              </el-form-item>
              <el-form-item label="所在实验室" prop="laboratory_id">
                <el-select
                  v-model="editForm.laboratory_id"
                  placeholder="请选择类别"
                >
                  <el-option
                    v-for="item in laboratoryList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="设备状态" prop="status">
                <el-select
                  v-model="addForm.status"
                  placeholder="请选择设备状态"
                >
                  <el-option label="正常" value="1"></el-option>
                  <el-option label="损坏" value="2"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="add('addForm')">提交</el-button>
                <el-button @click="addFormVisible = false">取消</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </div>
        <!-- 表格 -->
        <br />
        <el-table
          :data="tableData"
          border
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" fixed="left" width="55">
          </el-table-column>
          <el-table-column prop="number" label="设备编号" width="150">
          </el-table-column>
          <el-table-column prop="name" label="设备名称" width="120">
          </el-table-column>
          <el-table-column prop="type" label="类别" width="100">
            <template slot-scope="scope">
              {{ scope.row.type == 1 ? "个人电脑" : "公共电脑" }}
            </template>
          </el-table-column>
          <el-table-column prop="soft_system" label="软件系统" width="120">
          </el-table-column>

          <el-table-column prop="supplier" label="设备供应商" width="120">
          </el-table-column>
          <el-table-column prop="purpose" label="用途" width="120">
          </el-table-column>
          <el-table-column
            prop="laboratory_name"
            label="所在实验室"
            width="120"
          >
            <!-- <template slot-scope="scope">
              <span v-if="scope.row.laboratory_id === 1">华迪</span>
              <span v-else-if="scope.row.laboratory_id === 2">软通</span>
              <span v-else-if="scope.row.laboratory_id === 3">学链</span>
              <span v-else>未知</span>
            </template> -->
          </el-table-column>
          <el-table-column label="设备状态" width="120">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 1">正常</span>
              <span v-else-if="scope.row.status === 2">损坏</span>
              <span v-else>错误状态</span>
            </template>
          </el-table-column>
          <el-table-column prop="update_time" label="更新时间" width="200">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            align="center"
            width="135"
          >
            <template slot-scope="scope">
              <!-- <el-button type="text" size="small" @click="editDialog = true"
                >编辑</el-button
              > -->
              <el-button type="text" size="small" @click="edit(scope.row)"
                >编辑</el-button
              >
              <el-button
                type="text"
                size="small"
                @click="showDetails(scope.row)"
                >详情</el-button
              >

              <!-- <el-button type="text" size="small" @click="deleteClick()"
                >删除</el-button> -->

              <el-popconfirm
                title="是否确定删除该设备？"
                @confirm="del(scope.row.id)"
              >
                <el-button
                  type="text"
                  size="small"
                  style="margin-left: 10px"
                  slot="reference"
                  >删除</el-button
                >
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!-- 编辑设备 -->
        <el-dialog title="编辑信息" :visible.sync="editFormVisible">
          <el-form
            ref="editForm"
            :model="editForm"
            label-width="auto"
            size="mini"
          >
            <el-form-item label="设备编号">
              <el-input v-model="editForm.number"></el-input>
            </el-form-item>
            <el-form-item label="设备名称">
              <el-input v-model="editForm.name"></el-input>
            </el-form-item>
            <el-form-item label="类别">
              <el-select v-model="editForm.type" placeholder="请选择类别">
                <el-option label="个人电脑" value="1"></el-option>
                <el-option label="公共电脑" value="2"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="软件系统">
              <el-select
                v-model="editForm.soft_system"
                placeholder="请选择软件系统"
              >
                <el-option label="windows" value="windows"></el-option>
                <el-option label="mac" value="mac"></el-option>
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="版本号">
              <el-input v-model="editForm.version"></el-input>
            </el-form-item> -->
            <el-form-item label="设备供应商">
              <el-select
                v-model="editForm.supplier"
                placeholder="请选择设备供应商"
              >
                <el-option label="华迪" value="华迪"></el-option>
                <el-option label="软通" value="软通"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="用途">
              <el-input v-model="editForm.purpose"></el-input>
            </el-form-item>
            <el-form-item label="所在实验室">
              <el-select
                v-model="editForm.laboratory_id"
                placeholder="请选择类别"
              >
                <el-option
                  v-for="item in laboratoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="设备状态">
              <el-select v-model="editForm.status" placeholder="请选择设备状态">
                <el-option label="正常" value="1"></el-option>
                <el-option label="损坏" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="confirmEdit()">确 定</el-button>
            <el-button @click="editFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>
        <!-- 详情 -->
        <el-dialog :visible.sync="detailsVisible" title="仿真设备详情">
          <el-descriptions title="设备信息" :column="2">
            <el-descriptions-item label="设备编号">{{
              selectedSimulation.number
            }}</el-descriptions-item>
            <el-descriptions-item label="设备名称">{{
              selectedSimulation.name
            }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{
              selectedSimulation.type
            }}</el-descriptions-item>
            <el-descriptions-item label="软件系统">{{
              selectedSimulation.soft_system
            }}</el-descriptions-item>
            <el-descriptions-item label="设备供应商">{{
              selectedSimulation.supplier
            }}</el-descriptions-item>
            <el-descriptions-item label="用途">{{
              selectedSimulation.purpose
            }}</el-descriptions-item>
            <!-- <el-descriptions-item label="地址">{{
              selectedSimulation.thumbnail
            }}</el-descriptions-item> -->
            <el-descriptions-item label="所在实验室">{{
              selectedSimulation.laboratory_name
            }}</el-descriptions-item>
            <el-descriptions-item label="设备状态">{{
              selectedSimulation.status
            }}</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{
              selectedSimulation.update_time
            }}</el-descriptions-item>
          </el-descriptions>
          <span slot="footer">
            <el-button type="primary" @click="detailsVisible = false"
              >关闭</el-button
            >
          </span>
        </el-dialog>

        <br />
        <!-- 分页条 -->
        <!-- <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :background="background"
            :current-page="currentPage"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="5"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCount"
          >
          </el-pagination>
        </div> -->
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
import request from "../../utils/request";
import moment from "moment";

export default {
  data() {
    var checknumber = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入课程编号"));
      } else {
        callback();
      }
    };
    return {
      detailsVisible: false, //详情对话框可见性
      selectedSimulation: {}, //当前选中的设备
      addFormVisible: false, //新增设备表单可见性
      editFormVisible: false, //编辑表单可见性
      multipleSelection: [], //选中行的数据存储在该数组
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的记录数
      total: 0, // 总记录数
      //搜索框
      laboratoryList: [],
      searchForm: {
        name: "",
        number: "",
      },
      tableData: [], //表格数据
      //编辑表单
      editForm: {
        number: "", //仿真设备编号
        name: "", //仿真设备名称
        type: "", //仿真设备类别
        soft_system: "", //软件系统
        thumbnail: "", //缩略图地址
        supplier: "", //供应商
        purpose: "", //用途
        laboratory_id: "", //所在实验室
        status: "", //状态
      },
      // 新增表单
      addForm: {
        number: "", //仿真设备编号
        name: "", //仿真设备名称
        type: "", //仿真设备类别
        soft_system: "", //软件系统
        // version: "", //仿真设备版本号
        supplier: "", //供应商
        purpose: "", //用途
        laboratory_id: "", //所在实验室(缩略图地址)
        status: "", //状态
      },
      rules:{
        number: [{ required: true, message: "请输入设备编号", trigger: "blur",validator:checknumber , }],
      }
    };
  },
  methods: {
    //详情展示
    showDetails(simulation) {
      this.selectedSimulation = simulation;
      // 处理设备状态
      // this.selectedSimulation.status =
      //   simulation.status === 1 ? "正常" : "损坏";

      // 处理所在实验室
      // switch (simulation.laboratory_id) {
      //   case 1:
      //     this.selectedSimulation.laboratory_id = "华迪";
      //     break;
      //   case 2:
      //     this.selectedSimulation.laboratory_id = "软通";
      //     break;
      //   case 3:
      //     this.selectedSimulation.laboratory_id = "学链";
      //     break;
      //   default:
      //     this.selectedSimulation.laboratory_id = "未知";
      //     break;
      // }

      console.log(this.selectedSimulation);
      this.detailsVisible = true;
    },
    // 时间转换
    formatUpdateTime(dateString) {
      return moment(dateString).format("YYYY-MM-DD HH:mm:ss");
    },
    // 获取数据
    getData() {
      //发送请求访问后端接口
      let params = {
        number: this.searchForm.number,
        name: this.searchForm.name,
      };
      console.log(params);
      request
        .get("/simulationEquipment/selectSimulationEquipmentList", { params })
        .then((res) => {
          console.log(res);
          if (res.code == 0) {
            console.log(res.data);
            let tempData = res.data;
            res.data.forEach((item) => {
              item.update_time = this.formatUpdateTime(item.update_time);
            });
            this.tableData = tempData;
          } else {
            alert("查询失败");
          }
        })
        .catch((error) => {
          // 处理请求出错的情况
          this.$message({
            showClose: true,
            message: "请求出错",
            type: "error",
          });
          console.error("Error:", error);
        });
    },
    //添加数据表单的×
    addClose() {
      this.addFormVisible = false;
    },
    edit(row) {
      this.editFormVisible = true;
      let tempData = row;

      // if (tempData.status == 1) {
      //   tempData.status = "正常";
      // } else if (tempData.status == 2) {
      //   tempData.status = "损坏";
      // }
      // if(tempData.laboratory_id == 1) {
      //   tempData.laboratory_id = "华迪";
      // } else if (tempData.laboratory_id == 2) {
      //   tempData.laboratory_id = "软通";
      // } else if (tempData.laboratory_id == 3) {
      //   tempData.laboratory_id = "学链";
      // } else {
      //   tempData.laboratory_id = "未知";
      // }

      this.editForm = tempData;
    },
    stringToLocalDateTime(dateTimeString) {
      // 解析字符串为 Date 对象
      const dateTime = new Date(dateTimeString);

      // 将 Date 对象转换为 LocalDateTime 对象（JavaScript 方式）
      const localDateTime = new Date(dateTime.getTime());

      return localDateTime;
    },
    confirmEdit() {
      let params = this.editForm;
      console.log(params);
      if (params.laboratory_id == "华迪") {
        params.laboratory_id = 1;
      } else if (params.laboratory_id == "软通") {
        params.laboratory_id = 2;
      } else if (params.laboratory_id == "学链") {
        params.laboratory_id = 3;
      }
      // params.update_time = this.stringToLocalDateTime(params.update_time);
      delete params.create_time;
      delete params.update_time;
      console.log(params);
      request
        .post("/simulationEquipment/updateSimulationEquipment", params)
        .then((res) => {
          if (res.code == 0) {
            this.$message.success("修改成功！");
            this.getData();
          } else {
            this.$message.error("修改失败，设备已存在！");
            this.getData();
          }
        });
      this.editFormVisible = false;
    },

    //删除按钮的方法
    del(id) {
      request
        .delete("/simulationEquipment/deleteSimulationEquipmentById/" + id)
        .then((res) => {
          if (res.code == 0) {
            this.$message.success("删除成功！");
            this.getData();
          } else {
            this.$message.error("删除失败！");
            this.getData();
          }
        });
    },
    // 批量删除方法
    deleteByIds() {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let arr = [];
          //循环遍历获取选中数据的id并放⼊数组中
          this.multipleSelection.forEach((item) => {
            arr.push(item.id);
          });
          console.log(arr);
          request.get("/simulationEquipment/del/" + arr).then((res) => {
            if (res.code == 0) {
              this.$message.success("删除成功");
              this.getData();
            } else {
              this.$message.error("删除失败");
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message.info("已取消删除");
        });
      //发送请求访问后端批量删除接⼝
    },
    //添加设备
    add(addForm) {
      this.$refs[addForm].validate((valid) => {
        if(valid){
      let parent = this.addForm;
      //转换几个字段
      if (parent.soft_system == 1) {
        parent.soft_system = "windows";
      } else if (parent.soft_system == 2) {
        parent.soft_system = "mac";
      } else if (parent.soft_system == 3) {
        parent.soft_system = "其他";
      }
      if (parent.supplier == 1) {
        parent.supplier = "华迪";
      } else if (parent.supplier == 2) {
        parent.supplier = "软通";
      }

      console.log(parent);
      this.addFormVisible = true;
      request
        .post("/simulationEquipment/insertSimulationEquipment", parent)
        .then((result) => {
          if (result.code == 0) {
            this.$message({
              showClose: true,
              message: "添加成功",
              type: "success",
              onClose: () => {
                this.addFormVisible = false;
                this.resetForm();
                this.getData();
              },
            });
          } else {
            this.$message({
              showClose: true,
              message: "设备已存在",
              type: "error",
              onClose: () => {
                this.addFormVisible = false;
                this.resetForm();
              },
            });
          }
        });
      }else {
        this.$message({
              showClose: true,
              message: "请检查输入信息",
              type: "error",
            });
          return false;
        }
      });
    },
    //多选框记录
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val;
      this.page();
    },
    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val;
      this.page();
    },
    resetForm() {
      // 清除表单元素
      this.addForm = {};
      this.editForm = {};
    },

    exp() {
      this.$confirm("确认导出选中数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(this.multipleSelection);
          let arr = [];
          // 循环遍历获取选中数据的 id、name 和 number 并放入数组中
          this.multipleSelection.forEach((item) => {
            arr.push(item.id);
          });
          console.log(arr);
          // 发送 POST 请求
          request.get("/export/exportToExcel/" + arr);
          this.$message({
              showClose: true,
              duration: 8000 ,// 显示时间为8秒
              message: "导出成功,目录为：“D:\\exportData\\”",
              type: "success",
            });

        })
        .catch(() => {
          this.$message.info("已取消导出");
        });
    },
    findAllLaboratory() {
      request.get("/laboratory/selectLaborytory").then((result) => {
        console.log("roles:", result);
        this.laboratoryList = result.data;
      });
    },
  },
  mounted() {
    this.getData();
    this.findAllLaboratory();
  },
  computed: {
    breadcrumbList() {
      // 动态计算面包屑;
      const routes = this.$route.matched;
      const breadcrumbItems = routes.map((route) => ({
        path: route.path,
        title: route.meta.title || "未命名",
      }));

      return breadcrumbItems;
    },
  },
};
</script>
<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.clink {
  color: #525354;
  text-decoration: none;
}
</style>