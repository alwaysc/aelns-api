CREATE TABLE `platform_application` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '名称',
  `app_key` varchar(2000) DEFAULT NULL COMMENT '应用ID',
  `public_key` varchar(2000) DEFAULT NULL COMMENT '公钥',
  `private_key` varchar(2000) DEFAULT NULL COMMENT '私钥',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1815333743 DEFAULT CHARSET=utf8;