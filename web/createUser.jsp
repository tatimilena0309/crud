<%-- 
    Document   : user
    Created on : 4/Ago/2014, 16:59:47
    Author     : nelio_saturnino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/validations.js"></script>
        <script src="js/buttons.js"></script>
    </head>
    <body>
        <form name="formCreateUser" action="PostCreateUserServlet" method="POST">
            <label for="name">Name : </label>
            <input class="name" type="text" name="name" value="${user.name}"/>  
            <span class="name">${errors.name}</span> <br>
            
            <label for="email">E-mail : </label>
            <input class="email" type="text" name="email" value="${user.email}"/>   
            <span class="email">${errors.email}</span> <br>
            
            <label for="password">Password : </label>
            <input class="password" type="password" name="password" value="${user.password}"/>
            <span class="password">${errors.password}</span> <br>
            
            <button class="add_phone" type="button">+</button>
            <button class="remove_phone" type="button">-</button>
            
            <div id="phones">
                <div class="phone">
                    <label for="phone">Phone : </label>
                    <input class="phone" type="text" name="phone" value="${user.phones}"/>
                    <span class="phone">${errors.phones}</span>
                </div>
            </div>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
