/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2019-05-12 21:50:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `bid` int(11) NOT NULL,
  `bname` varchar(255) default NULL,
  `account` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  PRIMARY KEY  (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', '包包商家', 'admin1', '1');
INSERT INTO `business` VALUES ('2', '酒家', 'admin2', '123123');
INSERT INTO `business` VALUES ('3', '服装商家', 'admin3', '123123');
INSERT INTO `business` VALUES ('4', '香水店商家', 'admin4', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL auto_increment,
  `cname` varchar(255) default NULL,
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '包包类');
INSERT INTO `category` VALUES ('2', '食品类');
INSERT INTO `category` VALUES ('3', '服装类');
INSERT INTO `category` VALUES ('4', '香水类');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(255) NOT NULL,
  `number` int(11) default NULL,
  `price` double(10,2) default NULL,
  `total` double(11,0) default NULL,
  `name` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `addr` varchar(50) default NULL,
  `time` datetime default NULL,
  `state` int(11) default NULL,
  `channel` int(11) default NULL,
  `beizhu` varchar(255) default NULL,
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY  (`oid`),
  KEY `FKC3DF62E5AA3D9C7` (`uid`),
  KEY `order_pid` (`pid`),
  KEY `order_sid` (`state`),
  KEY `orders_b` (`bid`),
  CONSTRAINT `orders_b` FOREIGN KEY (`bid`) REFERENCES `business` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_p` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_u` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0438c97711b24cb6bab190f23d986d6d', '7', '10.00', '166', '李四', '12312424312', '广西钦州市', '2019-03-29 23:22:19', '1', '0', '无', '2', '1', '1');
INSERT INTO `orders` VALUES ('12711c30db8448b490dc1a41fca0074c', '4', '8113.00', '32587', '李四', '12312424312', '广西钦州市', '2019-03-29 23:32:58', '1', '0', '无', '2', '28', '3');
INSERT INTO `orders` VALUES ('1d5709670dad4ef7a6493bd992619a0e', '1', '670.00', '111', '李武', '12525553324', '广西', '2019-04-07 12:28:06', '1', '2', '无', '10', '41', '4');
INSERT INTO `orders` VALUES ('25986fb2a56744d78d75c172dbc41394', '1', '670.00', '1', '李四', '12312424312', '广西钦州市', '2019-03-29 23:56:03', '2', '2', '无', '2', '41', '4');
INSERT INTO `orders` VALUES ('51e4b442535b4f428d82ad0bab97f66b', '1', '1090.00', '145', '王六', '12345678901', '广西钦州市', '2019-03-29 23:46:12', '1', '1', '无', '6', '45', '4');
INSERT INTO `orders` VALUES ('5ce7744a60954a918aa116c3c31d61e5', '1', '4423.00', '4447', '李四', '12312424312', '广西钦州市', '2019-03-29 23:32:26', '1', '0', '无', '2', '4', '1');
INSERT INTO `orders` VALUES ('6879b66f41e4443fa722dfac686257aa', '1', '2400.00', '2424', '李四', '12312424312', '广西钦州市', '2019-03-29 23:24:29', '1', '0', '无', '2', '3', '1');
INSERT INTO `orders` VALUES ('7b4e44dfb91f4442ba8d2f79d7539ecc', '1', '10.00', '10', '李四', '12312424312', '广西钦州市', '2019-03-29 23:13:24', '1', '0', '无', '2', '1', '1');
INSERT INTO `orders` VALUES ('be80988c7eb04892bccefcd79bcca067', '8', '4423.00', '35492', '李四', '12312424312', '广西钦州市', '2019-03-29 23:32:42', '1', '0', '无', '2', '4', '1');
INSERT INTO `orders` VALUES ('d7865b47d6134e248dfc6e1992a8a816', '1', '1090.00', '1135', '李四', '12312424312', '广西钦州市', '2019-03-29 23:20:07', '3', '0', '无', '2', '45', '4');
INSERT INTO `orders` VALUES ('e13a1f6bd4554aa28f15b4d997b1ca6b', '1', '8113.00', '8158', '李四', '12312424312', '广西钦州市', '2019-03-29 23:31:54', '1', '0', '无', '2', '28', '3');
INSERT INTO `orders` VALUES ('e13e28cc940348ebbdea4323586b9600', '1', '8113.00', '8158', '李四', '12312424312', '广西钦州市', '2019-03-29 23:32:04', '1', '0', '无', '2', '28', '3');
INSERT INTO `orders` VALUES ('ea6c6e599d864ad3b448f36c30223ed1', '1', '1090.00', '145', '李四', '12312424312', '广西钦州市', '2019-03-29 23:49:11', '1', '1', '无', '2', '45', '4');
INSERT INTO `orders` VALUES ('ff522cfb1dc94c38b897a1ab8fe41513', '1', '99.00', '99', '李武', '12525553324', '广西', '2019-04-07 12:27:24', '1', '0', '无', '10', '12', '2');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int(11) NOT NULL auto_increment,
  `pname` varchar(255) default NULL,
  `price` double(255,2) default NULL,
  `image` varchar(255) default NULL,
  `details` varchar(255) default NULL,
  `hot` int(11) default NULL,
  `pdate` datetime default NULL,
  `taxation` double(255,2) default NULL,
  `postage` double(255,2) default NULL,
  `repertory` int(11) default NULL,
  `sales` int(11) default NULL,
  `source` varchar(255) default NULL,
  `wholesale` int(11) default NULL,
  `cid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY  (`pid`),
  KEY `FKcxmxpfdetdqdqm69d4cgbhotv` (`cid`),
  KEY `product_b` (`bid`),
  CONSTRAINT `product_b` FOREIGN KEY (`bid`) REFERENCES `business` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_c` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '斜挎包', '10.00', '/shop/images/baobao/baobao1 .png', 'Madewell Core Flap 斜挎包', '1', '2018-08-08 11:10:40', '12.00', '12.00', '100', '78', '俄罗斯', '1', '1', '1');
