create database if not exists self_growth_auth;

create table if not exists self_growth_auth.app_version(
    id int auto_increment primary key not null ,
    version varchar(100) not null,
    code int not null default 0,
    msg text not null ,
    url varchar(100) not null unique
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

create table if not exists self_growth_auth.feedback(
    id int auto_increment primary key not null ,
    feedback longtext not null,
    email varchar(100) not null default '',
    is_process tinyint not null default '0'
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

create table if not exists self_growth_auth.user_info(
    id int auto_increment primary key not null ,
    username varchar(100) not null unique ,
    password varchar(200) not null ,
    create_time timestamp not null default current_timestamp ,
    update_time timestamp not null default current_timestamp ,
    is_delete tinyint not null default '0'
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

insert into self_growth_auth.user_info(username, password) values ("username", "password"), ("test", "test");
