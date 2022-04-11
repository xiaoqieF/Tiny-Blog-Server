# Tiny-Blog-Server

## Introduction

本项目为 [Tiny-Blog](https://github.com/xiaoqieF/Tiny-Blog) 项目的后端系统，与其共同组成了一个简洁的个人博客系统。  

本项目采用的开发工具和版本为：  
1. SpringBoot v2.6.5
2. 数据库操作使用 Mybatis v3.5.9
3. jdk版本为： jdk 8
4. 数据库为MySQL 8.xx

主要实现的功能为：

- [x] 数据库博客表的维护
- [x] 用户表的维护
- [x] 博客评论的维护
- [x] 博客分类维护
- [x] 博客标签维护

## Usage

```
git clone git@github.com:xiaoqieF/Tiny-Blog-Server.git
```

1. 创建数据库。

在 mysql 终端运行 createTables.sql 创建本项目所有数据库表。

2. 使用idea打开项目，编译运行。


## 说明

本项目需要配合 [Tiny-Blog](https://github.com/xiaoqieF/Tiny-Blog) 前端项目运行，程序运行的默认端口号是8082，若在全局配置文件中修改，
需要在前端项目的 `main.js`文件中修改Axios的baseUrl。  

文件的默认上传位置在 Windows 环境下是`E:/upload`，你的设备不存在该路径时需要在全局配置文件`application.yml`文件和`FileUploadUtil.java`
文件中同时修改它们。