INSERT INTO `product` VALUES ('2', '迷你斜背包', '3820.00', '/shop/images/baobao/baobao2.png', 'Furla Metropolis 迷你斜背包', '0', '2017-06-01 11:26:12', '12.00', '12.00', '100', '30', '法国', '0', '1', '1');
INSERT INTO `product` VALUES ('3', '迷你斜挎包', '2400.00', '/shop/images/baobao/baobao3.png', 'Madewell Core 迷你斜挎包', '0', '2017-06-01 11:26:12', '12.00', '12.00', '148', '61', '爱尔兰', '1', '1', '1');
INSERT INTO `product` VALUES ('4', '小包', '4423.00', '/shop/images/baobao/baobao4.png', 'Maryam Nassir Zadeh Marlow 小包', '0', '2019-03-16 11:26:12', '12.00', '12.00', '109', '24', '法国', '0', '1', '1');
INSERT INTO `product` VALUES ('5', '迷你 Oslo 包', '3835.00', '/shop/images/baobao/baobao5.png', 'DeMellier 迷你 Oslo 包', '0', '2018-11-06 11:46:54', '12.00', '12.00', '26', '10', '冰岛', '1', '1', '1');
INSERT INTO `product` VALUES ('6', '斜挎包', '1995.00', '/shop/images/baobao/baobao6.png', 'Botkier Cobble Hill  斜挎包', '0', '2018-11-06 11:46:54', '36.00', '23.00', '26', '10', '爱尔兰', '0', '1', '1');
INSERT INTO `product` VALUES ('7', '澳洲红酒', '450.00', '/shop/images/jiu/hongjiu.png', '奔富澳洲奔富BIN128螺旋盖干红葡萄酒2012双支礼盒红酒', '1', '2019-02-13 18:51:46', '30.00', '15.00', '500', '231', '法国', '1', '2', '2');
INSERT INTO `product` VALUES ('8', '葡萄酒', '199.00', '/shop/images/jiu/putaojiu.png', '奔富澳洲奔富BIN128螺旋盖干红葡萄酒2012 原瓶进口', '0', '2017-06-01 11:26:12', '0.00', '0.00', '198', '31', '爱尔兰', '1', '2', '2');
INSERT INTO `product` VALUES ('9', '葡萄酒2013', '88.00', '/shop/images/jiu/putaojiu2.png', '奔富奔富Penfolds蔻兰山赤霞珠干红葡萄酒2013 红酒', '0', '2017-06-01 11:26:12', '0.00', '0.00', '60', '20', '俄罗斯', '0', '2', '2');
INSERT INTO `product` VALUES ('10', '杰卡斯梅洛干红葡萄酒', '388.00', '/shop/images/jiu/putaojiu3.png', '杰卡斯澳大利亚原瓶进口 杰卡斯梅洛干红葡萄酒六支', '0', '2017-06-01 11:26:12', '0.00', '15.00', '100', '30', '爱尔兰', '1', '2', '2');
INSERT INTO `product` VALUES ('11', '本威士忌 750', '100.00', '/shop/images/jiu/Whisky.png', '威凤凰美国进口洋酒 威凤凰经典波本威士忌 Whisky 750', '0', '2017-06-01 11:26:12', '0.00', '15.00', '150', '60', '法国', '0', '2', '2');
INSERT INTO `product` VALUES ('12', '卓尔白兰地', '99.00', '/shop/images/jiu/bailandi.png', '卓尔包邮 洋酒 巴西 原装进口 卓尔白兰地配制酒 D', '0', '2019-03-16 11:26:12', '0.00', '0.00', '121', '13', '冰岛', '0', '2', '2');
INSERT INTO `product` VALUES ('13', '本威士忌 75', '100.00', '/shop/images/jiu/Whisky2.png', '威凤凰美国进口洋酒 威凤凰美国甜心蜂蜜波本威士忌 75', '0', '2017-06-01 11:26:12', '30.00', '15.00', '26', '10', '俄罗斯', '1', '2', '2');
INSERT INTO `product` VALUES ('14', '冰葡萄酒', '399.00', '/shop/images/jiu/putaojiu4.png', '云惜官方旗舰店Icewine 原瓶进口vqa冰葡萄酒', '0', '2019-03-11 11:26:12', '0.00', '0.00', '26', '10', '法国', '0', '2', '2');
INSERT INTO `product` VALUES ('15', ' 冰酒 ', '699.00', '/shop/images/jiu/bingjiu.png', '云惜官方旗舰店 土豪金礼盒 冰酒 加拿大进口VQA认证', '0', '2017-06-01 11:26:12', '30.00', '15.00', '500', '231', '冰岛', '0', '2', '2');
INSERT INTO `product` VALUES ('16', '拉菲', '278.00', '/shop/images/jiu/lafei.png', '拉菲专卖拉菲红酒巴斯克特级藏酿干红葡萄酒', '0', '2017-06-01 11:26:12', '30.00', '15.00', '200', '30', '爱尔兰', '1', '2', '2');
INSERT INTO `product` VALUES ('17', '金龙舌兰酒', '105.00', '/shop/images/jiu/jinlongshelan.png', '洋酒奥美加金龙舌兰酒750ML', '1', '2017-06-01 11:26:12', '30.00', '15.00', '6', '10', '俄罗斯', '0', '2', '2');
INSERT INTO `product` VALUES ('18', '伏特加', '132.00', '/shop/images/jiu/futejia.png', '洋酒ABSOLUT VODKA 绝对伏特加覆盆莓味 包邮', '0', '2019-03-11 11:26:12', '0.00', '0.00', '20', '10', '法国', '1', '2', '2');
INSERT INTO `product` VALUES ('19', '白兰地 ', '99.00', '/shop/images/jiu/bailandi2.png', '轩尼诗XO干邑白兰地 50ml', '0', '2017-06-01 11:26:12', '30.00', '15.00', '100', '12', '法国', '0', '2', '2');
INSERT INTO `product` VALUES ('20', '针织毛衣', '8253.00', '/shop/images/yifu/fuzhuang2.png', '弹力提花针织毛衣', '0', '2019-03-16 11:26:12', '30.00', '15.00', '150', '10', '法国', '1', '3', '3');
INSERT INTO `product` VALUES ('21', '皮革围裹式半身裙', '2456.00', '/shop/images/yifu/fuzhuang3.png', '不对称人造皮革围裹式半身裙', '0', '2017-06-01 11:26:12', '0.00', '0.00', '123', '10', '俄罗斯', '0', '3', '3');
INSERT INTO `product` VALUES ('22', '西装式外套', '24036.00', '/shop/images/yifu/fuzhuang4.png', '羊毛华达呢西装式外套', '0', '2018-11-06 11:46:54', '0.00', '0.00', '26', '231', '芬兰', '0', '3', '3');
INSERT INTO `product` VALUES ('23', '半身裙', '8036.00', '/shop/images/yifu/fuzhuang5.png', '羽毛边饰真丝乔其纱半身裙', '0', '2018-11-06 11:46:54', '30.00', '15.00', '26', '30', '芬兰', '1', '3', '3');
INSERT INTO `product` VALUES ('24', '西装外套', '8346.00', '/shop/images/yifu/fuzhuang6.png', '卡迪面料西装外套', '1', '2018-11-06 11:46:54', '30.00', '15.00', '500', '20', '英国', '1', '3', '3');
INSERT INTO `product` VALUES ('25', '丝缎西装外套', '8235.00', '/shop/images/yifu/fuzhuang7.png', 'Amandine 大廓形丝缎西装外套', '0', '2018-11-06 11:46:54', '0.00', '0.00', '200', '30', '波兰', '0', '3', '3');
INSERT INTO `product` VALUES ('26', '喇叭裤', '1502.00', '/shop/images/yifu/fuzhuang8.png', '绉纱喇叭裤', '0', '2018-11-06 11:46:54', '30.00', '15.00', '60', '60', '德国', '0', '3', '3');
INSERT INTO `product` VALUES ('27', '羊毛毛衣', '4836.00', '/shop/images/yifu/fuzhuang9.png', '美利奴羊毛毛衣', '0', '2018-11-06 11:46:54', '30.00', '15.00', '100', '12', '瑞典', '1', '3', '3');
INSERT INTO `product` VALUES ('28', '高领毛衣', '8113.00', '/shop/images/yifu/fuzhuang10.png', '羊绒高领毛衣', '0', '2019-03-16 11:46:54', '30.00', '15.00', '139', '17', '德国', '0', '3', '3');
INSERT INTO `product` VALUES ('29', '风衣', '6451.00', '/shop/images/yifu/fuzhuang11.png', '配腰带软壳面料风衣', '0', '2018-11-06 11:46:54', '30.00', '15.00', '123', '10', '瑞典', '0', '3', '3');
INSERT INTO `product` VALUES ('30', '毛衣', '4560.00', '/shop/images/yifu/fuzhuang12.png', '羊绒真丝混纺毛衣', '0', '2018-11-06 11:46:54', '30.00', '15.00', '26', '231', '波兰', '0', '3', '3');
INSERT INTO `product` VALUES ('31', '布帽衫', '4365.00', '/shop/images/yifu/fuzhuang13.png', '印花纯棉平纹布帽衫', '0', '2018-11-06 11:46:54', '30.00', '15.00', '26', '30', '德国', '1', '3', '3');
INSERT INTO `product` VALUES ('32', '卫衣', '2436.00', '/shop/images/yifu/fuzhuang14.png', '大廓形印花纯棉平纹布卫衣', '0', '2018-11-06 11:46:54', '0.00', '0.00', '500', '10', '英国', '0', '3', '3');
INSERT INTO `product` VALUES ('33', '开襟衫', '2400.00', '/shop/images/yifu/fuzhuang15.png', '大廓形罗纹针织开襟衫', '0', '2018-11-06 11:46:54', '30.00', '15.00', '60', '10', '波兰', '1', '3', '3');
INSERT INTO `product` VALUES ('34', 'T 恤', '1625.00', '/shop/images/yifu/fuzhuang16.png', '印花纯棉平纹布 T 恤', '0', '2018-11-06 11:46:54', '0.00', '0.00', '100', '20', '德国', '0', '3', '3');
INSERT INTO `product` VALUES ('35', '连衣裙', '2764.00', '/shop/images/yifu/fuzhuang17.png', 'Ella 不对称烧花缎布超长裹身连衣裙', '0', '2018-11-06 11:46:54', '30.00', '15.00', '150', '30', '瑞典', '1', '3', '3');
INSERT INTO `product` VALUES ('36', '牛仔裤', '1625.00', '/shop/images/yifu/fuzhuang18.png', '高腰紧身牛仔裤', '0', '2018-11-06 11:46:54', '0.00', '0.00', '123', '60', '芬兰', '0', '3', '3');
INSERT INTO `product` VALUES ('37', '连衣裙', '8465.00', '/shop/images/yifu/fuzhuang19.png', 'Daniela 露肩褶饰卡迪面料连衣裙', '0', '2018-11-06 11:46:54', '30.00', '15.00', '26', '12', '芬兰', '1', '3', '3');
INSERT INTO `product` VALUES ('38', '布帽衫', '1624.00', '/shop/images/yifu/fuzhuang20.png', '印花纯棉平纹布帽衫', '0', '2018-11-06 11:46:54', '0.00', '0.00', '26', '10', '英国', '0', '3', '3');
INSERT INTO `product` VALUES ('39', '连衣裙', '12256.00', '/shop/images/yifu/fuzhuang1.png', '不对称孔眼缀饰罗纹弹力针织中长连衣裙', '0', '2018-11-06 11:46:54', '30.00', '15.00', '500', '10', '波兰', '0', '3', '3');
INSERT INTO `product` VALUES ('40', '拉菲红酒', '608.00', '/shop/images/jiu/lafei2.png', '不对称孔Lafite拉菲红酒高档礼盒 拉菲奥希耶', '0', '2018-11-06 11:46:54', '0.00', '0.00', '200', '231', '德国', '1', '2', '2');
INSERT INTO `product` VALUES ('41', '罗意威', '670.00', '/shop/images/xiangshui/xiangshui1.png', '罗意威 001 香水15ml套装', '0', '2018-11-06 11:46:54', '0.00', '0.00', '55', '33', '瑞典', '0', '4', '4');
INSERT INTO `product` VALUES ('42', '克丽丝香水', '750.00', '/shop/images/xiangshui/xiangshui2.png', '克丽丝汀迪奥迪奥小姐花漾淡香水', '0', '2018-11-06 11:46:54', '30.00', '15.00', '94', '23', '德国', '0', '4', '4');
INSERT INTO `product` VALUES ('43', '阿玛尼香水', '880.00', '/shop/images/xiangshui/xiangshui3.png', '阿玛尼迷情挚爱女士香水', '0', '2018-11-06 11:46:54', '30.00', '15.00', '148', '31', '芬兰', '0', '4', '4');
INSERT INTO `product` VALUES ('44', '罗意威男款香水', '690.00', '/shop/images/xiangshui/xiangshui4.png', '罗意威001男款香水', '0', '2018-11-06 11:46:54', '0.00', '0.00', '123', '60', '英国', '1', '4', '4');
INSERT INTO `product` VALUES ('45', '圣罗兰香水', '1090.00', '/shop/images/xiangshui/xiangshui5.png', '圣罗兰黑色奥飘茗女士香水', '1', '2018-11-06 11:46:54', '30.00', '15.00', '93', '15', '波兰', '0', '4', '4');
INSERT INTO `product` VALUES ('46', '精醇古龙香水', '1480.00', '/shop/images/xiangshui/xiangshui6.png', '欧珑无极乌龙精醇古龙', '0', '2018-11-06 11:46:54', '0.00', '0.00', '26', '10', '德国', '0', '4', '4');
INSERT INTO `product` VALUES ('47', '缪缪粉漾香水', '1080.00', '/shop/images/xiangshui/xiangshui7.png', '缪缪粉漾女士香水', '0', '2018-11-06 11:46:54', '0.00', '0.00', '60', '10', '瑞典', '1', '4', '4');
INSERT INTO `product` VALUES ('48', '纪梵希香水', '790.00', '/shop/images/xiangshui/xiangshui8.png', '纪梵希粉红倾城灵动淡香水', '0', '2018-11-06 11:46:54', '30.00', '15.00', '100', '231', '德国', '1', '4', '4');
INSERT INTO `product` VALUES ('49', '凯卓纯净香水', '580.00', '/shop/images/xiangshui/xiangshui9.png', '凯卓纯净之水男士香氛', '0', '2018-11-06 11:46:54', '0.00', '0.00', '150', '30', '瑞典', '0', '4', '4');
INSERT INTO `product` VALUES ('50', '凯卓挚爱男士香水', '710.00', '/shop/images/xiangshui/xiangshui10.png', '凯卓挚爱纯水男士淡香氛', '0', '2018-11-06 11:46:54', '30.00', '15.00', '123', '10', '波兰', '1', '4', '4');
INSERT INTO `product` VALUES ('51', '凯卓挚爱女士香水', '710.00', '/shop/images/xiangshui/xiangshui11.png', '凯卓挚爱纯水女士淡香氛', '0', '2018-11-06 11:46:54', '30.00', '15.00', '111', '10', '德国', '1', '4', '4');
INSERT INTO `product` VALUES ('52', '缪缪莹铃香水', '1180.00', '/shop/images/xiangshui/xiangshui12.png', '缪缪莹铃女士香氛', '0', '2018-11-06 11:46:54', '0.00', '0.00', '111', '12', '英国', '0', '4', '4');
INSERT INTO `product` VALUES ('53', '蒂芙尼女士', '930.00', '/shop/images/xiangshui/xiangshui13.png', '蒂芙尼女士香氛（铂金版）', '0', '2018-11-06 11:46:54', '30.00', '15.00', '101', '10', '波兰', '0', '4', '4');
INSERT INTO `product` VALUES ('54', '宝格丽香水', '1000.00', '/shop/images/xiangshui/xiangshui14.png', '宝格丽罗马之夜女士香水', '0', '2018-11-06 11:46:54', '30.00', '15.00', '11', '10', '德国', '0', '4', '4');
INSERT INTO `product` VALUES ('55', '馥蕾诗香水', '180.00', '/shop/images/xiangshui/xiangshui15.png', '馥蕾诗清甜荔枝香香氛', '0', '2018-11-06 11:46:54', '0.00', '0.00', '9', '231', '瑞典', '0', '4', '4');
INSERT INTO `product` VALUES ('56', '欧珑香水', '1480.00', '/shop/images/xiangshui/xiangshui16.png', '欧珑香水 帝国麝香', '0', '2018-11-06 11:46:54', '30.00', '15.00', '9', '30', '德国', '1', '4', '4');
INSERT INTO `product` VALUES ('57', '莫杰雏菊挚爱香水', '880.00', '/shop/images/xiangshui/xiangshui17.png', '莫杰雏菊挚爱女士淡香氛', '0', '2018-11-06 11:46:54', '30.00', '15.00', '9', '20', '英国', '0', '4', '4');
INSERT INTO `product` VALUES ('58', '周末淡香水', '1000.00', '/shop/images/xiangshui/xiangshui18.png', 'Maison Margiela慵懒周末淡香水', '0', '2018-11-06 11:46:54', '0.00', '0.00', '9', '30', '波兰', '1', '4', '4');
INSERT INTO `product` VALUES ('59', '博斯澄澈香水', '610.00', '/shop/images/xiangshui/xiangshui19.png', '博斯澄澈男士淡香水', '0', '2018-11-06 11:46:54', '30.00', '15.00', '9', '60', '英国', '0', '4', '4');
INSERT INTO `product` VALUES ('60', '卡雷优香水', '510.00', '/shop/images/xiangshui/xiangshui20.png', '卡尔文克雷恩卡雷优香水', '0', '2018-08-08 11:10:40', '0.00', '0.00', '9', '12', '德国', '1', '4', '4');
INSERT INTO `product` VALUES ('61', '克丽丝香水', '750.00', '/shop/images/xiangshui/xiangshui21.png', '克丽丝汀迪奥迪奥小姐淡香水', '0', '2018-08-08 11:10:40', '0.00', '0.00', '9', '10', '英国', '0', '4', '4');
INSERT INTO `product` VALUES ('62', '纪梵希香水', '790.00', '/shop/images/upload/test.png', '纪梵希粉红倾城灵动淡香水', '0', '2019-03-29 23:18:52', '30.00', '15.00', '9', '0', '瑞典', '1', '4', '4');

