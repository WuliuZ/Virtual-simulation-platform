/*
 Navicat Premium Data Transfer

 Source Server         : 实训test
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : mybatist1

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 12/07/2024 10:27:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编号',
  `experiment` int NULL DEFAULT NULL COMMENT '实验',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `pte_id` int NULL DEFAULT NULL COMMENT '实验实训id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pte_id`(`pte_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`pte_id`) REFERENCES `training` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '计算机系统', '10001001', NULL, '2024-07-09 23:16:33', '2024-07-09 23:38:24', NULL, 4);
INSERT INTO `course` VALUES (2, '软件分析与设计', '10009003', NULL, '2024-07-09 23:16:33', '2024-07-10 00:16:04', NULL, 1);
INSERT INTO `course` VALUES (3, '高等数学', '10009002', NULL, '2024-07-09 23:16:33', '2024-07-09 23:16:33', NULL, NULL);
INSERT INTO `course` VALUES (4, '大学物理', '10080586', NULL, '2024-07-09 23:16:33', '2024-07-09 23:16:33', NULL, NULL);
INSERT INTO `course` VALUES (5, '离散数学', '10258946', NULL, '2024-07-09 23:16:33', '2024-07-09 23:16:33', NULL, NULL);
INSERT INTO `course` VALUES (6, '离散数学', '12688967', NULL, '2024-07-09 23:16:33', '2024-07-09 23:16:33', NULL, NULL);
INSERT INTO `course` VALUES (7, '线性代数', '25625698', NULL, '2024-07-09 23:16:33', '2024-07-09 23:16:33', NULL, NULL);

-- ----------------------------
-- Table structure for laboratory
-- ----------------------------
DROP TABLE IF EXISTS `laboratory`;
CREATE TABLE `laboratory`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '实验室编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实验室名称',
  `organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组织机构',
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `totalValue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设施总值',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '面积',
  `workStation` int NULL DEFAULT NULL COMMENT '工位数',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of laboratory
-- ----------------------------
INSERT INTO `laboratory` VALUES (1, '光学实验室', '物理学院', '张三', '15965256548', '10000', 'C501', 40, '2024-07-08 20:10:50', '2024-07-08 20:10:50');
INSERT INTO `laboratory` VALUES (2, '力学实验室', '物理学院', '王五', '15965256543', '5000', 'C304', 40, '2024-07-08 20:10:50', '2024-07-08 20:10:50');
INSERT INTO `laboratory` VALUES (3, '热学实验室', '物理学院', '李华', '15965256545', '20000', 'C401', 40, '2024-07-08 20:10:50', '2024-07-08 20:10:50');
INSERT INTO `laboratory` VALUES (4, '电学实验室', '物理学院', '刘梅', '15965256544', '15000', 'C207', 40, '2024-07-08 20:10:50', '2024-07-08 20:10:50');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL,
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `router` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `f_id` int NULL DEFAULT NULL,
  `order` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int NOT NULL,
  `role_id` int NULL DEFAULT NULL,
  `menu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '学生', '学生是学校的主要组成部分，他们在学校接受教育和学习各种知识，以提高自己的综合素质和能力。学生角色通常拥有查看课程、选择课程等权限。', '2024-07-08 14:31:26', '2024-07-08 14:31:29');
INSERT INTO `roles` VALUES (2, '教师', '老师是学校的教学和科研骨干，负责教授学生知识，引导学生进行学术研究和实践。教师角色通常拥有新增课程、查看实验室，查看课程等权限。', '2024-07-08 14:31:26', '2024-07-08 14:31:26');
INSERT INTO `roles` VALUES (3, '实验室管理员', '实验室管理员负责管理和维护实验室的日常运行，确保实验室的安全和设备正常使用。他们协助老师和学生进行实验，提供实验技术支持。实验室管理员角色通常拥有实验室管理、仿真设备管理等权限', '2024-07-08 14:31:26', '2024-07-08 14:31:26');
INSERT INTO `roles` VALUES (4, '后台管理员', '后台管理员负责管理教师和学生账号以及管理课程等任务。后台管理员角色通常拥有用户管理、课程管理等权限。', '2024-07-08 14:31:26', '2024-07-08 14:31:26');
INSERT INTO `roles` VALUES (5, '超级管理员', '超级管理员是系统最高权限的角色，负责管理整个系统的一切事务。他们可以管理其他用户账号，分配权限等。超级管理员角色通常拥有最高级别的权限，如角色管理等。', '2024-07-08 14:31:26', '2024-07-09 23:46:26');
INSERT INTO `roles` VALUES (16, '角色4', '', '2024-07-10 09:29:55', '2024-07-10 09:29:55');

-- ----------------------------
-- Table structure for simulationequipment
-- ----------------------------
DROP TABLE IF EXISTS `simulationequipment`;
CREATE TABLE `simulationequipment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '编号',
  `type` int NULL DEFAULT NULL,
  `soft_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '软件系统',
  `supplier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `purpose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `laboratory_id` int NULL DEFAULT NULL COMMENT '实验室id',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of simulationequipment
-- ----------------------------
INSERT INTO `simulationequipment` VALUES (8, '信号发生器', '10003', 2, 'windows', '华迪', 1, '波形', 1, '2024-07-08 20:13:42', '2024-07-09 22:38:09');
INSERT INTO `simulationequipment` VALUES (9, '螺旋测微计', '10005', 1, 'windows', '华迪', 1, '测量', 2, '2024-07-08 20:13:42', '2024-07-08 20:13:42');
INSERT INTO `simulationequipment` VALUES (10, '电子天平', '10004', 1, 'windows', '华迪', 1, '测量重量', 2, '2024-07-08 20:13:42', '2024-07-08 20:13:42');
INSERT INTO `simulationequipment` VALUES (11, 'ZKY-LS螺线管磁场实验仪', '4001', 1, 'windows', '华迪', 1, '磁场', 4, '2024-07-08 20:13:42', '2024-07-08 20:13:42');
INSERT INTO `simulationequipment` VALUES (12, 'ZKY-H/L霍尔效应螺线管磁场测试仪', '4005', 1, 'windows', '软通', 1, '电磁实验', 4, '2024-07-08 20:13:42', '2024-07-08 20:13:42');
INSERT INTO `simulationequipment` VALUES (13, 'ZKY-GD-3光电效应实验仪', '4006', 1, 'windows', '软通', 1, '光电效应', 1, '2024-07-08 20:13:42', '2024-07-08 20:13:42');
INSERT INTO `simulationequipment` VALUES (14, '示波器', '4007', 1, 'windows', '华迪', 1, '实验', 1, '2024-07-08 20:13:42', '2024-07-10 00:19:13');

-- ----------------------------
-- Table structure for training
-- ----------------------------
DROP TABLE IF EXISTS `training`;
CREATE TABLE `training`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `classHours` int NULL DEFAULT NULL,
  `experimentType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `courseSpecific` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `certificationMapping` int NULL DEFAULT NULL,
  `competitionMapping` int NULL DEFAULT NULL,
  `relatedTrainingResources` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `laboratory_id` int NULL DEFAULT NULL COMMENT '实验室id',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `laboratory_id`(`laboratory_id`) USING BTREE,
  CONSTRAINT `training_ibfk_1` FOREIGN KEY (`laboratory_id`) REFERENCES `laboratory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of training
-- ----------------------------
INSERT INTO `training` VALUES (1, '信号发生器与示波器的使用', '计算机', 12, '', NULL, NULL, NULL, NULL, '', '2024-07-10 09:17:25', 1, '2024-07-09 22:18:39');
INSERT INTO `training` VALUES (4, '霍尔效应', '软件工程', 12, '电学实验', NULL, NULL, NULL, NULL, '了解霍尔效应', '2024-07-09 22:18:39', 4, '2024-07-09 22:18:39');
INSERT INTO `training` VALUES (5, '利用光电效应测定普朗克常数', '计算机', 8, '光学实验', NULL, NULL, NULL, NULL, '利用光电效应测定普朗克常数', '2024-07-09 22:18:39', 1, '2024-07-09 22:18:39');
INSERT INTO `training` VALUES (6, '固体密度测量', '软件工程', 2, '力学实验', NULL, NULL, NULL, NULL, NULL, '2024-07-09 22:18:39', 2, '2024-07-09 22:18:39');
INSERT INTO `training` VALUES (7, '物理光波实验', '软件工程', 2, '物理', NULL, NULL, NULL, NULL, '', '2024-07-09 22:36:21', 1, '2024-07-09 22:18:39');

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `user_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_course
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_training
-- ----------------------------
DROP TABLE IF EXISTS `user_training`;
CREATE TABLE `user_training`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `pte_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `pte_id`(`pte_id`) USING BTREE,
  CONSTRAINT `user_training_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_training_ibfk_2` FOREIGN KEY (`pte_id`) REFERENCES `training` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_training
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (11, '小张', 'zs', '123456', '1231@qq.com', NULL, '15898686202', '2024-07-08 16:17:06', '2024-07-08 16:17:06', 6);
INSERT INTO `users` VALUES (23, '学生1', 'xuesheng1', '123456', '1232@qq.com', NULL, '15898686203', '2024-07-08 16:17:06', '2024-07-08 16:17:06', 1);
INSERT INTO `users` VALUES (24, '教师1', 'jiaoshi1', '123456', '1233@qq.com', NULL, '15898686204', '2024-07-08 16:17:06', '2024-07-08 16:17:06', 2);
INSERT INTO `users` VALUES (25, '实验室管理员1', 'sysgly1', '123456', '1235@qq.com', NULL, '15898686205', '2024-07-08 16:17:06', '2024-07-10 16:20:07', 3);
INSERT INTO `users` VALUES (26, '后台管理员', 'htgly1', '123456', '1236@qq.com', NULL, '15898686206', '2024-07-08 16:17:06', '2024-07-10 16:20:11', 4);
INSERT INTO `users` VALUES (27, '超级管理员', 'cjgly1', '123456', '1237@qq.com', NULL, '15898686201', '2024-07-08 16:17:06', '2024-07-10 15:19:00', 5);
INSERT INTO `users` VALUES (28, '张三', 'ZS12345', '123456', '1234@qq.com', NULL, '15898686209', '2024-07-08 16:17:06', '2024-07-10 00:14:52', 1);

SET FOREIGN_KEY_CHECKS = 1;
