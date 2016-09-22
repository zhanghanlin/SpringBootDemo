DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id`           VARCHAR(64)  NOT NULL,
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
  `status`       INT(3)       NOT NULL DEFAULT 0
  COMMENT '状态,默认正常',
  `created_at`   DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by`   VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at`   DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by`   VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`      INT(10)      NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_NAME` (`user_name`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_user (id, user_name, password, display_name, email)
VALUES ('1', 'admin', '4280d89a5a03f812751f504cc10ee8a5', 'Admin', 'admin@test.com');
INSERT INTO m_user (id, user_name, password, display_name, email)
VALUES ('2', 'user', '4280d89a5a03f812751f504cc10ee8a5', 'User', 'user@test.com');


DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id`         VARCHAR(64)  NOT NULL,
  `name`       VARCHAR(100) NOT NULL
  COMMENT '角色名',
  `note`       VARCHAR(200) COMMENT '角色说明',
  `unique_key` VARCHAR(200) COMMENT '角色唯一key',
  `status`     INT(3)       NOT NULL DEFAULT 0
  COMMENT '状态,默认正常',
  `created_at` DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)      NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_NAME` (`name`) USING BTREE,
  UNIQUE KEY `UNI_KEY` (`unique_key`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_role (id, name, note, unique_key) VALUES ('1', 'Admin', '管理员', 'admin');
INSERT INTO m_role (id, name, note, unique_key) VALUES ('2', 'NormalUser', '普通用户', 'normal_user');


DROP TABLE IF EXISTS `m_permission`;
CREATE TABLE `m_permission` (
  `id`         VARCHAR(64)   NOT NULL,
  `name`       VARCHAR(100)  NOT NULL
  COMMENT '权限名',
  `note`       VARCHAR(200) COMMENT '权限说明',
  `unique_key` VARCHAR(200) COMMENT '权限唯一key',
  `parent_id`  VARCHAR(64)   NOT NULL         DEFAULT 0
  COMMENT '上一级权限Id',
  `parent_ids` VARCHAR(2000) NOT NULL
  COMMENT '所有父级Id',
  `link`       VARCHAR(200) COMMENT '权限对应的功能连接',
  `icon`       VARCHAR(100) COMMENT '图标',
  `target`     VARCHAR(100) COMMENT '打开方式',
  `is_show`    INT(1)        NOT NULL
  COMMENT '是否在菜单中显示',
  `is_sys`     INT(1)        NOT NULL         DEFAULT 1
  COMMENT '是否系统菜单',
  `weight`     INT(3) COMMENT '权重,排序使用',
  `status`     INT(1)        NOT NULL         DEFAULT 0
  COMMENT '状态,默认正常',
  `created_at` DATETIME      NOT NULL         DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100)  NOT NULL         DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME                       DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)                   DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)       NOT NULL         DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_KEY` (`unique_key`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('1', '系统设置', '系统设置', 'sys', 0, '0', NULL, NULL, 1, 1, 2);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('2', '用户信息', '用户信息', 'sys:user', 1, '0,1', '/user/list', NULL, 1, 1, 3);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('3', '权限信息', '权限信息', 'sys:perm', 1, '0,1', '/perm/list', NULL, 1, 1, 4);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('4', '角色信息', '角色信息', 'sys:role', 1, '0,1', '/role/list', NULL, 1, 1, 5);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight)
VALUES ('5', '日志信息', '日志信息', 'sys:log', 0, '0,', NULL, NULL, 1, 1, 6);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, is_show, is_sys, weight, target)
VALUES ('6', '数据库监控', '数据库监控', 'sys:log:datasource', 5, '0,5', '/druid/datasource.html', NULL, 1, 1, 7, '_blank');

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `id`         VARCHAR(64)  NOT NULL,
  `user_id`    VARCHAR(64)      NOT NULL
  COMMENT '用户Id',
  `role_id`    VARCHAR(64)      NOT NULL
  COMMENT '角色Id',
  `created_at` DATETIME     NOT NULL  DEFAULT NOW()
  COMMENT '创建时间',
  `created_by` VARCHAR(100) NOT NULL  DEFAULT 'System'
  COMMENT '创建人',
  `changed_at` DATETIME               DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)           DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)      NOT NULL  DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_user_role (id, user_id, role_id) VALUES ('1', '1', '1');
INSERT INTO m_user_role (id, user_id, role_id) VALUES ('2', '2', '2');


DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission` (
  `id`            VARCHAR(64)  NOT NULL,
  `role_id`       VARCHAR(64)      NOT NULL
  COMMENT '角色id',
  `permission_id` VARCHAR(64)     NOT NULL
  COMMENT '权限id',
  `created_at`    DATETIME     NOT NULL DEFAULT NOW()
  COMMENT '创建时间',
  `created_by`    VARCHAR(100) NOT NULL DEFAULT 'System'
  COMMENT '创建人',
  `changed_at`    DATETIME              DEFAULT NOW()
  COMMENT '修改时间',
  `changed_by`    VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`       INT(10)      NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('1', '1', '1');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('2', '1', '2');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('3', '1', '3');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('4', '1', '4');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('5', '1', '5');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('6', '1', '6');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('7', '2', '1');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('8', '2', '2');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('9', '2', '3');
INSERT INTO m_role_permission (id, role_id, permission_id) VALUES ('10', '2', '4');