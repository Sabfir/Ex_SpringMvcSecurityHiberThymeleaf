Loopme - test task
===============================
Spring MVC + Spring Security + Hibernate + Thymeleaf + Embedded Database (HSQLDB).

###1. Technologies used
* Java 1.8
* Maven 3.0
* Spring 4.1.6.RELEASE
* Spring Security 4.0.2.RELEASE
* Hibernate 4.3.5 Final
* HSQLDB 2.3.2
* Thymeleaf 2.1.3.RELEASE
* Bootstrap

###2. To Run this project locally
```shell
$ git clone https://github.com/Sabfir/Ex_SpringMvcSecurityHiberThymeleaf.git
$ cd Ex_SpringMvcSecurityHiberThymeleaf
$ mvn jetty:run
```
Access http://localhost:8080/

###3. Credentials and roles to access app

|   ROLE	            |   LOGIN               |   PASSWORD
|   --------------------|  :-------------------:|  :-------------------:|
|   ROLE_PUBLISHER		|   Publisher_Ivanov    |   Publisher_Ivanov    |
|   ROLE_OPERATOR		|   Operator_Petrov     |   Operator_Petrov     |
|   ROLE_ADMINISTRATOR	|   Admin_Sidorov       |   Admin_Sidorov       |

###4. To Stop the project
```shell
$ Ctrl+C
```

###4. Task Description

Цель тестового задания:
Оценить уровень знаний кандидата по следующим технологиям:
1.	Template engine (Thymeleaf предпочтительно) (Можно использовать angular(2))
2.	Bootstrap
3.	Js
4.	Spring security
5.	Spring MVC
6.	Hibernate (база данных значения не имеет)

Задание
Создать вебприложение для регистрации и настройки параметров приложений или вебсайтов с которых приходит запрос на рекламу.
Пользователи приложения: Паблишеры (клиенты компании), Операторы (сотрудники компании), Администратор (сотрудник компании)
Описание: Оператор регистрирует в системе паблишера. Паблишер регистрирует в системе приложения/сайты и настраивает параметры своего приложения (смотри схему). Паблишер может видеть и редактировать только свои приложения. Оператор может видеть все приложения.

Матрица доступа:

|   Операция	            |   Администратор   |   Оператор    |   Паблишер    |
|   ------------------------|  :---------------:|  :-----------:|  :-----------:|
|   создать паблишера		|   х	            |   х          	|   -           |
|   редактировать паблишера	|   х	            |   х	        |   -           |
|   удалить паблишера		|   х	            |   х	        |   -           |
|   создать Оператора		|   х	            |   -	        |   -           |
|   редактировать Оператора	|   х	            |   -	        |   -           |
|   удалить Оператора		|   х	            |   -	        |   -           |
|   создать приложение		|   -	            |   х	        |   х           |
|   обновить приложение		|   -	            |   х	        |   х           |
|   удалить приложение		|   -	            |   х       	|   х           |

Внешнее оформление на усмотрение кандидата
