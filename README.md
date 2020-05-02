# **Super Shop Management**
## Introduction: 
This project is about a super shop management system software using JAVA programming language. It is mainly windows based software. This management software is very helpful to manage different super shop related activity like entry and selling of goods or products, employee management. This project is done for our final project of Object Oriented Programming-1 course. 

## User Category: 
##### There are two types of Users here. They are: 
-	Admin 
-	Salesman 
## Feature List: 
 
##### In this project the “**Admin**” has the following features: 
- Can manage all username and password 
- Can edit and delete bill information 
- Manage employee and their salary 
##### In this project the “**Salesman**” has the following features: 
-	Add, Update and Delete product in product list 
-	Can change only his password 
-	Add, Update and Delete employee from employee list but can’t access the salary 
-	Can sale products to customer  
 
## GUI Description: 
# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/1.jpg?raw=true)
> **Fig:** ***Login page***, have two text field to enter user name and password and two buttons. After verifying the user id and password from database it opens home page or admin page determining the type of user.

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/2.jpg?raw=true)
> **Fig:** ***Home page***, contains 6 buttons to perform different operation. For example, product list button used to open product list window and logout for return to login page by logging out. 

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/3.jpg?raw=true)
> **Fig:** ***Product list*** window, here are the all information of the products with search, update, add, delete operation.

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/4.jpg?raw=true)
> **Fig:** ***Purchase*** window, here all the product list added form product list window. Salesman can add multiple product in cart with the customer information and can sell the products if the stock available. 

#  ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/5.jpg?raw=true)
> **Fig:** ***Change Password***, Here User can change only his password.  

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/6.jpg?raw=true)
> **Fig:** ***Admin page***, Here admin can access 3 operation can manage the bill  Information of products sold by the salesman, manage all user password,   name etc. and mange employee information like name, salary.

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/7.jpg?raw=true)
> **Fig:** ***Bill Info***, this window is accessed by admin and can manage bill related information. 

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/8.jpg?raw=true)
> **Fig:** ***User data***, Here admin can add, update and delete any user information. 

# ![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/9.jpg?raw=true)
> **Fig:** ***Employee Data***, Admin can manage employee information From this window like name, contact, salary and also use CRUD operation. 	 	 	 	 	  


## **Database Table Description:**
![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/10.jpg?raw=true)
> **Fig:** User Table (Login)

![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/11.jpg?raw=true)
> **Fig:** Product List Table

![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/12.jpg?raw=true)
> **Fig:** Customer Table 

![Solid](https://github.com/fahimm98/Super-Shop-Management/blob/master/image/ScreenShoot/13.jpg?raw=true)
> **Fig:** Employee Table 

### Tools Used: 
To develop this project we have used the following: 
-	[JDK ( Java Development Kit)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 
-	[Eclipse IDE](https://www.eclipse.org) 
-	[MySQL](https://www.mysql.com) 
-	[Xampp](https://www.apachefriends.org/download.html) 

### OOP and Java Concepts Used: 
 
-	**Association**: Association is mostly used almost every class in this project to make a relation between two separate class through their objects.  
-	**Swing and AWT:** Swing and AWT both are used to design the GUI of the project. 
-	**Abstraction:** Abstraction means using simple things to represent complexity. This OOP concept is used by making different method, objects with specific operation. 
-	**Encapsulation:** Different objects, classes, variables and methods are kept in protective barrier using different type of access modifier to give particular access to particular user of field. 
-	**Polymorphism:** It is used to overload the method that is used in the classes to make easy to do different kind of job by calling different method with same name. 
-	**Package:** Package is a namespace that organizes a set of related classes and interfaces. This concept is used to organize the classes used in this project. 

### Limitations and Possible Future Improvements: 
This management software has some limitation. It can be improved for Future like Customer can search the products without any login, the bill details will generate automatically and also employee attendance and salary management will also include, some improvement in GUI will perform. 
