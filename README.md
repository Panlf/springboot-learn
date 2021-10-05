## springboot-learn

整合SpringBoot的相关技术，并给与对应的案例，方便学习和快速入门使用。

### SpringBoot使用AOP实现多数据源

Spring自带的AOP功能实现多数据源的配置。

### SpringBoot使用Guava的限流器

SpringBoot整合Guava中的RateLimiter类，实现接口限流功能。

### SpringBoot整合Caffeine

SpringBoot整合Caffeine，实现内存缓存功能。

### 基于SpringBoot的自定义事件使用。

使用Spring自带的自定义事件，用于监听并事件发布。案例是监听到邮件的传入就异步进行事件处理，但是目前这种方式不常用，一般用MQ-消息中间件替代，可在中小型项目可以使用。

### SpringBoot整合Dataway

SpringBoot整合Dataway，实现无需Controller、Service等类即可访问接口。

### SpringBoot整合分布式锁

基于Redis、Redisson、Zookeeper的分布式锁。

### 基于SpringBoot的策略模式

基于SpringBoot使用策略模式实现处理Txt、Doc等写文档和都文档。

### SpringBoot整合Druid Monitor

SpringBoot整合Druid、Postgresql、Mybatis，实现Druid对SQL语句的监控。

### SpringBoot整合elastic-job

SpringBoot整合elastic-job，实现分布式定时任务。

### SpringBoot整合ElasticSearch

SpringBoot整合ElasticSearch，实现基本的存储和读取的功能

### SpringBoot整合JetCache

简单实现基于JetCache的数据缓存。

### SpringBoot整合Spring-Data-Jpa

Spring-Data-Jpa 功能完善，但是还是有缺陷，比如update不支持如果NULL则不更新，还有动态条件写起来比较复杂，个人感觉Jpa适合单表操作，很考验数据库设计水平。

### 5、SpringBoot整合JWT

Jwt用于信息访问授权功能，本案例使用Jwt生成授权token，访问特定方法进行token验证，并使用Redis存储敏感数据。

### SpringBoot整合Kafka集群

SpringBoot整合Kafka集群，实现生产者、消费者消息的发送、监听。

### 基于SpringBoot的log日志记录

基于SpringBoot，使用AOP记录log日志。

### 基于SpringBoot的发送邮件功能。

使用SpringBoot的邮件功能，发送简单邮件、HTML邮件、带附件邮件、带图片邮件。

### SpringBoot整合MongoDB

使用SpringBoot整合MongoDB，实现基本数据存储和读取功能

### SpringBoot整合MyBatis-Plus

MyBatis-Plus功能比较强大，本案例使用注解式开发，但是过程中发现注解式不适合业务复杂的，比如动态条件多表联查，建议使用XML方式。

### SpringBoot整合Redis

SpringBoot整合Redis，并实现基于Redis的接口幂等性功能。

### SpringBoot整合Spring Security

SpringBoot整合Spring Security，实现权限控制。

### SpringBoot整合Sharding-JDBC和MyBatis

简单根据取模0-1进行数据的分库分表，使用配置的方式进行操作。

### SpringBoot整合Spring Session实现分布式Session

SpringBoot整合Spring Session，并将Session数据保存到Redis中，实现分布式Session。

### SpringBoot整合Shiro

实现Shiro的登录、登出、权限操作等简单功能。

### SpringBoot整合Solr

SpringBoot整合Solr，实现基本的数据存储和读取功能。


### SpringBoot整合MongoDB、ActiveMQ、Freemarker实现静态化页面

数据存储在MongoDB中，利用ActiveMQ的队列模式，监听到数据产生或者更改就立即对页面进行静态化输出。

### 基于SpringBoot的Threadlocal

基于SpringBoot的Threadlocal，实现线程隔离。

### SpringBoot整合websocket

SpringBoot整合Websocket，实现前后端消息的传送接受。




