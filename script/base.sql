-- ------------------------------
-- Table structure for `template`
-- ------------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id`           INT(11)      NOT NULL AUTO_INCREMENT,
  `user_name`    VARCHAR(100) NOT NULL
  COMMENT '登录名',
  `password`     VARCHAR(100) NOT NULL
  COMMENT '密码',
  `display_name` VARCHAR(100)
  COMMENT '姓名',
  `email`        VARCHAR(100)
  COMMENT '邮箱',
  `phone`        VARCHAR(100)
  COMMENT '手机号',
  `status`       INT(3)       NOT NULL DEFAULT '0'
  COMMENT '状态,默认正常',
  `created_at`   DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by`   VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at`   DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by`   VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`      INT(10)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_UNAME` (`user_name`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_user (user_name, password, display_name, email, phone)
VALUES ('admin', '4280d89a5a03f812751f504cc10ee8a5', 'Admin', 'admin@test.com', '12345678901');
INSERT INTO m_user (user_name, password, display_name, email, phone)
VALUES ('tom', '4280d89a5a03f812751f504cc10ee8a5', 'Tom', 'tom@test.com', '12345678902');
INSERT INTO m_user (user_name, password, display_name, email, phone)
VALUES ('lisi', '4280d89a5a03f812751f504cc10ee8a5', '李四', 'lisi@test.com', '12345678903');

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id`         INT(11)      NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(100) NOT NULL
  COMMENT '角色名',
  `note`       VARCHAR(200) COMMENT '角色说明',
  `unique_key` VARCHAR(200) COMMENT '角色唯一key',
  `status`     INT(3)       NOT NULL DEFAULT '0'
  COMMENT '状态,默认正常',
  `created_at` DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_RNAME` (`name`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_role (name, note, unique_key) VALUES ('Admin', '管理员', 'admin');
INSERT INTO m_role (name, note, unique_key) VALUES ('NormalUser', '普通用户', 'normal_user');

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_permission`;
CREATE TABLE `m_permission` (
  `id`         INT(11)       NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(100)  NOT NULL
  COMMENT '权限名',
  `note`       VARCHAR(200) COMMENT '权限说明',
  `unique_key` VARCHAR(200) COMMENT '权限唯一key',
  `parent_id`  INT(11)                DEFAULT 0
  COMMENT '上一级权限Id',
  `parent_ids` VARCHAR(2000) NOT NULL
  COMMENT '所有父级Id',
  `link`       VARCHAR(200) COMMENT '权限对应的功能连接',
  `icon`       VARCHAR(100) COMMENT '图标',
  `target`     VARCHAR(100) COMMENT '打开方式',
  `is_show`    INT(1)        NOT NULL
  COMMENT '是否在菜单中显示',
  `is_sys`     INT(1)        NOT NULL DEFAULT 1
  COMMENT '是否系统菜单',
  `weight`     INT(3) COMMENT '权重,排序使用',
  `status`     INT(1)        NOT NULL DEFAULT 0
  COMMENT '状态,默认正常',
  `created_at` DATETIME      NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100)  NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME               DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)           DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)       NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_KEY` (`unique_key`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('系统设置', '系统设置', 'sys', 0, '0,', NULL, NULL, 1, 1, 2);
INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('用户信息', '用户信息', 'sys:user', 5, '0,5', '/user/list', NULL, 1, 1, 3);
INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('权限信息', '权限信息', 'sys:perm', 5, '0,5', '/perm/list', NULL, 1, 1, 4);
INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('角色信息', '角色信息', 'sys:role', 5, '0,5', '/role/list', NULL, 1, 1, 5);
INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('日志信息', '日志信息', 'sys:log', 0, '0,', NULL, NULL, 1, 1, 6, 0);
INSERT INTO boot.m_permission (name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('数据库监控', '数据库监控', 'sys:log:datasource', 9, '0,9', '/druid/datasource.html', NULL, 1, 1, 7);

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `id`         INT(11)      NOT NULL  AUTO_INCREMENT,
  `user_id`    INT(11)      NOT NULL
  COMMENT '用户Id',
  `role_id`    INT(11)      NOT NULL
  COMMENT '角色Id',
  `created_at` DATETIME     NOT NULL  DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100) NOT NULL  DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME               DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)           DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)      NOT NULL  DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission` (
  `id`            INT(11)      NOT NULL AUTO_INCREMENT,
  `role_id`       INT(11)      NOT NULL
  COMMENT '角色id',
  `permission_id` INT(200)     NOT NULL
  COMMENT '权限id',
  `created_at`    DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by`    VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at`    DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by`    VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`       INT(10)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;