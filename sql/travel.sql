SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ctime` timestamp NULL DEFAULT NULL,
  `mid` int NULL DEFAULT NULL,
  `ccontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 1, 'zhangsan', '2022-12-26 08:28:12', 1, '景色真不错');
INSERT INTO `t_comment` VALUES (2, 1, 'zhangsan', '2022-12-26 11:14:26', 1, '春暖花开，景色不错');
INSERT INTO `t_comment` VALUES (3, 2, 'zhangsi', '2022-12-26 11:17:29', 1, '风景优美');
INSERT INTO `t_comment` VALUES (4, 1, 'zhangsan', '2023-01-03 15:48:35', 1, '一一');

-- ----------------------------
-- Table structure for t_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamic`;
CREATE TABLE `t_dynamic`  (
  `dynamicid` int NOT NULL AUTO_INCREMENT,
  `dcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishtime` timestamp NULL DEFAULT NULL,
  `publishid` int NULL DEFAULT NULL,
  `publishname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dynamicid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_dynamic
-- ----------------------------
INSERT INTO `t_dynamic` VALUES (1, '好看就看接发速度快', '2022-06-22 08:59:59', 1, 'zhangsan');
INSERT INTO `t_dynamic` VALUES (2, '宏观经济和广泛的', '2022-10-11 09:33:16', 1, 'zhangsan');
INSERT INTO `t_dynamic` VALUES (3, '所得税法法规和健康科技和规范', '2022-10-13 09:33:38', 1, 'zhangsan');
INSERT INTO `t_dynamic` VALUES (4, '的非官方个规模', '2023-01-04 09:34:21', 3, 'lisi');
INSERT INTO `t_dynamic` VALUES (13, '方大哥大哥都是尬', '2022-03-15 15:53:41', 4, 'lucy');
INSERT INTO `t_dynamic` VALUES (14, '反倒是煽风点火史蒂夫\\(^o^)/~', '2021-06-30 15:54:13', 5, 'jack');
INSERT INTO `t_dynamic` VALUES (15, '放几天假哟一挺方便', '2021-12-23 15:54:55', 3, 'lisi');

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `friendid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES (1, 1, 3);
INSERT INTO `t_friend` VALUES (2, 1, 5);
INSERT INTO `t_friend` VALUES (4, 3, 1);
INSERT INTO `t_friend` VALUES (6, 4, 1);
INSERT INTO `t_friend` VALUES (7, 5, 1);

-- ----------------------------
-- Table structure for t_guide
-- ----------------------------
DROP TABLE IF EXISTS `t_guide`;
CREATE TABLE `t_guide`  (
  `gid` int NOT NULL AUTO_INCREMENT,
  `gtitle` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `crowd` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tid` int NULL DEFAULT NULL,
  `gintroduce` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `equipment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tips` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `glocation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT NULL,
  `publishid` int UNSIGNED NULL DEFAULT NULL,
  `publishname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_guide
-- ----------------------------
INSERT INTO `t_guide` VALUES (1, '一起去上海', '2023.5.2', '你', '/upload/guide/1673150041289wallhaven-e7ek7k.jpg', 1, '鼎折覆餗', '伞，衣物', '注意安全', '上海', '2023-01-03 16:16:47', 3, 'lisi');
INSERT INTO `t_guide` VALUES (2, '一起去深圳', '2023.6.3', '所有人', '/upload/guide/1673150041289wallhaven-e7ek7k.jpg', 2, '发送嘎嘎', '伞，衣物', '注意安全', '深圳', '2022-12-13 16:20:15', 3, 'lisi');
INSERT INTO `t_guide` VALUES (3, '一起去云上', '2023.9.9', '所有人', '/upload/guide/1673150041289wallhaven-e7ek7k.jpg', 1, '诶礼物比', '伞，衣物', '注意安全', '云上', '2023-01-08 11:54:01', 3, 'lisi');
INSERT INTO `t_guide` VALUES (4, '一起去西藏', '2024.8.9', '信徒', '/upload/guide/16731663063111656824640368.jpg', 2, 'ububub', '氧气', '安全', '西藏', '2023-01-08 16:25:06', 3, 'lisi');

