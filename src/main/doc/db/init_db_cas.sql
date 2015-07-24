/*
SQLyog Ultimate v11.3 (32 bit)
MySQL - 5.5.37 : Database - cas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `s_user` */

CREATE TABLE `s_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `state` int(11) DEFAULT '0' COMMENT '状态0 正常 1锁定 2 密码过期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `job_id` varchar(32) DEFAULT NULL COMMENT '工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `s_user` */

insert  into `s_user`(`id`,`user_name`,`password`,`state`,`create_time`,`update_time`,`email`,`id_card`,`mobile`,`nick_name`,`job_id`) values (1,'admin','123456',0,'2015-05-11 15:17:28','2015-05-11 15:17:30','466@qq.com','4111101110','15910860051','超级管理员','110110110'),(2,'jack','123456',0,'2015-05-12 14:24:40','2015-05-12 14:24:42','ccc@qq.com','23222','13497654321','一般用户','111'),(3,'xiaochong','123456',0,'2015-05-12 14:24:34','2015-05-12 14:24:37',NULL,NULL,'13497654322','一般用户','2222');

/*Table structure for table `s_user_attempts` */

CREATE TABLE `s_user_attempts` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `attempts` int(11) DEFAULT '0' COMMENT '尝试登录次数',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后登录尝试次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `s_user_attempts` */

insert  into `s_user_attempts`(`id`,`user_name`,`attempts`,`last_update_time`) values (1,'jack',0,'2015-05-26 12:01:46'),(2,'admin',3,'2015-05-26 14:20:26');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
