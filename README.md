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
