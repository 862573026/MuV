/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : newx-db

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-01-17 16:09:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `uID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uName` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '路人甲' COMMENT '用户姓名',
  `uPsw` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `isVisible` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可见',
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'xzj', '123456', '1');
INSERT INTO `tbl_user` VALUES ('2', 'ce2cb39@atguigu.com', '123456', '1');
INSERT INTO `tbl_user` VALUES ('3', 'atguigu.com', '123456', '0');
