/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : muv_db

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-03 19:32:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_log
-- ----------------------------
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户账户操作日志主键',
  `log_name` varchar(255) DEFAULT NULL COMMENT '日志名称(login,register,logout)',
  `user_id` varchar(30) DEFAULT NULL COMMENT '用户名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `succeed` tinyint(4) DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `message` varchar(255) DEFAULT NULL COMMENT '具体消息',
  `ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8 COMMENT='登录注册登出记录';

-- ----------------------------
-- Records of account_log
-- ----------------------------
INSERT INTO `account_log` VALUES ('1', '用户登录日志', '1', '2018-05-18 11:12:17', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('2', '用户登录日志', '1', '2018-05-21 14:43:02', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('3', '用户登录日志', '1', '2018-05-29 15:09:33', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('4', '用户登录日志', '1', '2018-05-29 15:16:55', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('5', '用户登录日志', '1', '2018-05-29 15:34:13', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('6', '用户登录日志', '1', '2018-05-30 09:39:58', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('7', '用户登录日志', '1', '2018-05-31 19:22:41', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('8', '用户登录日志', '1', '2018-05-31 19:22:41', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('9', '用户登录日志', '1', '2018-06-07 13:47:24', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('10', '用户登录日志', '1', '2018-06-08 13:12:11', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('11', '用户登录日志', '1', '2018-06-08 13:13:19', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('12', '用户登录日志', '1', '2018-06-08 14:17:07', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('13', '用户登录日志', '1', '2018-06-08 14:19:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('14', '用户登录日志', '1', '2018-06-08 14:22:48', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('15', '用户登录日志', '1', '2018-06-08 14:23:55', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('16', '用户登录日志', '1', '2018-06-08 14:24:51', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('17', '用户登录日志', '1', '2018-06-08 14:26:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('18', '用户登录日志', '1', '2018-06-08 14:33:06', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('19', '用户登录日志', '1', '2018-06-08 16:35:10', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('20', '用户登录日志', '1', '2018-06-08 16:38:11', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('21', '用户登录日志', '1', '2018-06-08 16:39:58', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('22', '用户登录日志', '1', '2018-06-08 16:53:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('23', '用户登录日志', '1', '2018-06-08 16:57:50', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('24', '用户登录日志', '1', '2018-06-08 16:59:35', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('25', '用户登录日志', '1', '2018-06-08 17:01:38', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('26', '用户登录日志', '1', '2018-06-08 17:06:16', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('27', '用户登录日志', '1', '2018-06-08 17:09:20', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('28', '用户登录日志', '1', '2018-06-08 17:10:09', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('29', '用户登录日志', '1', '2018-06-11 10:36:58', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('30', '用户登录日志', '1', '2018-06-11 10:43:51', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('31', '用户登录日志', '1', '2018-06-11 15:42:55', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('32', '用户登录日志', '1', '2018-06-11 17:29:56', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('33', '用户登录日志', '1', '2018-06-21 16:56:44', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('34', '用户登录日志', '1', '2018-06-21 17:08:38', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('35', '用户登录日志', '1', '2018-06-21 18:13:38', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('36', '用户登录日志', '1', '2018-06-21 19:14:07', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('37', '用户登录日志', '1', '2018-06-21 19:19:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('38', '用户登录日志', '1', '2018-06-26 10:09:06', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('39', '用户登录日志', '1', '2018-06-26 10:45:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('40', '用户登录日志', '1', '2018-06-26 10:46:30', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('41', '用户登录日志', '1', '2018-06-26 11:40:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('42', '用户登录日志', '1', '2018-06-26 11:47:39', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('43', '用户登录日志', '1', '2018-06-26 13:53:00', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('44', '用户登录日志', '1', '2018-06-27 14:29:23', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('45', '用户登录日志', '1', '2018-06-27 14:29:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('46', '用户登录日志', '1', '2018-06-27 14:30:43', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('47', '用户登录日志', '1', '2018-06-27 14:31:17', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('48', '用户登录日志', '1', '2018-06-27 14:32:12', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('49', '用户登录日志', '1', '2018-06-27 14:59:48', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('50', '用户登录日志', '1', '2018-06-28 14:10:25', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('51', '用户登录日志', '1', '2018-06-29 09:30:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('52', '用户登录日志', '1', '2018-06-29 09:33:01', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('53', '用户登录日志', '1', '2018-06-29 09:36:56', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('54', '用户登录日志', '1', '2018-06-29 09:45:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('55', '用户登录日志', '1', '2018-06-29 09:45:30', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('56', '用户登录日志', '1', '2018-06-29 09:49:27', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('57', '用户登录日志', '1', '2018-06-29 10:17:49', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('58', '用户登录日志', '1', '2018-06-29 10:21:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('59', '用户登录日志', '1', '2018-06-29 10:25:04', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('60', '用户登录日志', '1', '2018-06-29 10:25:46', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('61', '用户登录日志', '1', '2018-06-29 10:26:06', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('62', '用户登录日志', '1', '2018-06-29 10:26:24', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('63', '用户登录日志', '1', '2018-06-29 10:27:20', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('64', '用户登录日志', '1', '2018-06-29 10:30:52', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('65', '用户登录日志', '1', '2018-06-29 10:31:02', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('66', '用户登录日志', '1', '2018-06-29 10:32:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('67', '用户登录日志', '1', '2018-06-29 10:33:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('68', '用户登录日志', '1', '2018-06-29 10:34:14', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('69', '用户登录日志', '1', '2018-06-29 10:45:46', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('70', '用户登录日志', '1', '2018-06-29 10:46:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('71', '用户登录日志', '1', '2018-06-29 10:47:49', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('72', '用户登录日志', '1', '2018-06-29 10:48:47', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('73', '用户登录日志', '1', '2018-06-29 10:50:07', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('74', '用户登录日志', '1', '2018-06-29 10:51:19', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('75', '用户登录日志', '1', '2018-06-29 10:53:21', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('76', '用户登录日志', '1', '2018-06-29 10:53:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('77', '用户登录日志', '1', '2018-06-29 10:59:51', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('78', '用户登录日志', '1', '2018-06-29 11:03:41', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('79', '用户登录日志', '1', '2018-06-29 11:07:38', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('80', '用户登录日志', '1', '2018-06-29 11:09:54', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('81', '用户登录日志', '1', '2018-06-29 11:11:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('82', '用户登录日志', '1', '2018-06-29 11:12:01', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('83', '用户登录日志', '1', '2018-06-29 11:12:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('84', '用户登录日志', '1', '2018-06-29 11:13:46', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('85', '用户登录日志', '1', '2018-06-29 11:15:06', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('86', '用户登录日志', '1', '2018-06-29 11:15:25', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('87', '用户登录日志', '1', '2018-06-29 11:15:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('88', '用户登录日志', '1', '2018-06-29 11:16:49', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('89', '用户登录日志', '1', '2018-06-29 11:18:15', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('90', '用户登录日志', '1', '2018-06-29 11:19:09', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('91', '用户登录日志', '1', '2018-06-29 11:19:59', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('92', '用户登录日志', '1', '2018-06-29 11:32:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('93', '用户登录日志', '1', '2018-06-29 11:40:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('94', '用户登录日志', '1', '2018-06-29 11:40:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('95', '用户登录日志', '1', '2018-06-29 11:42:46', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('96', '用户登录日志', '1', '2018-06-29 11:45:46', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('97', '用户登录日志', '1', '2018-06-29 11:47:57', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('98', '用户登录日志', '1', '2018-06-29 11:49:54', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('99', '用户登录日志', '1', '2018-06-29 11:50:28', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('100', '用户登录日志', '1', '2018-06-29 11:51:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('101', '用户登录日志', '1', '2018-06-29 13:10:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('102', '用户登录日志', '1', '2018-06-29 13:13:53', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('103', '用户登录日志', '1', '2018-06-29 13:14:38', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('104', '用户登录日志', '1', '2018-06-29 13:15:16', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('105', '用户登录日志', '1', '2018-06-29 13:16:04', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('106', '用户登录日志', '1', '2018-06-29 13:18:54', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('107', '用户登录日志', '1', '2018-06-29 13:21:01', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('108', '用户登录日志', '1', '2018-06-29 13:26:28', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('109', '用户登录日志', '1', '2018-06-29 13:27:20', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('110', '用户登录日志', '1', '2018-06-29 13:27:52', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('111', '用户登录日志', '1', '2018-06-29 13:33:20', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('112', '用户登录日志', '1', '2018-06-29 13:41:59', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('113', '用户登录日志', '1', '2018-06-29 13:45:32', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('114', '用户登录日志', '1', '2018-06-29 13:46:25', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('115', '用户登录日志', '1', '2018-06-29 13:51:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('116', '用户登录日志', '1', '2018-06-29 13:52:02', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('117', '用户登录日志', '1', '2018-06-29 13:57:31', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('118', '用户登录日志', '1', '2018-06-29 14:00:02', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('119', '用户登录日志', '1', '2018-06-29 14:00:39', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('120', '用户登录日志', '1', '2018-06-29 14:03:53', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('121', '用户登录日志', '1', '2018-06-29 14:04:44', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('122', '用户登录日志', '1', '2018-06-29 14:10:25', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('123', '用户登录日志', '1', '2018-06-29 14:18:19', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('124', '用户登录日志', '1', '2018-06-29 14:37:23', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('125', '用户登录日志', '1', '2018-06-29 14:39:19', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('126', '用户登录日志', '1', '2018-06-29 14:39:35', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('127', '用户登录日志', '1', '2018-06-29 14:40:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('128', '用户登录日志', '1', '2018-06-29 14:40:30', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('129', '用户登录日志', '1', '2018-06-29 14:41:30', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('130', '用户登录日志', '1', '2018-06-29 14:41:57', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('131', '用户登录日志', '1', '2018-06-29 14:44:27', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('132', '用户登录日志', '1', '2018-06-29 14:49:59', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('133', '用户登录日志', '1', '2018-06-29 14:50:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('134', '用户登录日志', '1', '2018-06-29 15:06:12', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('135', '用户登录日志', '1', '2018-06-29 15:12:31', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('136', '用户登录日志', '1', '2018-06-29 15:25:31', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('137', '用户登录日志', '1', '2018-06-29 15:26:43', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('138', '用户登录日志', '1', '2018-06-29 15:27:22', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('139', '用户登录日志', '1', '2018-06-29 15:36:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('140', '用户登录日志', '1', '2018-06-29 15:37:17', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('141', '用户登录日志', '1', '2018-06-29 15:38:24', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('142', '用户登录日志', '1', '2018-06-29 16:04:35', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('143', '用户登录日志', '1', '2018-06-29 16:06:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('144', '用户登录日志', '1', '2018-06-29 16:07:34', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('145', '用户登录日志', '1', '2018-06-29 16:14:55', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('146', '用户登录日志', '1', '2018-06-29 16:15:48', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('147', '用户登录日志', '1', '2018-06-29 16:18:52', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('148', '用户登录日志', '1', '2018-06-29 16:27:37', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('149', '用户登录日志', '1', '2018-06-29 16:35:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('150', '用户登录日志', '1', '2018-06-29 16:36:25', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('151', '用户登录日志', '1', '2018-06-29 16:37:27', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('152', '用户登录日志', '1', '2018-06-29 16:40:26', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('153', '用户登录日志', '1', '2018-06-29 16:41:45', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('154', '用户登录日志', '1', '2018-06-29 16:45:20', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('155', '用户登录日志', '1', '2018-06-29 16:51:05', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('156', '用户登录日志', '1', '2018-06-29 16:54:34', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('157', '用户登录日志', '1', '2018-06-29 16:54:50', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('158', '用户登录日志', '1', '2018-06-29 16:55:41', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('159', '用户登录日志', '1', '2018-06-29 16:56:15', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('160', '用户登录日志', '1', '2018-06-29 16:56:36', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('161', '用户登录日志', '1', '2018-06-29 16:56:55', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('162', '用户登录日志', '1', '2018-06-29 17:02:12', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('163', '用户登录日志', '1', '2018-06-29 17:05:51', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('164', '用户登录日志', '1', '2018-06-29 17:07:18', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('165', '用户登录日志', '1', '2018-06-29 17:08:15', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('166', '用户登录日志', '1', '2018-06-29 17:08:43', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('167', '用户登录日志', '1', '2018-06-29 17:08:51', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('168', '用户登录日志', '1', '2018-06-29 17:09:01', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('169', '用户登录日志', '1', '2018-06-29 17:10:42', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('170', '用户登录日志', '1', '2018-06-29 17:11:02', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('171', '用户登录日志', '1', '2018-06-29 17:13:40', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('172', '用户登录日志', '1', '2018-06-29 17:19:17', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('173', '用户登录日志', '1', '2018-07-02 09:00:41', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('174', '用户登录日志', '1', '2018-07-02 10:28:29', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('175', '用户登录日志', '1', '2018-07-03 10:24:58', '1', '登录成功', '127.0.0.1', '1');
INSERT INTO `account_log` VALUES ('176', '用户登录日志', '1', '2018-07-03 16:36:01', '1', '登录成功', '127.0.0.1', '1');

-- ----------------------------
-- Table structure for apk
-- ----------------------------
DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL COMMENT '上传者的Id',
  `name` varchar(30) DEFAULT NULL,
  `package_name` varchar(255) NOT NULL COMMENT '包名',
  `version` varchar(30) DEFAULT NULL,
  `importance` int(3) DEFAULT '100' COMMENT '重要性 0 - 100 (数值越小越重要)',
  `path` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk
-- ----------------------------
INSERT INTO `apk` VALUES ('47', '1', 'muv_db.sql', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('49', '1', 'sdfsdg', 'com.muv.newx', '1.1.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('50', '1', 'xbd', 'com.muv.newx', '1.19.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('51', '1', 'dhdb', 'com.muv.newx', '2.20.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('52', '1', 'dfger', 'com.muv.newx', '2.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('53', '1', 'dfgdg', 'com.muv.newx', '3.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('54', '1', 'dbervgf', 'com.muv.newx', '9.0.1', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('55', '1', 'dfwe', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('56', '1', 'sgs', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('57', '1', 'wd', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('58', '1', 'sdfcx', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('59', '1', 'sdwegg', 'com.muv.newx', '1.0.0', '4', '/apk/10023B219F291909CE8D2F5C40F27F08', '描述', '1');
INSERT INTO `apk` VALUES ('60', '1', 'app-beijing-release.apk', 'com.beijing', '1.0.0', '4', '/apk/A94204BBC0F9DE35B30E2D64BE3294B5', 'aa', '1');
INSERT INTO `apk` VALUES ('61', '1', 'demo3.apk', 'Demo', '1.2.0', '4', '/apk/8B321C8A5759A4C7FCA97A33B877AB32', '描述', '1');

-- ----------------------------
-- Table structure for apk_plugin
-- ----------------------------
DROP TABLE IF EXISTS `apk_plugin`;
CREATE TABLE `apk_plugin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apk_id` int(11) DEFAULT NULL,
  `plugin_id` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `path` varchar(100) NOT NULL,
  `upload_time` datetime(3) NOT NULL,
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('5', 'HK_Buy22114.PNG', 'B417805AFC3BB5EE12D3F8E83DDCC70A', '/apk/B417805AFC3BB5EE12D3F8E83DDCC70A', '2018-07-03 17:26:14.000', '1');
INSERT INTO `file` VALUES ('6', 'app-beijing-release.apk', 'A94204BBC0F9DE35B30E2D64BE3294B5', '/apk/A94204BBC0F9DE35B30E2D64BE3294B5', '2018-07-03 17:26:55.000', '1');
INSERT INTO `file` VALUES ('7', 'app-beijing-release.apk', 'A94204BBC0F9DE35B30E2D64BE3294B5', '/apk/A94204BBC0F9DE35B30E2D64BE3294B5', '2018-07-03 18:59:07.000', '1');
INSERT INTO `file` VALUES ('8', 'demo3.apk', '8B321C8A5759A4C7FCA97A33B877AB32', '/apk/8B321C8A5759A4C7FCA97A33B877AB32', '2018-07-03 19:00:54.000', '1');

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户操作日志主键',
  `log_name` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `user_id` varchar(30) DEFAULT NULL COMMENT '用户id',
  `api` varchar(255) DEFAULT NULL COMMENT 'api名称',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `succeed` tinyint(4) DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `message` varchar(255) DEFAULT NULL COMMENT '具体消息备注',
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for plugin
-- ----------------------------
DROP TABLE IF EXISTS `plugin`;
CREATE TABLE `plugin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `version` varchar(30) DEFAULT NULL,
  `importance` int(3) DEFAULT '100' COMMENT '重要性 0 - 100 (数值越小越重要)',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plugin
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `code` varchar(30) DEFAULT NULL COMMENT '资源名称',
  `name` varchar(30) DEFAULT NULL COMMENT '资源描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源编码->菜单',
  `uri` varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `type` smallint(4) DEFAULT NULL COMMENT '类型 1:菜单menu 2:资源element(rest-api) 3:资源分类',
  `method` varchar(10) DEFAULT NULL COMMENT '访问方式 GET POST PUT DELETE PATCH',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) DEFAULT '1' COMMENT '状态   1:正常、0：禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源信息表(菜单,资源)';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('101', 'ACCOUNT_LOGIN', '用户登录', '103', '/account/login', '2', 'POST', null, null, null, '1');
