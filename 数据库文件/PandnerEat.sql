/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 22/08/2018 00:44:03
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
  `createtime` int(10) UNSIGNED NOT NULL COMMENT '发起时间',
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_food
-- ----------------------------
DROP TABLE IF EXISTS `pe_food`;
CREATE TABLE `pe_food`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售价',
  `original_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '原价（只做显示用）',
  `detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品详情，图文',
  `cat_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品类别',
  `status` smallint(6) NOT NULL DEFAULT 0 COMMENT '上架状态：0=下架，1=上架',
  `addtime` int(11) NOT NULL DEFAULT 0,
  `is_delete` smallint(1) NOT NULL DEFAULT 0,
  `virtual_sales` int(11) NULL DEFAULT 0 COMMENT '虚拟销量',
  `cover_pic` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品缩略图',
  `video_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '视频',
  `real_sales` int(11) NULL DEFAULT 0 COMMENT '真实销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_order
-- ----------------------------
DROP TABLE IF EXISTS `pe_order`;
CREATE TABLE `pe_order`  (
  `storeid` int(10) UNSIGNED NOT NULL COMMENT '商店id',
  `orderid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `usrid` int(10) UNSIGNED NOT NULL COMMENT '订单id',
  `createtime` int(10) UNSIGNED NOT NULL COMMENT '发起时间',
  `ordercontent` json NOT NULL COMMENT '订单内容',
  `orderprice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单价格',
  `customernum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用餐人数',
  `orderstatue` smallint(1) NOT NULL DEFAULT 0 COMMENT '订单状态 0--待确认  1--已确认  2--未付款  3--订单结束',
  `endtime` int(10) UNSIGNED NOT NULL COMMENT '结束时间',
  `payway` smallint(1) NOT NULL DEFAULT 0 COMMENT '支付方式 0--微信支付  1--支付宝  2--银行卡  3--现金',
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_store
-- ----------------------------
DROP TABLE IF EXISTS `pe_store`;
CREATE TABLE `pe_store`  (
  `storeid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商店id',
  `createtime` int(10) UNSIGNED NOT NULL COMMENT '注册时间',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商店名字',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商店头像',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商店电话',
  `ordernum` int(10) UNSIGNED NOT NULL COMMENT '营业订单数量',
  `orderprice` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业订单总额',
  `usernum` int(10) UNSIGNED NOT NULL COMMENT '活跃量 即人数',
  PRIMARY KEY (`storeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user`  (
  `storeid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商店id',
  `userid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `openid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户openid',
  `createtime` int(10) UNSIGNED NOT NULL COMMENT '注册时间',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话',
  `gender` tinyint(1) NOT NULL COMMENT '用户性别',
  `ordernum` int(10) UNSIGNED NOT NULL COMMENT '订单数量',
  `orderprice` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单总价值',
  PRIMARY KEY (`storeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
