/*
 Navicat Premium Data Transfer

 Source Server         : pandaeat
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 120.78.174.107:3306
 Source Schema         : pandaeat

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 27/08/2018 13:45:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_admin
-- ----------------------------
DROP TABLE IF EXISTS `cms_admin`;
CREATE TABLE `cms_admin`  (
  `adminid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起时间',
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_cat
-- ----------------------------
DROP TABLE IF EXISTS `pe_cat`;
CREATE TABLE `pe_cat`  (
  `catid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别',
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册时间',
  `catname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名',
  PRIMARY KEY (`catid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1321322 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_cat
-- ----------------------------
INSERT INTO `pe_cat` VALUES (1000, 1000, '1111', '谷类');
INSERT INTO `pe_cat` VALUES (1001, 1000, '1111', '饮料');

-- ----------------------------
-- Table structure for pe_food
-- ----------------------------
DROP TABLE IF EXISTS `pe_food`;
CREATE TABLE `pe_food`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售价',
  `originalprice` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '原价（只做显示用）',
  `detail`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品详情，图文',
  `catid` int(11) NOT NULL DEFAULT 0 COMMENT '商品类别',
  `status` smallint(1) NOT NULL DEFAULT 0 COMMENT '上架状态：0=下架，1=上架',
  `addtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `score` int(3) NOT NULL DEFAULT 0,
  `isdelete` smallint(1) NOT NULL DEFAULT 0,
  `virtualsales` int(11) NULL DEFAULT 0 COMMENT '虚拟销量',
  `compressimg`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品缩略图',
  `img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品原图',
  `videourl`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '视频',
  `realsales` int(11) NULL DEFAULT 0 COMMENT '真实销量',
  `goodsnums` int(11) NOT NULL DEFAULT 0 COMMENT '商品剩余量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_food
-- ----------------------------
INSERT INTO `pe_food` VALUES (1, 1000, '馒头', 2.00, 0.00, '馒头馒头馒头', 1000, 1, '0', 0, 0, 10, 'http://i2.tiimg.com/655782/88fa676a5402f3d1.jpg', '馒头', '馒头', 0, 0);
INSERT INTO `pe_food` VALUES (2, 1000, '包子', 5.00, 0.00, '包子包子包子', 1000, 1, '0', 0, 0, 10, 'http://i2.tiimg.com/655782/d2e8345799ad20bb.jpg', '包子', '包子', 0, 0);
INSERT INTO `pe_food` VALUES (3, 1000, '快乐水', 3.00, 0.00, '死肥宅快乐', 1001, 1, '0', 0, 0, 5, 'http://i2.tiimg.com/655782/bcfea52579e19c5a.jpg', '是你的骄傲·', '按时间段', 0, 0);
INSERT INTO `pe_food` VALUES (1000, 1000, '雪碧', 4.00, 0.00, '写必写必', 1001, 1, '0', 0, 0, 5, 'http://i2.tiimg.com/655782/991054275275d078.jpg', '写必写必', '写必写必', 0, 0);

-- ----------------------------
-- Table structure for pe_order
-- ----------------------------
DROP TABLE IF EXISTS `pe_order`;
CREATE TABLE `pe_order`  (
  `orderid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `seatid` int(10) UNSIGNED NOT NULL COMMENT '座位id',
  `userid` int(10) UNSIGNED NOT NULL COMMENT '订单id',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起时间',
  `ordercontent` json NOT NULL COMMENT '订单内容',
  `orderprice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单价格',
  `customernum` int(11) NOT NULL DEFAULT 0 COMMENT '订单人数',
  `orderstatue` smallint(1) NOT NULL DEFAULT 0 COMMENT '订单状态 0--待确认  1--已确认  2--未付款  3--订单结束',
  `endtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `payway` smallint(1) NOT NULL DEFAULT 0 COMMENT '支付方式 0--已经支付，1--未支付，2--免单',
  `tips` varchar(255) DEFAULT "暂无备注" COMMENT '用户备注',
  `dishstatue` smallint(1) NULL DEFAULT 0 COMMENT '是否上菜 0-未上菜；1-已上菜',
  `delstatue` smallint(1) NOT NULL DEFAULT 0 COMMENT '删除状态 0-未删除 1-已删除',
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_order
-- ----------------------------
INSERT INTO `pe_order` VALUES (1000, 1000, 12, 1, '2018-08-26 16:09:41', '{\"0\": 6, \"1\": 4}', '68.00', 12, 1, '2018-08-27 10:50:37', 3, NULL, 1, 1);
INSERT INTO `pe_order` VALUES (1001, 1000, 12, 1, '2018-08-26 16:10:17', '{\"0\": 6, \"1\": 4}', '68.00', 12, 1, NULL, 3, NULL, 0, 0);
INSERT INTO `pe_order` VALUES (1002, 1000, 12, 1, '2018-08-27 10:17:23', '{\"0\": 6, \"1\": 4}', '68.00', 12, 1, NULL, 3, NULL, 0, 0);

-- ----------------------------
-- Table structure for pe_store
-- ----------------------------
DROP TABLE IF EXISTS `pe_store`;
CREATE TABLE `pe_store`  (
  `storeid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商店id',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册时间',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商店名字',
  `avatar` varchar(255) NOT NULL COMMENT '商店头像',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商店电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家地址',
  `selltime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业时间',
  `foodscore` int(4) NOT NULL DEFAULT 100 COMMENT '食物评分',
  `severscore` int(4) NOT NULL DEFAULT 100 COMMENT '服务品分',
  `avescore` int(4) NOT NULL DEFAULT 100 COMMENT '平均评分',
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家优惠/提醒',
  `ordernum` int(10) UNSIGNED NOT NULL COMMENT '营业订单数量',
  `orderprice` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业订单总额',
  `usernum` int(10) UNSIGNED NOT NULL COMMENT '活跃量 即人数',
  PRIMARY KEY (`storeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_store
-- ----------------------------
INSERT INTO `pe_store` VALUES (1000, '2018-08-23 19:52:04', '少掌柜包子店', 'http://i2.tiimg.com/655782/4f5357e8aef19692.jpg', '18642316507', '河北科技大学裕华路东20米', '9：00-10：00', 4, 5, 95, '本店最新开张，欢迎大家品尝', 11, '11', 11);

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user`  (
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `userid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户openid',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册时间',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `avatar` varchar(255) NULL COMMENT '用户头像',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话',
  `gender` tinyint(1) NOT NULL COMMENT '用户性别',
  `ordernum` int(10) UNSIGNED NOT NULL COMMENT '订单数量',
  `orderprice` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单总价值',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1009 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_user
-- ----------------------------
INSERT INTO `pe_user` VALUES (11223, 1000, 'o4zMU0ftzt7k5LbToxYHnI3qiNDU', '\"2018-08-23 17:06:24\"', '简单DI年华', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXE94zOq4ZNZ4p8Z35cnj5xKEBGXoOaccO5orgJFaAZe7SffaiaObDeO4mBL37zhEfG0eWJ5libL2A/132', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1001, 'o4zMU0ftzt7k5LbToxYHnI3qiNDU', '\"2018-08-23 17:20:56\"', '简单DI年华', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXE94zOq4ZNZ4p8Z35cnj5xKEBGXoOaccO5orgJFaAZe7SffaiaObDeO4mBL37zhEfG0eWJ5libL2A/132', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1002, 'o4zMU0ftzt7k5LbToxYHnI3qiNDU', '\"2018-08-23 17:55:36\"', '简单DI年华', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXE94zOq4ZNZ4p8Z35cnj5xKEBGXoOaccO5orgJFaAZe7SffaiaObDeO4mBL37zhEfG0eWJ5libL2A/132', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1003, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:52:04\"', '110', '111', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1004, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:55:52\"', '110', '111', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1005, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:56:23\"', '110', '111', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1006, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:56:35\"', '110', '111', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1007, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:56:44\"', '110', '111', '18642316507', 0, 0, '0');
INSERT INTO `pe_user` VALUES (11223, 1008, 'oGUyP4qjjipvK7_0wNx4KDHs_k8Q', '\"2018-08-23 19:56:53\"', '110', '111', '18642316507', 0, 0, '0');

SET FOREIGN_KEY_CHECKS = 1;
