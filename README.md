# 虚拟仿真管理平台

面向高校虚拟仿真实验教学场景的前后端分离管理系统，提供用户、角色、实训课程、课程、实验设备、实验室六类资源的统一管理，并支持实验设备数据导出 Excel。

本项目为高校实训课程的小组协作作品（4 人小组），源码为团队整合后的最终版本。

- 在线地址：https://github.com/WuliuZ/Virtual-simulation-platform
- 后端 42 个 Java 类 / 前端 15 个 Vue 页面组件，共 87 个文件

## 功能

| 模块 | 说明 |
| --- | --- |
| 用户 | 注册、登录、按用户名/手机号/邮箱查重、条件组合查询 |
| 角色 | 角色管理与用户-角色关联 |
| 实训课程 | 实训课程的增删改查 |
| 课程 | 课程信息的增删改查 |
| 实验室 | 实验室信息的增删改查 |
| 实验设备 | 虚拟仿真设备的增删改查，支持导出 Excel |
| 鉴权 | 基于 JWT 的登录校验，拦截器统一拦截非白名单请求 |

## 技术栈

**后端**（`backend/`，Java 包名 `cn.zhangjie.spring001`）

| 组件 | 版本 |
| --- | --- |
| Spring Boot | 3.2.6 |
| JDK | 17 |
| MyBatis | 3.0.3 |
| tk.mybatis mapper | 4.1.5 |
| MySQL 驱动 | 8.0.23 |
| Java-JWT (auth0) | 3.10.3 |
| Hutool | 5.5.4 |
| Apache POI | 5.2.4 |

**前端**（`frontend/`，工程名 `zj-vue001`）

| 组件 | 版本 |
| --- | --- |
| Vue | 2.6.14 |
| Vue Router | 3.5.1 |
| Vuex | 3.6.2 |
| Element UI | 2.15.14 |
| Axios | 1.7.2 |
| Vue CLI | 5.0 |

## 目录结构

```
backend/
  src/main/java/cn/zhangjie/spring001/
    common/       JwtInterceptor（登录拦截）、JwtTokenUtils、Result（统一响应体）
    config/       CorsConfig（跨域）、WebConfig（拦截器注册）
    controller/   User / Role / Training / Course / Laboratory / SimulationEquipment / ExportExcel
    service/      各模块 Service 接口与 impl 实现
    mapper/       6 个 Mapper 接口
    entity/       6 个实体类
    exception/    CustomException、GlobalExceptionHandler（全局异常处理）
  src/main/resources/
    application.yml            数据库连接等配置
    mapper/*.xml               6 份 MyBatis XML 映射文件
frontend/
  src/
    views/        login / user / course / experiment 四个业务目录
    router/       前端路由
    store/        Vuex
    utils/        request.js（Axios 封装，统一携带 token）
sql/
  mybatist1.sql   数据库脚本
```

## 快速开始

1. 创建 MySQL 数据库，导入 `sql/mybatist1.sql`。
2. 按本地环境修改 `backend/src/main/resources/application.yml` 中的数据库连接配置。
3. 启动后端：

   ```bash
   cd backend
   mvn spring-boot:run
   ```

4. 启动前端：

   ```bash
   cd frontend
   npm install
   npm run serve
   ```

## 关键实现说明

- **登录鉴权**：`JwtInterceptor` 实现 `HandlerInterceptor`，在 `preHandle` 中从请求头 `token` 或请求参数取 token，为空则抛出 `CustomException("登录失效,请重新登录")`，校验通过后从载荷取出 userId 并查询用户信息。
- **统一响应与异常**：所有接口返回 `Result`；`GlobalExceptionHandler` 用 `@RestControllerAdvice` 统一捕获异常。
- **前端请求**：`utils/request.js` 封装 Axios 实例，请求拦截器统一注入 token，响应拦截器统一处理错误码。

## 已知限制

- Excel 导出目前调用 Apache POI 的 `XSSFWorkbook` 写入服务端本地目录，浏览器直接下载的代码被注释保留，未接入 HTTP 响应流。
- 项目为教学实训作品，未做权限细粒度控制、参数校验与并发处理的完整覆盖。
- 数据库连接配置需按本地环境自行修改。
