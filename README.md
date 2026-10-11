# 虚拟仿真管理平台

基于 Vue 2 + Spring Boot 3 的前后端分离管理系统，面向高校虚拟仿真实验教学场景，提供用户、角色、课程、实验设备与实验室的统一管理能力。

## 功能

- 用户登录与注册（JWT 鉴权）
- 用户管理、角色管理
- 课程管理、实训课程管理
- 实验设备管理、实验室管理
- 数据导出 Excel

## 技术栈

**后端**（`backend/`，Maven `groupId` 为 `cn.yy`、`artifactId` 为 `spring-demo`，Java 包名 `cn.zhangjie.spring001`）

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
backend/     Spring Boot 后端源码与 pom.xml
frontend/    Vue 2 前端源码
sql/         数据库脚本 mybatist1.sql
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

## 后端接口分层

后端按 `controller` / `service` / `mapper` / `entity` 四层组织，并包含 `common`（JWT 拦截器与统一返回结果）、
`config`（跨域与 Web 配置）、`exception`（全局异常处理）三个支撑包。MyBatis 的 XML 映射文件位于
`backend/src/main/resources/mapper/`，覆盖 User、Role、Training、Course、Laboratory、
SimulationEquipment 六张业务表。
