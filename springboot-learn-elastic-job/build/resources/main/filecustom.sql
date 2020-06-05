/*
Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-02 17:50:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for filecustom
-- ----------------------------
DROP TABLE IF EXISTS `filecustom`;
CREATE TABLE `filecustom` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `backup` int(2) NOT NULL DEFAULT '0' COMMENT '0 未备份 1 已备份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filecustom
-- ----------------------------
INSERT INTO `filecustom` VALUES ('20191202001', '1.txt', 'text', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202002', '2.txt', 'text', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202003', '3.txt', 'text', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202004', '4.jpg', 'image', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202005', '5.jpg', 'image', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202006', '6.jpg', 'image', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202007', '7.jpg', 'image', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202008', '8.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202009', '9.jpg', 'image', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202010', '10.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202011', '11.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202012', '12.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202013', '13.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202014', '14.mp4', 'video', '/20191202', '0');
INSERT INTO `filecustom` VALUES ('20191202015', '15.txt', 'text', '/20191202', '0');