-- ----------------------------
-- Table structure for t_guide_type
-- ----------------------------
DROP TABLE IF EXISTS `t_guide_type`;
CREATE TABLE `t_guide_type`  (
  `tid` int NOT NULL AUTO_INCREMENT,
  `tname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tintroduce` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_guide_type
-- ----------------------------
INSERT INTO `t_guide_type` VALUES (1, '主题游', NULL, NULL);
INSERT INTO `t_guide_type` VALUES (2, '时令游', NULL, NULL);

-- ----------------------------
-- Table structure for t_memory
-- ----------------------------
DROP TABLE IF EXISTS `t_memory`;
CREATE TABLE `t_memory`  (
  `mid` int NOT NULL AUTO_INCREMENT,
  `tid` int NULL DEFAULT NULL,
  `mtitle` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishid` int NULL DEFAULT NULL,
  `publishname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishtime` timestamp NULL DEFAULT NULL,
  `mcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_memory
-- ----------------------------
INSERT INTO `t_memory` VALUES (1, 1, '面朝大海1', 1, 'zhangsan', '2022-12-21 17:42:59', '面朝大海，春暖花开', '/upload/memory/memory1.jpg\n');
INSERT INTO `t_memory` VALUES (2, 1, '面朝大海2', 1, 'zhansgan', '2022-12-19 17:43:38', '面朝大海2', '/upload/memory/memory1.jpg\n');
INSERT INTO `t_memory` VALUES (3, 2, '古镇时光1', 1, 'zhangsan', '2022-12-21 17:59:46', '古镇时光，很悠闲', '/upload/memory/memory2.jpg\n');
INSERT INTO `t_memory` VALUES (4, 2, '古镇时光2', 1, 'zhangsan', '2022-12-21 18:00:39', '古镇时光，很悠闲2', '/upload/memory/memory3.jpg\n');
INSERT INTO `t_memory` VALUES (5, 2, '古镇时光3', 1, 'zhangsan', '2022-12-21 18:00:39', '古镇时光，很悠闲3', '/upload/memory/memory4.jpg\n');
INSERT INTO `t_memory` VALUES (6, 2, '古镇时光4', 1, 'zhangsan', '2022-12-21 18:00:39', '古镇时光，很悠闲4', '/upload/memory/memory1.jpg\n');
INSERT INTO `t_memory` VALUES (7, 2, '古镇时光5', 1, 'zhangsan', '2022-12-21 18:00:39', '古镇时光，很悠闲5', '/upload/memory/memory2.jpg\n');
INSERT INTO `t_memory` VALUES (8, 3, '吃货血拼1', 1, 'zhangsan', '2022-12-21 18:02:02', '脑子里的美食地图', '/upload/memory/memory3.jpg\n');
INSERT INTO `t_memory` VALUES (9, 4, '户外撒野1', 1, 'zhangsan', '2022-12-21 18:02:02', '户外很刺激', '/upload/memory/memory4.jpg\n');
INSERT INTO `t_memory` VALUES (10, 3, '吃货血拼2', 1, 'zhangsan', '2022-12-25 11:36:46', '脑子里的美食地图2', '/upload/memory/memory1.jpg\n');
INSERT INTO `t_memory` VALUES (11, 4, '户外撒野2', 1, 'zhangsan', '2022-12-25 11:36:46', '户外很刺激2', '/upload/memory/memory2.jpg\n');
INSERT INTO `t_memory` VALUES (12, 3, '吃货血拼3', 1, 'zhangsan', '2022-12-25 11:36:46', '脑子里的美食地图3', '/upload/memory/memory3.jpg\n');
INSERT INTO `t_memory` VALUES (13, 4, '户外撒野3', 1, 'zhangsan', '2022-12-25 11:36:46', '户外很刺激3', '/upload/memory/memory1.jpg\n');
INSERT INTO `t_memory` VALUES (14, 3, '吃货血拼4', 1, 'zhangsan', '2022-12-25 11:36:46', '脑子里的美食地图4', '/upload/memory/memory2.jpg\n');
INSERT INTO `t_memory` VALUES (15, 4, '户外撒野4', 1, 'zhangsan', '2022-12-25 11:36:46', '户外很刺激4', '/upload/memory/memory3.jpg\n');
INSERT INTO `t_memory` VALUES (16, 3, '吃货血拼5', 1, 'zhangsan', '2022-12-25 11:36:46', '脑子里的美食地图5', '/upload/memory/memory4.jpg\n');
INSERT INTO `t_memory` VALUES (17, 4, '户外撒野5', 1, 'zhangsan', '2022-12-25 11:36:46', '户外很刺激5', '/upload/memory/memory1.jpg\n');

-- ----------------------------
-- Table structure for t_memory_type
-- ----------------------------
DROP TABLE IF EXISTS `t_memory_type`;
CREATE TABLE `t_memory_type`  (
  `tid` int NOT NULL AUTO_INCREMENT,
  `tname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tintroduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_memory_type
-- ----------------------------
INSERT INTO `t_memory_type` VALUES (1, '面朝大海', NULL, '2022-12-21 17:42:20');
INSERT INTO `t_memory_type` VALUES (2, '古镇时光', NULL, '2022-12-21 17:42:24');
INSERT INTO `t_memory_type` VALUES (3, '吃货血拼', NULL, '2022-12-21 17:57:34');
INSERT INTO `t_memory_type` VALUES (4, '户外撒野', NULL, '2022-12-21 17:57:34');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `nid` int NOT NULL AUTO_INCREMENT,
  `ntitle` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ncontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishtime` timestamp NULL DEFAULT NULL,
  `publishid` int NULL DEFAULT NULL,
  `publishname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_together
-- ----------------------------
DROP TABLE IF EXISTS `t_together`;
CREATE TABLE `t_together`  (
  `togetherid` int NOT NULL AUTO_INCREMENT,
  `publishid` int NULL DEFAULT NULL,
  `publishname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishtime` timestamp NULL DEFAULT NULL,
  `togethertitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `starttime` timestamp NULL DEFAULT NULL,
  `endtime` timestamp NULL DEFAULT NULL,
  `togetherdetail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `togethermoblie` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`togetherid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_together
-- ----------------------------
INSERT INTO `t_together` VALUES (1, 2, 'lisi', '2022-12-01 09:13:45', '一起去兰州', '兰州', '2022-12-26 09:14:07', '2023-03-03 09:14:20', '羊肉泡馍', '123123123', '/upload/together/16724579434631656824469612.jpg');
INSERT INTO `t_together` VALUES (2, 1, 'zhangsan', '2020-08-28 09:16:25', '一起去敦煌', '敦煌', '2022-03-16 09:16:59', '2023-04-18 09:17:05', '莫高窟', '456456456', '/upload/together/16726257499011656824349305.jpg');
INSERT INTO `t_together` VALUES (3, 1, 'zhangsan', '2021-09-14 09:17:55', '一起去云南', '云南', '2022-03-24 09:18:23', '2023-03-01 09:18:28', '西双版纳', '789789789', '/upload/together/16724579434631656824469612.jpg');
INSERT INTO `t_together` VALUES (6, 1, 'zhangsan', '2023-01-02 10:15:49', '一起去天堂', '天堂', '2023-01-19 00:00:00', '2023-01-20 00:00:00', '哎呀呀呀呀', '4444444444', '/upload/together/16726264815558.jpg');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `location` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `QQ` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hobby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `headimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `registertime` timestamp NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `isFace` int NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'zhangsan', '123', '34354@qq.com', '1', 1, '2000-09-09', '北京', '123456789', '13248888888', '篮球', '个人爱好丰富', '/upload/head/16733465463311656824396795.jpg', '2022-12-21 17:15:38', 1, 1);
INSERT INTO `t_user` VALUES (2, 'zhangsi', '123', NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL, '/upload/head/head2.jpg\n', NULL, 1, NULL);
INSERT INTO `t_user` VALUES (3, 'lisi', '123', NULL, '2', 1, '2002-06-05', '云南', '8888888888', '31619161', '羽毛球', NULL, '/upload/head/head1.jpg', NULL, 2, NULL);
INSERT INTO `t_user` VALUES (4, 'lucy', '123', NULL, '1', 1, '2000-06-21', '西藏', '77777777777', '1231231', '乒乓球', NULL, '/upload/head/head1.jpg', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (5, 'jack', '123456', '121@qq.com', '2', 1, '2001-07-12', '兰州', '222222222', '12348486', '篮球', NULL, '/upload/head/head1.jpg', NULL, 1, NULL);
INSERT INTO `t_user` VALUES (6, '济苦怜', '123456', 'dgsdg228@163.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_user` VALUES (7, 'dnwhy', '123456', 'dfdaljglds@qq.com', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
