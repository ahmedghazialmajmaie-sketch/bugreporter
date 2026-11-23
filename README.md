#  1. Used Tools 
Springboot 4.0.8

MySql Server 8.4

Java Version: 25 

---
#  2. Database Schema:

```sql
create table logcategories  
(  
    log_category_id int auto_increment  
        primary key,  
    Log_Category    varchar(30) not null  
);  
  
create table users  
(  
    user_id               int auto_increment  
        primary key,  
    user_email            varchar(40)  not null,  
    password_hashed       varchar(255)  not null,  
    username              varchar(15)  not null,  
    profile_picture_path  varchar(200) null,  
    user_account_status   tinyint(1)   not null,  
    account_creation_date datetime     not null,  
    constraint Users_email_ak  
        unique (user_email),  
    constraint Users_username_ak  
        unique (username)  
);  
  
create table administrators  
(  
    user_id int not null  
        primary key,  
    constraint Administrators_Users_user_id_fk  
        foreign key (user_id) references users (user_id)  
);  
  
create table logs  
(  
    log_category_id int          not null,  
    user_id         int          not null,  
    log_content     varchar(200) null,  
    log_date        datetime     not null,  
    log_id          bigint auto_increment  
        primary key,  
    constraint Logs_Users_user_id_fk  
        foreign key (user_id) references users (user_id),  
    constraint logs_logcategories_log_category_id_fk  
        foreign key (log_category_id) references logcategories (log_category_id)  
);  
  
create table managers  
(  
    user_id int not null  
        primary key,  
    constraint Managers_Users_user_id_fk  
        foreign key (user_id) references users (user_id)  
);  
  
create table notifications  
(  
    notification_content varchar(100) not null,  
    notification_title   varchar(100) not null,  
    notification_id      int auto_increment  
        primary key,  
    user_id              int          not null,  
    notification_date    datetime     not null,  
    constraint Notifications_Users_user_id_fk  
        foreign key (user_id) references users (user_id)  
);  
  
create table projects  
(  
    project_id         int auto_increment  
        primary key,  
    project_name       varchar(50) null,  
    project_version    varchar(15) not null,  
    project_manager_id int         null,  
    constraint Projects_ak  
        unique (project_name),  
    constraint Projects_Managers_user_id_fk  
        foreign key (project_manager_id) references managers (user_id)  
);  
  
create table bugs  
(  
    bug_id            int auto_increment  
        primary key,  
    priority          varchar(10)   not null,  
    severity          varchar(10)   not null,  
    project_id        int           not null,  
    bug_status        varchar(10)   not null,  
    creation_date     datetime      not null,  
    last_update_date  datetime      null,  
    OS                varchar(10)   not null,  
    reporter_id       int           not null,  
    bug_summery       varchar(50)   not null,  
    replication_steps varchar(500)  null,  
    bug_description   varchar(1000) null,  
    bug_type          varchar(10)   null,  
    constraint Bugs_Projects_project_id_fk  
        foreign key (project_id) references projects (project_id),  
    constraint Bugs_Users_user_id_fk  
        foreign key (reporter_id) references users (user_id)  
);  
  
create table comments  
(  
    bug_id              int           not null,  
    comment_content     varchar(1000) not null,  
    commenter_id        int           not null,  
    comment_date        datetime      not null,  
    comment_update_date datetime      null,  
    comment_id          int           not null  
        primary key,  
    constraint Comments_Bugs_bug_id_fk  
        foreign key (bug_id) references bugs (bug_id),  
    constraint Comments_Users_user_id_fk  
        foreign key (commenter_id) references users (user_id)  
);  
  
create table components  
(  
    project_id     int         not null,  
    component_name varchar(50) not null,  
    primary key (project_id, component_name),  
    constraint Components_Projects_project_id_fk  
        foreign key (project_id) references projects (project_id)  
);  
  
create table developers  
(  
    user_id              int not null  
        primary key,  
    assigned_bug_id      int null,  
    developer_manager_id int null,  
    constraint Developers_Bugs_bug_id_fk  
        foreign key (assigned_bug_id) references bugs (bug_id),  
    constraint Developers_Managers_user_id_fk  
        foreign key (developer_manager_id) references managers (user_id),  
    constraint Developers_Users_user_id_fk  
        foreign key (user_id) references users (user_id)  
);  
  

```

<img width="1772" height="3348" alt="bugtracker-sql-diagram" src="https://github.com/user-attachments/assets/edc0c8e0-f262-40ff-b717-5e32ab96e21d" />

---


#  3. Project Dependencies:

| Lombock                                     | A Java library that uses compile-time annotations to automatically generate boilerplate code—such as getters, setters, constructors, equals(), hashCode(), and toString()—so I can write less repetitive code                                                                                                                                                                 |
| ------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **The official Java JWT (JJWT) repository** | An open-source library for creating, parsing, and verifying JSON Web Tokens (JWTs), JWSs, JWEs, and JWKs on the JVM and Android. I have implemented the spring security filter chain to generate and verify login tokens using this dependency manually instead of relying on an external service.                                                                            |
| **ModelMapper**                             | An open-source Java object-mapping library that automatically converts data between different object models—such as entities and Data Transfer Objects (DTOs)—using convention-based rules. ModelMapper reduces the boilerplate by automatically mapping my DTOs before sending them back tot he client, which protects my entities from being exposed to the client directly |
| ~~**spring-boot-starter-data-jdbc**~~       | The official Spring Boot starter that enables Spring Data JDBC, offering a lightweight, repository-based approach to relational databases without the complexity of a full ORM like Hibernate. **~~Currently not used; Kept only for future code rewrite.~~**                                                                                                                 |
| **mysql-connector-j**                       | The official JDBC Type 4 driver for MySQL, allowing Java applications to connect to MySQL servers and run SQL queries                                                                                                                                                                                                                                                         |
| **spring-boot-starter-validation**          | The official Spring Boot starter dependency that integrates Java Bean Validation (Jakarta Validation) using Hibernate Validator. It allows you to enforce data integrity rules declaratively using annotations instead of manual if-else checks.                                                                                                                              |
| **spring-boot-starter-security**            | The official starter dependency that adds Spring Security to a Spring Boot application for authentication, authorization, and protection against common exploits.                                                                                                                                                                                                             |
| **spring-boot-starter-data-jpa**            | A Spring Boot starter dependency that bundles everything needed to build a relational database persistence layer using Spring Data JPA and Hibernate.                                                                                                                                                                                                                         |
| **spring-boot-starter-webmvc**              | The official Spring Boot 4.0+ starter dependency for building traditional, Servlet-based web applications and RESTful APIs using Spring MVC and embedded Tomcat                                                                                                                                                                                                               |


