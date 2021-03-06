## springboot-learn

整合SpringBoot的相关技术，并给与对应的案例，方便学习和快速入门使用。

### 1、基于SpringBoot的自定义事件使用。

使用Spring自带的自定义事件，用于监听并事件发布。案例是监听到邮件的传入就异步进行事件处理，但是目前这种方式不常用，一般用MQ-消息中间件替代，可在中小型项目可以使用。

### 2、基于SpringBoot的发送邮件功能。

使用SpringBoot的邮件功能，发送简单邮件、HTML邮件、带附件邮件、带图片邮件。

### 3、SpringBoot整合MyBatis-Plus

MyBatis-Plus功能比较强大，本案例使用注解式开发，但是过程中发现注解式不适合业务复杂的，比如动态条件多表联查，建议使用XML方式。

### 4、SpringBoot整合Spring-Data-Jpa

Spring-Data-Jpa 功能完善，但是还是有缺陷，比如update不支持如果NULL则不更新，还有动态条件写起来比较复杂，个人感觉Jpa适合单表操作，很考验数据库设计水平。

### 5、SpringBoot整合JWT

Jwt用于信息访问授权功能，本案例使用Jwt生成授权token，访问特定方法进行token验证，并使用Redis存储敏感数据。

### 6、SpringBoot整合分布式锁

基于Redis、Redisson、Zookeeper的分布式锁。

### 7、SpringBoot整合JetCache

简单实现基于JetCache的数据缓存。

### 8、SpringBoot整合Shiro

实现Shiro的登录、登出、权限操作等简单功能。

### 9、SpringBoot整合Sharding-JDBC和MyBatis

简单根据取模0-1进行数据的分库分表，使用配置的方式进行操作。

### 10、SpringBoot整合MongoDB、ActiveMQ、Freemarker实现静态化页面

数据存储在MongoDB中，利用ActiveMQ的队列模式，监听到数据产生或者更改就立即对页面进行静态化输出。

### 11、SpringBoot使用AOP实现多数据源

Spring自带的AOP功能实现多数据源的配置。

### 12、SpringBoot整合Spring Session实现分布式Session

SpringBoot整合Spring Session，并将Session数据保存到Redis中，实现分布式Session。

### 13、SpringBoot整合elastic-job

SpringBoot整合elastic-job，实现分布式定时任务。

### 14、SpringBoot整合Druid Monitor

SpringBoot整合Druid、Postgresql、Mybatis，实现Druid对SQL语句的监控。

### 15、SpringBoot整合Kafka集群

SpringBoot整合Kafka集群，实现生产者、消费者消息的发送、监听。

