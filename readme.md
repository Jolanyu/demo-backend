# demo-backend
> 为实验室学弟学妹们提供的 Web 前后端项目演示 Demo 的 Java Spring Boot 后端。

## 启动
    + 导入数据库 
        导入 /src/main/resources/demo.sql 至数据库, mysql5.7需要调整字符集

    + 填写数据库地址
        修改 /src/main/resources/application.yml，修改以下内容：
        spring:
        datasource:
            driver-class-name: com.mysql.jdbc.Driver
            url: "jdbc:mysql://数据库地址:端口/数据库名称"
            username: "数据库用户名"
            password: "数据库密码"
        redis:
            host: redis地址
            port: redis端口
            database: 0


