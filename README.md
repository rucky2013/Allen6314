# Allen6314 

just a personal blog

# 目前部署架构图

![blog](http://7xrzlm.com1.z0.glb.clouddn.com/aliyun.png?imageMogr2/thumbnail/!30p)

Ps：作为一个小博客，这样子做确认有点大炮打蚊子，主要目的是为了熟悉下这些技术。

# bkallenway(后端部分)

- 大体技术：springboot + spring data jpa + mysql 
- 基础服务已完成：
  - json的统一返回格式
  - 加密
  - 统一异常处理
  - 操作记录
  - 增删权限管理
  - 错误日志记录

# frontallenway（前端部分）

- 大体技术：nodejs(express + swig)
- 细节的地方
  - 使用 async 异步编程

# 启动

- 后端配置文件： bkallenway/sourcecode/boot/src/main/resources/application.yml
- 数据库初始化脚本：bkallenway/sourcecode/boot/src/main/resources/allenway_init.sql
