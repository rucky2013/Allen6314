# Allen6314 

just a personal blog

# 目前部署架构图

![blog](http://7xrzlm.com1.z0.glb.clouddn.com/aliyun1.png?imageMogr2/thumbnail/!30p)

Ps：

- Node 和 Java 项目：主要目的做前后端分离。
- [Kong](https://getkong.org/)：做为一个 [Api Gateway](https://www.nginx.com/blog/building-microservices-using-an-api-gateway/) ，安全，以及 OAuth 等 放在这一层做。
- Nginx：由于 Kong 主要是对 API的一个管理，不对静态资源进行管理，所以静态资源放在 nginx 层。（Ps：其实这里一开始是因为 Kong 无法对 首页 www.wuhuachuan.com 进行转发，所以加的 nginx）
- Kong-Admin：自己开发的一个管理Kong的 Dashboard。因为 Kong 的操作需要使用命令行，为了方便和入门 openResty 所做的一个项目。
- cassandra/mysql 数据库：cassandra 是 Kong 所必须的，虽然可以改，但是自配的为了方便就不动它了。


# 目前出现的问题

- 单点太多，一个挂了， docker 重启的时候， --link 的连接相当于没用了，得全部容器都 restart。
- 上一个问题没解决，持续集成没法做。


# bkallenway(后端部分)

- 大体技术：springboot + spring data jpa + mysql 
- 基础服务已完成：
  - json的统一返回格式
  - 加密
  - 统一异常处理
  - 操作记录
  - 错误日志记录

# frontallenway（前端部分）

- 大体技术：nodejs(express + swig)
- 细节的地方
  - 使用 async 异步编程

# 启动

- 后端配置文件： bkallenway/sourcecode/boot/src/main/resources/application.yml
- 数据库初始化脚本：bkallenway/sourcecode/boot/src/main/resources/allenway_init.sql
