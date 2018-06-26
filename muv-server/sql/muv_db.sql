/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : muv_db

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-10 17:21:59
*/
DROP DATABASE IF EXISTS muv_db;
CREATE DATABASE muv_db DEFAULT CHARACTER SET utf8;
USE muv_db;

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='登录注册登出记录';

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

-- ----------------------------
-- Table structure for apk
-- ----------------------------
DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL COMMENT '上传者的Id',
  `name` varchar(30) DEFAULT NULL,
  `version` varchar(30) DEFAULT NULL,
  `importance` int(3) DEFAULT '100' COMMENT '重要性 0 - 100 (数值越小越重要)',
  `path` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk
-- ----------------------------

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
INSERT INTO `role` VALUES ('100', 'role_admin', '管理员角色', '2018-06-26 14:47:33', '2018-06-26 14:47:33', '1');
INSERT INTO `role` VALUES ('101', 'role_user', '用户角色', '2018-06-26 09:31:14', '2018-06-26 09:31:54', '1');
INSERT INTO `role` VALUES ('102', 'role_guest', '访客角色', '2018-06-26 09:31:17', '2018-06-26 09:31:57', '1');
INSERT INTO `role` VALUES ('103', 'role_anon', '非角色', '2018-06-26 09:32:00', '2018-06-26 09:32:03', '1');
INSERT INTO `role` VALUES ('114', 'List', '修改', '2018-06-26 14:55:30', '2018-06-26 14:55:30', '0');

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
