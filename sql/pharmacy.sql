/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:43306
 Source Schema         : pharmacy

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 08/09/2022 13:16:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'ruandy');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `addnum` int(11) NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'add or reduce',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`, `mid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (11, 101, 10, '测试', 'add', '2022-09-06 19:11:35');
INSERT INTO `inventory` VALUES (13, 101, 50, '测试', 'add', '2022-09-08 11:49:37');
INSERT INTO `inventory` VALUES (14, 101, 30, '测试', 'reduce', NULL);
INSERT INTO `inventory` VALUES (15, 102, 80, '测试', 'add', '2022-09-08 13:14:17');

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `facturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symptom` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适应症状',
  `used` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用法用量',
  `mimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `taboo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禁忌',
  `cost` double(10, 2) NULL DEFAULT NULL COMMENT '成本',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `profit` double(10, 2) NULL DEFAULT NULL COMMENT '利润',
  `number` int(255) NULL DEFAULT 0 COMMENT '数量',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medicine
-- ----------------------------
INSERT INTO `medicine` VALUES (101, '诺氟沙星胶囊', '西药', '武鼎药品生产公司', '诺氟沙星胶囊，西药，主要用于治疗尿路感染', '尿路感染、淋病、前列腺炎、肠道感染等等', '一次400mg(4粒)、一日两次', '657938fc-02ae-43d0-8e53-9c7e26b1b5a4flsx.png', '对本品及氟喹诺酮类要过敏者禁用', 6.00, 9.00, 3.00, 30);
INSERT INTO `medicine` VALUES (102, '阿莫西林胶囊', '西药', '福建太平洋制药有限公司', '阿莫西林与青霉素作用机制相似，通过抑制细菌细胞壁生物合成而导致细菌死亡，对增殖期的敏感细菌具有杀菌作用。', '本品尚可用于治疗伤寒、伤寒带菌者及钩端螺旋体病；阿莫西林亦可与克拉霉素、兰索拉唑三联用药根除胃、十二指肠幽门螺杆菌，降低消化道溃疡复发率。', '本品为处方药，必须由医生根据病情开处方拿药，并遵医嘱用药，包括用法、用量、用药时间等，不得擅自按照药品说明书自行用药。', '924c6e37-9d86-4961-93c4-48b468b1c19damxl.png', '既往对阿莫西林或其他β-内酰胺类抗生素（如青霉素类和头孢菌素类）发生严重过敏反应的患者禁用本品。', 18.00, 28.80, 10.80, 80);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1662597837454', '李伟', '101,102', '2022-09-08 08:43:57');
INSERT INTO `orders` VALUES ('1662601696112', '郭逸轩', '101,102,101', '2022-09-08 09:48:16');
INSERT INTO `orders` VALUES ('1662575692043', '董金金', '101', '2022-09-08 02:34:52');
INSERT INTO `orders` VALUES ('1662553300113', '郭逸轩', '101,102,102,101,', '2022-09-07 20:21:40');
INSERT INTO `orders` VALUES ('1662609125495', '李伟', '101,102', '2022-09-08 11:52:05');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '中药', '中国传统医药理论指导采集、炮制、制剂，说明作用机理，指导临床应用的药物');
INSERT INTO `type` VALUES (2, '中成药', '以中药材为主要原料，在中医药理论指导下，按照规定的处方和固定的工艺批量生产的药物');
INSERT INTO `type` VALUES (3, '西药', '有机化学药品以及无机化学药品和生物制品等药物');
INSERT INTO `type` VALUES (4, '生物制药', '运用微生物学、生物学、医学、生物化学等的研究成果，从生物体、生物组织、细胞、器官、体液等，综合利用微生物学、化学、生物化学、生物技术、药学等科学的原理和方法制造的一类用于预防、治疗和诊断的制品');
INSERT INTO `type` VALUES (5, '医疗器具', '用于医疗的器具');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('mavis', 101, '123456', '1530624156@qq.com', '郭逸轩', '13797383596');
INSERT INTO `user` VALUES ('lx', 112, '123456', 'liuxiang@qq.com', '刘翔', '12312312312');
INSERT INTO `user` VALUES ('cj', 113, '123456', 'chenjie@qq.com', '陈杰', '12312312312');
INSERT INTO `user` VALUES ('xzh', 114, '123456', 'xiazihao@qq.com', '夏梓豪', '12312312312');

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
  `wid` int(11) NOT NULL,
  `wpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wtel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`wid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES (1001, '123456', '郭逸轩', '13797383596');
INSERT INTO `worker` VALUES (1003, '123456', '陈杰', '12312312312');
INSERT INTO `worker` VALUES (1002, '123456', '刘翔', '12312312312');
INSERT INTO `worker` VALUES (1004, '123456', '夏梓豪', '12312312312');

SET FOREIGN_KEY_CHECKS = 1;