INSERT INTO `resource` VALUES ('103', 'GROUP_ACCOUNT', '账户系列', '110', '', '3', 'POST', null, null, null, '1');
INSERT INTO `resource` VALUES ('104', 'USER_MAGE', '用户管理', '-1', '/index/user', '1', 'POST', 'fa fa-user', null, null, '1');
INSERT INTO `resource` VALUES ('106', 'RESOURCE_MAGE', '资源配置', '-1', '', '1', 'POST', 'fa fa-pie-chart', null, null, '1');
INSERT INTO `resource` VALUES ('107', 'MENU_MANAGE', '菜单管理', '106', '/index/menu', '1', 'POST', 'fa fa-th', null, null, '1');
INSERT INTO `resource` VALUES ('109', 'API_MANAGE', 'API管理', '106', '/index/api', '1', null, 'fa fa-share', '2018-04-07 09:40:24', '2018-04-07 09:40:24', '1');
INSERT INTO `resource` VALUES ('110', 'CATEGORY_GROUP', '分类集合(API类别请放入此集合)', '-1', null, '3', null, null, '2018-04-07 14:27:58', '2018-04-07 14:27:58', '1');
INSERT INTO `resource` VALUES ('112', 'ACCOUNT_REGISTER', '用户注册', '103', '/account/register', '2', 'POST', null, '2018-04-07 16:21:45', '2018-04-07 16:21:45', '1');
INSERT INTO `resource` VALUES ('115', 'GROUP_USER', '用户系列', '110', '', '3', 'GET', null, '2018-04-07 16:31:01', '2018-04-07 16:31:01', '1');
INSERT INTO `resource` VALUES ('117', 'ROLE_MANAGE', '角色管理', '106', '/index/role', '1', null, 'fa fa-adjust', '2018-04-08 05:36:31', '2018-04-08 05:36:31', '1');
INSERT INTO `resource` VALUES ('118', 'GROUP_RESOURCE', '资源系列', '110', null, '3', null, null, '2018-04-09 02:29:14', '2018-04-09 02:29:14', '1');
INSERT INTO `resource` VALUES ('119', 'USER_ROLE_APPID', '获取对应用户角色', '115', '/user/role', '2', 'GET', null, '2018-04-12 03:07:22', '2018-04-12 03:07:22', '1');
INSERT INTO `resource` VALUES ('120', 'USER_LIST', '获取用户列表', '115', '/user/list', '2', 'GET', null, '2018-04-12 03:08:30', '2018-04-12 03:08:30', '1');
INSERT INTO `resource` VALUES ('121', 'USER_AUTHORITY_ROLE', '给用户授权添加角色', '115', '/user/authority/role', '2', 'POST', null, '2018-04-12 03:15:56', '2018-04-12 03:15:56', '1');
INSERT INTO `resource` VALUES ('122', 'USER_AUTHORITY_ROLE', '删除已经授权的用户角色', '115', '/user/authority/role', '2', 'DELETE', null, '2018-04-12 03:29:03', '2018-04-12 03:29:03', '1');
INSERT INTO `resource` VALUES ('123', 'RESOURCE_AUTORITYMENU', '获取用户被授权菜单', '118', '/resource/authorityMenu', '2', 'GET', null, '2018-04-12 05:30:03', '2018-04-12 05:30:03', '1');
INSERT INTO `resource` VALUES ('124', 'RESOURCE_MENUS', '获取全部菜单列', '118', '/resource/menus', '2', 'GET', null, '2018-04-12 05:42:46', '2018-04-12 05:42:46', '1');
INSERT INTO `resource` VALUES ('125', 'RESOURCE_MENU', '增加菜单', '118', '/resource/menu', '2', 'POST', null, '2018-04-12 06:15:39', '2018-04-12 06:15:39', '1');
INSERT INTO `resource` VALUES ('126', 'RESOURCE_MENU', '修改菜单', '118', '/resource/menu', '2', 'PUT', null, '2018-04-12 06:16:35', '2018-04-12 06:16:35', '1');
INSERT INTO `resource` VALUES ('127', 'RESOURCE_MENU', '删除菜单', '118', '/resource/menu', '2', 'DELETE', null, '2018-04-12 06:17:18', '2018-04-12 06:17:18', '1');
INSERT INTO `resource` VALUES ('128', 'RESOURCE_API', '获取API list', '118', '/resource/api', '2', 'GET', null, '2018-04-12 06:18:02', '2018-04-12 06:18:02', '1');
INSERT INTO `resource` VALUES ('129', 'RESOURCE_API', '增加API', '118', '/resource/api', '2', 'POST', null, '2018-04-12 06:18:42', '2018-04-12 06:18:42', '1');
INSERT INTO `resource` VALUES ('130', 'RESOURCE_API', '修改API', '118', '/resource/api', '2', 'PUT', null, '2018-04-12 06:19:32', '2018-04-12 06:19:32', '1');
INSERT INTO `resource` VALUES ('131', 'RESOURCE_API', '删除API', '118', '/resource/api', '2', 'DELETE', null, '2018-04-12 06:20:03', '2018-04-12 06:20:03', '1');
INSERT INTO `resource` VALUES ('132', 'GROUP_ROLE', '角色系列', '110', null, '3', null, null, '2018-04-12 06:22:02', '2018-04-12 06:22:02', '1');
INSERT INTO `resource` VALUES ('133', 'ROLE_USER', '获取角色关联用户列表', '132', '/role/user', '2', 'GET', null, '2018-04-12 06:22:59', '2018-04-12 06:22:59', '1');
INSERT INTO `resource` VALUES ('134', 'ROLE_USER', '获取角色未关联用户列表', '132', '/role/user/-/', '2', 'GET', null, '2018-04-12 06:24:09', '2018-04-12 06:24:09', '1');
INSERT INTO `resource` VALUES ('135', 'ROLE_API', '获取角色关联API资源', '132', '/role/api', '2', 'GET', null, '2018-04-12 06:25:32', '2018-04-12 06:25:32', '1');
INSERT INTO `resource` VALUES ('136', 'ROLE_API', '获取角色未关联API资源', '132', '/role/api/-/', '2', 'GET', null, '2018-04-12 06:26:12', '2018-04-12 06:26:12', '1');
INSERT INTO `resource` VALUES ('137', 'ROLE_MENU', '获取角色关联菜单资源', '132', 'role/menu', '2', 'GET', null, '2018-04-12 06:27:20', '2018-04-12 06:27:20', '1');
INSERT INTO `resource` VALUES ('138', 'ROLE_MENU', '获取角色未关联菜单资源', '132', '/role/menu/-/', '2', 'GET', null, '2018-04-12 06:27:56', '2018-04-12 06:27:56', '1');
INSERT INTO `resource` VALUES ('139', 'ROLE_AUTHORITY_RESOURCE', '授权资源给角色', '132', '/role/authority/resource', '2', 'POST', null, '2018-04-12 06:29:45', '2018-04-12 06:29:45', '1');
INSERT INTO `resource` VALUES ('140', 'ROLE_AUTHORITY_RESOURCE', '删除角色的授权资源', '132', '/role/authority/resource', '2', 'DELETE', null, '2018-04-12 06:31:12', '2018-04-12 06:31:12', '1');
INSERT INTO `resource` VALUES ('141', 'ROLE', '获取角色LIST', '132', 'role', '2', 'GET', null, '2018-04-12 06:32:34', '2018-04-12 06:32:34', '1');
INSERT INTO `resource` VALUES ('142', 'ROLE', '添加角色', '132', 'role', '2', 'POST', null, '2018-04-12 06:33:25', '2018-04-12 06:33:25', '1');
INSERT INTO `resource` VALUES ('143', 'USER', '更新角色', '132', 'role', '2', 'PUT', null, '2018-04-12 06:34:27', '2018-04-12 06:34:27', '1');
INSERT INTO `resource` VALUES ('144', 'ROLE', '删除角色', '132', 'role', '2', 'DELETE', null, '2018-04-12 06:35:15', '2018-04-12 06:35:15', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `code` varchar(30) NOT NULL COMMENT '角色编码',
  `description` varchar(30) DEFAULT NULL COMMENT '角色简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) DEFAULT '1' COMMENT '状态   1:正常、0：禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('100', 'admin', '管理员角色', '2018-06-26 14:47:33', '2018-06-26 14:47:33', '1');
INSERT INTO `role` VALUES ('101', 'user', '用户角色', '2018-06-26 09:31:14', '2018-06-26 09:31:54', '1');
INSERT INTO `role` VALUES ('102', 'guest', '访客角色', '2018-06-26 09:31:17', '2018-06-26 09:31:57', '1');
INSERT INTO `role` VALUES ('103', 'anon', '非角色', '2018-06-26 09:32:00', '2018-06-26 09:32:03', '1');
INSERT INTO `role` VALUES ('114', 'Modify', '修改', '2018-06-26 14:55:30', '2018-06-26 14:55:30', '0');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ROLE_ID` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资源角色关联表';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('10', '103', '105', '2018-04-01 12:50:19', '2018-04-01 12:50:19', '1');
INSERT INTO `role_resource` VALUES ('21', '102', '102', '2018-04-09 21:09:09', '2018-04-09 21:09:09', '1');
INSERT INTO `role_resource` VALUES ('23', '103', '101', '2018-04-09 21:51:39', '2018-04-09 21:51:39', '1');
INSERT INTO `role_resource` VALUES ('24', '103', '102', '2018-04-09 21:51:43', '2018-04-09 21:51:43', '1');
INSERT INTO `role_resource` VALUES ('25', '103', '103', '2018-04-09 21:51:46', '2018-04-09 21:51:46', '1');
INSERT INTO `role_resource` VALUES ('26', '103', '112', '2018-04-09 21:51:49', '2018-04-09 21:51:49', '1');
INSERT INTO `role_resource` VALUES ('27', '101', '112', '2018-04-09 22:21:02', '2018-04-09 22:21:02', '1');
INSERT INTO `role_resource` VALUES ('28', '101', '103', '2018-04-09 22:21:06', '2018-04-09 22:21:06', '1');
INSERT INTO `role_resource` VALUES ('29', '100', '102', '2018-04-09 22:25:09', '2018-04-09 22:25:09', '1');
INSERT INTO `role_resource` VALUES ('30', '101', '101', '2018-04-09 22:39:28', '2018-04-09 22:39:28', '1');
INSERT INTO `role_resource` VALUES ('31', '103', '100', '2018-04-09 22:45:00', '2018-04-09 22:45:00', '1');
INSERT INTO `role_resource` VALUES ('32', '103', '104', '2018-04-09 22:46:26', '2018-04-09 22:46:26', '1');
INSERT INTO `role_resource` VALUES ('33', '103', '106', '2018-04-09 22:46:28', '2018-04-09 22:46:28', '1');
INSERT INTO `role_resource` VALUES ('34', '103', '107', '2018-04-09 22:46:31', '2018-04-09 22:46:31', '1');
INSERT INTO `role_resource` VALUES ('35', '103', '109', '2018-04-09 22:46:34', '2018-04-09 22:46:34', '1');
INSERT INTO `role_resource` VALUES ('36', '103', '116', '2018-04-09 22:46:37', '2018-04-09 22:46:37', '1');
INSERT INTO `role_resource` VALUES ('37', '103', '117', '2018-04-09 22:46:43', '2018-04-09 22:46:43', '1');
INSERT INTO `role_resource` VALUES ('38', '104', '101', '2018-04-09 22:49:46', '2018-04-09 22:49:46', '1');
INSERT INTO `role_resource` VALUES ('39', '104', '102', '2018-04-09 22:49:52', '2018-04-09 22:49:52', '1');
INSERT INTO `role_resource` VALUES ('40', '104', '103', '2018-04-09 22:49:55', '2018-04-09 22:49:55', '1');
INSERT INTO `role_resource` VALUES ('41', '100', '103', '2018-04-09 22:51:56', '2018-04-09 22:51:56', '1');
INSERT INTO `role_resource` VALUES ('42', '102', '101', '2018-04-11 09:35:37', '2018-04-11 09:35:37', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(18) NOT NULL AUTO_INCREMENT COMMENT 'uid,主键',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码(MD5(密码+盐))',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `real_name` varchar(30) DEFAULT NULL COMMENT '用户真名',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码(唯一)',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件地址(唯一)',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别(1.男 2.女)',
  `status` tinyint(4) DEFAULT NULL COMMENT '账户状态(1.正常 2.锁定 3.删除 4.非法)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_where` tinyint(4) DEFAULT NULL COMMENT '创建来源(1.web 2.android 3.ios 4.win 5.macos 6.ubuntu)',
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'CFABFE350DE49BC90D24553FF6CEBC0E', null, 'bqgvis', null, null, null, null, null, '1', '2018-03-20 10:24:48', '2018-03-20 02:24:48', null, '1');
INSERT INTO `user` VALUES ('2', 'newx', 'EAE91F83C777FBDBC91BB0469099F06C', null, 'gfgzgk', null, null, null, null, null, '1', '2018-05-10 16:36:44', '2018-05-10 16:36:46', null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表主键',
  `user_id` varchar(30) NOT NULL COMMENT '用户UID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `USER_ID` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '100', '2018-06-26 10:48:00', '2018-06-26 10:48:00', '1');
INSERT INTO `user_role` VALUES ('2', '1', '101', '2018-06-26 10:58:00', '2018-06-26 10:58:00', '1');

-- ----------------------------
-- View structure for view_user_role
-- ----------------------------
DROP VIEW IF EXISTS `view_user_role`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_user_role` AS select `role`.`description` AS `description`,`role`.`code` AS `code`,`user_role`.`role_id` AS `role_id`,`user_role`.`user_id` AS `user_id` from (`user_role` join `role`) where ((`user_role`.`role_id` = `role`.`id`) and (`role`.`enable` = 1) and (`user_role`.`enable` = 1)) ;
