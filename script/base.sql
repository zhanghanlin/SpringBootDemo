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
  `weight`     INT(10) COMMENT '权重,排序使用',
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

INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('1', '权限菜单', '顶级权限', NULL, 0, '0', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('2', '系统设置', '系统设置', NULL, 1, '0,1', NULL, NULL, 'mainFrame', 1, 1, 1);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('3', '用户信息', '用户信息', NULL, 2, '0,1,2', '/user/list', NULL, 'mainFrame', 1, 1, 101);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('4', '查看', '查看用户信息', 'user:view', 3, '0,1,2,3', NULL, NULL, 'mainFrame', 0, 1, 10101);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('5', '修改', '修改用户信息', 'user:edit', 3, '0,1,2,3', NULL, NULL, 'mainFrame', 0, 1, 10102);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('6', '权限信息', '权限信息', NULL, 2, '0,1,2', '/perm/list', NULL, 'mainFrame', 1, 1, 102);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('7', '查看', '查看权限信息', 'perm:view', 6, '0,1,2,6', NULL, NULL, 'mainFrame', 0, 1, 10201);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('8', '编辑', '编辑权限信息', 'perm:edit', 6, '0,1,2,6', NULL, NULL, 'mainFrame', 0, 1, 10202);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('9', '角色信息', '角色信息', NULL, 2, '0,1,2', '/role/list', NULL, 'mainFrame', 1, 1, 103);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('10', '查看', '查看角色信息', 'role:view', 9, '0,1,2,9', NULL, NULL, 'mainFrame', 0, 1, 10301);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('11', '编辑', '编辑角色信息', 'role:edit', 9, '0,1,2,9', NULL, NULL, 'mainFrame', 0, 1, 10302);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('12', '日志信息', '日志信息', NULL, 1, '0,1', NULL, NULL, 'mainFrame', 1, 1, 2);
INSERT INTO m_permission (id, name, note, unique_key, parent_id, parent_ids, link, icon, target, is_show, is_sys, weight)
VALUES ('13', '数据库监控', '数据库监控', NULL, 12, '0,1,12', '/druid/datasource.html', NULL, '_blank', 1, 1, 201);

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `user_id` VARCHAR(64) NOT NULL
  COMMENT '用户Id',
  `role_id` VARCHAR(64) NOT NULL
  COMMENT '角色Id',
  PRIMARY KEY (`user_id`, `role_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_user_role (user_id, role_id) VALUES ('1', '1');
INSERT INTO m_user_role (user_id, role_id) VALUES ('2', '2');


DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission` (
  `role_id`       VARCHAR(64) NOT NULL
  COMMENT '角色id',
  `permission_id` VARCHAR(64) NOT NULL
  COMMENT '权限id',
  PRIMARY KEY (`role_id`, `permission_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '1');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '2');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '3');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '4');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '5');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '6');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '7');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '8');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '9');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '10');
INSERT INTO m_role_permission (role_id, permission_id) VALUES ('2', '11');