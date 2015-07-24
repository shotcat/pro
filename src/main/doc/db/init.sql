/*
SQLyog Ultimate v11.3 (32 bit)
MySQL - 5.5.37 : Database - pro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `s_resource` */

CREATE TABLE `s_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0:模块 1：导航菜单 2:按钮操的',
  `parent_id` int(11) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `url` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `s_resource` */

insert  into `s_resource`(`resource_id`,`resource_name`,`type`,`parent_id`,`icon`,`sort`,`state`,`create_time`,`update_time`,`url`,`target`) values (1,'系统管理模块',0,NULL,NULL,0,0,'2015-05-05 09:34:16','2015-05-05 09:34:16','/admin/index.mvc',NULL),(2,'用户管理',1,1,NULL,0,0,'2015-04-20 10:38:21','2015-04-20 10:37:10','/admin/user/index.mvc',NULL),(3,'角色管理',1,1,NULL,0,0,'2015-04-20 10:41:34','2015-04-20 10:37:10','/admin/role/index.mvc',NULL),(4,'权限管理',1,1,NULL,0,0,'2015-04-20 10:41:35','2015-04-20 10:37:10','/admin/resource/index.mvc',NULL),(5,'新增用户',2,2,NULL,0,0,'2015-04-23 16:47:34','2015-04-23 16:47:37','/admin/user/addUser.mvc',NULL),(6,'修改用户',2,2,NULL,0,0,'2015-04-23 16:48:12','2015-04-23 16:48:14','/admin/user/editUser.mvc',NULL),(7,'删除用户',2,2,NULL,0,1,'2015-05-05 09:50:21','2015-05-05 09:50:21','/admin/user/deleteUser.mvc',NULL),(8,'设置用户角色',2,2,NULL,0,0,'2015-05-05 09:50:56','2015-05-05 09:50:56','/admin/user/settingRole.mvc',NULL),(9,'新增角色',2,3,NULL,0,0,'2015-04-27 15:53:58','2015-04-27 15:53:33','/admin/role/addRole.mvc',NULL),(10,'修改角色',2,3,NULL,0,0,'2015-04-27 15:54:35','2015-04-27 15:54:14','/admin/role/editRole.mvc',NULL),(11,'删除角色',2,3,NULL,0,1,'2015-05-05 09:51:03','2015-05-05 09:51:03','/admin/role/deleteRole.mvc',NULL),(12,'角色授权',2,3,NULL,0,0,'2015-04-27 15:55:38','2015-04-27 15:55:17','/admin/role/roleAuthorize.mvc',NULL),(13,'博客系统模块',0,NULL,NULL,0,0,'2015-05-05 09:34:00','2015-05-05 09:34:00','/admin/index.mvc',NULL),(17,'新建博客',0,20,NULL,0,0,'2015-05-05 09:35:18','2015-05-05 09:35:18','/blog/newBlog.mvc',NULL),(18,'编辑博客',0,20,NULL,0,0,'2015-05-05 09:35:32','2015-05-05 09:35:32','/blog/editBlog.mvc',NULL),(19,'删除博客',0,20,NULL,0,0,'2015-05-05 09:35:25','2015-05-05 09:35:25','/blog/deleteBlog.mvc',NULL),(20,'个人博客',0,13,NULL,0,0,'2015-05-05 09:35:10','2015-05-05 09:35:10','/blog/index.mvc',NULL),(21,'新增权限资源',0,4,NULL,0,0,'2015-05-05 09:49:25','2015-05-05 09:49:25','/admin/resource/addResource.mvc',NULL),(22,'修改权限资源',0,4,NULL,0,0,'2015-05-05 09:50:02','2015-05-05 09:50:02','/admin/resource/editResource.mvc',NULL);

/*Table structure for table `s_role` */

CREATE TABLE `s_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(32) DEFAULT '未定义角色' COMMENT '角色名称',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `des` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `s_role` */

insert  into `s_role`(`role_id`,`role_name`,`state`,`des`,`create_time`,`update_time`) values (1,'超级管理员',0,'我是超级管理员','2015-04-28 11:26:24','2015-04-28 11:26:29'),(2,'ddd',1,'dfd','2015-04-28 11:26:32','2015-04-28 11:26:34');

/*Table structure for table `s_role_resource` */

CREATE TABLE `s_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

/*Data for the table `s_role_resource` */

insert  into `s_role_resource`(`id`,`role_id`,`resource_id`) values (111,2,1),(112,2,8),(113,2,2),(114,2,12),(115,2,3),(116,2,17),(117,2,13),(118,2,11),(119,2,10),(120,2,9),(121,2,4),(122,2,5),(123,2,6),(124,2,21),(125,2,19),(126,2,20),(146,1,1),(147,1,2),(148,1,3),(149,1,4),(150,1,5),(151,1,6),(152,1,7),(153,1,8),(154,1,9),(155,1,10),(156,1,11),(157,1,12),(158,1,13),(159,1,17),(160,1,21),(161,1,22),(162,1,19),(163,1,20),(164,1,18);

/*Table structure for table `s_user` */

CREATE TABLE `s_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `state` int(11) DEFAULT '0' COMMENT '状态：0正常 1：无效',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `sex` int(11) DEFAULT '0' COMMENT '性别',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `s_user` */

insert  into `s_user`(`user_id`,`user_name`,`password`,`email`,`state`,`nick_name`,`create_time`,`update_time`,`age`,`sex`,`mobile`,`address`) values (1,'admin','123456','466862016@qq.com',0,'超级管理员','2015-04-30 10:53:55','2015-04-20 10:33:03',0,0,'15910860051','河南省商丘市'),(2,'dongtian','123456','248434199@qq.com',1,'24小时搞逗比','2015-04-30 10:54:12','2015-04-27 14:37:25',12,1,'15910860050','北京市朝阳区'),(3,'guest','123456','1112@qq.com',0,'小刀会','2015-04-30 13:41:16','2015-04-30 13:41:16',0,0,'15910562201','henan'),(4,'ftb001','123456','dddd@qq.com',1,'斧头帮','2015-04-30 15:13:22','2015-04-30 15:13:22',10,0,'13462798011','北京市朝阳区亚运村');

/*Table structure for table `s_user_role` */

CREATE TABLE `s_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `s_user_role_fk` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `s_user_role` */

insert  into `s_user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(3,1,2),(4,4,1),(5,4,2),(6,3,2),(7,2,2);

/*Table structure for table `tag` */

CREATE TABLE `tag` (
  `TAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TAG_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
