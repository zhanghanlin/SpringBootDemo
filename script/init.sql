/*Table structure for table `t_role` */
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(32)      DEFAULT NULL,
  KEY `id` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

/*Data for the table `t_role` */
INSERT INTO `t_role` (`id`, `rolename`) VALUES (1, 'admin'), (2, 'manager'), (3, 'normal');

/*Table structure for table `t_user` */
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(32)      DEFAULT NULL,
  `password`  VARCHAR(32)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

/*Data for the table `t_user` */

INSERT INTO `t_user` (`id`, `username`, `password`)
VALUES (1, 'tom', '4280d89a5a03f812751f504cc10ee8a5'),
  (2, 'jack', '4280d89a5a03f812751f504cc10ee8a5'),
  (3, 'rose', '4280d89a5a03f812751f504cc10ee8a5');

/*Table structure for table `t_user_role` */
DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` INT(11) DEFAULT NULL,
  `role_id` INT(11) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `t_user_role` */

INSERT INTO `t_user_role` (`user_id`, `role_id`) VALUES (1, 1), (1, 3), (2, 2), (2, 3), (3, 3);