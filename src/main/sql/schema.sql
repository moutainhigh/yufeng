--数据库初始化脚本

--创建数据库
CREATE DATABASE yufeng;

--使用数据库

use yufeng;

--创建注册账户表

CREATE TABLE register_account_table
(
    name VARCHAR(100) NOT NULL COMMENT '用户名',
    password VARCHAR(20) NOT NULL COMMENT '登录密码',
    phone_number VARCHAR(11) NOT NULL COMMENT '用户手机号',
    source VARCHAR(2) NOT NULL COMMENT '注册来源',
    account_flag VARCHAR(2) NOT NULL COMMENT '账户状态',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    PRIMARY KEY (name),
    KEY idx_name(name),
    KEY idx_phone_number(phone_number),
    KEY idx_account_flag(account_flag)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注册账户表';

INSERT INTO register_account_table(name,password,phone_number,
source,account_flag,create_time) VALUES
    ('ROOT','ROOT','1822323232','A','A','2016-07-17 00:00:00');