-- ----------------------------
-- Table structure for spelllist
-- ----------------------------
DROP TABLE IF EXISTS `spelllist`;
CREATE TABLE `spelllist` (
  `sid` int(11) unsigned zerofill NOT NULL,
  `price` double(10,2) default NULL,
  `number` int(11) default NULL,
  `eixtnum` int(255) default NULL,
  `state` int(255) default NULL,
  `pid` int(11) NOT NULL,
  `bid` int(11) default NULL,
  PRIMARY KEY  (`sid`),
  KEY `spellist_product` (`pid`),
  CONSTRAINT `spellist_product` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spelllist
-- ----------------------------
INSERT INTO `spelllist` VALUES ('00000000001', '100.00', '2', '2', '1', '45', '4');
INSERT INTO `spelllist` VALUES ('00000000002', '100.00', '2', '0', '0', '48', '4');
INSERT INTO `spelllist` VALUES ('00000000003', '100.00', '2', '0', '0', '41', '4');

-- ----------------------------
-- Table structure for spike
-- ----------------------------
DROP TABLE IF EXISTS `spike`;
CREATE TABLE `spike` (
  `sid` int(11) unsigned zerofill NOT NULL,
  `price` double(10,2) default NULL,
  `time` datetime default NULL,
  `state` int(11) default NULL,
  `number` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `bid` int(11) default NULL,
  PRIMARY KEY  (`sid`),
  KEY `spike_product` (`pid`),
  CONSTRAINT `spike_product` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spike
-- ----------------------------
INSERT INTO `spike` VALUES ('00000000001', '111.00', '2019-03-28 00:00:00', '2', '0', '42', '4');
INSERT INTO `spike` VALUES ('00000000002', '111.00', '2019-03-28 00:00:00', '2', '0', '41', '4');
INSERT INTO `spike` VALUES ('00000000003', '111.00', '2019-03-28 00:00:00', '0', '1', '43', '4');
INSERT INTO `spike` VALUES ('00000000004', '1.00', '2019-03-29 23:55:54', '2', '0', '41', '4');
INSERT INTO `spike` VALUES ('00000000005', '100.00', '2019-04-07 00:30:13', '0', '1', '51', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `addr` varchar(255) default NULL,
  `state` int(11) default NULL,
  `usid` int(64) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `user_sp` (`usid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a', 'a', '张三', '1666542@qq.com', '12123432131', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('2', 'aa', 'aa', '李四', '1552322@qq.com', '12312424312', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('3', 'ad', 'ad', '王武', '131231422@qq.com', '21312344124', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('4', 'asd', '12345a', '张明', '1552322@qq.com', '12343242212', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('5', 'wqw', '12345A', '朱晓明', '1552322@qq.com', '45413214533', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('6', 'aaa', 'aaa', '王六', '122@qq.com', '12345678901', '广西钦州市', null, null);
INSERT INTO `user` VALUES ('7', 'aaaa', 'aaaa', '李武', '12@qq.com', '12525553324', '广西钦州市', '0', null);
INSERT INTO `user` VALUES ('8', 'bbbbb', '123123aa', '李玩', '12@qq.com', '12525553324', '广西钦州市', '0', null);
INSERT INTO `user` VALUES ('9', 'a', '', '', '', '', '', '0', null);
INSERT INTO `user` VALUES ('10', 'b', '123456aa', '李武', '12@qq.com', '12525553324', '广西', '0', null);
INSERT INTO `user` VALUES ('11', '', '', '', '', '', '', '0', null);

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist` (
  `usid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY  (`usid`),
  KEY `user_spllist` (`uid`),
  KEY `spllist_user` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES ('2', '1', '1');
INSERT INTO `userlist` VALUES ('3', '1', '0');
INSERT INTO `userlist` VALUES ('4', '1', '6');
INSERT INTO `userlist` VALUES ('5', '1', '0');

-- ----------------------------
-- Procedure structure for autoInsert
-- ----------------------------
DROP PROCEDURE IF EXISTS `autoInsert`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `autoInsert`()
BEGIN
	DECLARE
		i INT DEFAULT 0 ; -- ��ʼ 
	SET autocommit = 0 ; -- ����
	WHILE (i <= 100) DO
		REPLACE INTO message (
			`id`,
			`nick_name`,
			`ip`,
			`insert_time`
		)
	VALUE
		(
			i,
			'��һ��',
			'127.0.0.1',
			NOW()
		) ;
	SET i = i + 1 ;
	END
	WHILE ;
	SET autocommit = 1 ; COMMIT ;
	END
;;
DELIMITER ;
