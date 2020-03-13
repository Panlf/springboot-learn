SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permName` varchar(50) DEFAULT NULL,
  `permTag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '产品查询', 'ROLE_LIST_PRODUCT');
INSERT INTO `sys_permission` VALUES ('2', '产品添加', 'ROLE_ADD_PRODUCT');
INSERT INTO `sys_permission` VALUES ('3', '产品修改', 'ROLE_UPDATE_PRODUCT');
INSERT INTO `sys_permission` VALUES ('4', '产品删除', 'ROLE_DELETE_PRODUCT');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '普通用户');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) DEFAULT NULL,
  `perm_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL COMMENT '创建日期',
  `lastLogintime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `enabled` int(5) DEFAULT NULL COMMENT '是否可用',
  `accountNonExpired` int(5) DEFAULT NULL COMMENT '是否过期',
  `accountNonLocked` int(5) DEFAULT NULL COMMENT '是否锁定',
  `credentialsNonExpired` int(5) DEFAULT NULL COMMENT '证书是否过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zs', '张三', '$2a$10$SGUc9GBEi5GClwwWNjSfv.6dzrPzub/XmbErP/i95SFfb9Q.Zhhz2', '2019-12-31 15:00:31', '2019-12-31 15:00:34', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'ls', '李四', '$2a$10$SGUc9GBEi5GClwwWNjSfv.6dzrPzub/XmbErP/i95SFfb9Q.Zhhz2', '2019-12-31 15:00:55', '2019-12-31 15:00:59', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
