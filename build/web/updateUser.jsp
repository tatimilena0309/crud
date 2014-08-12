<%-- 
    Document   : updateUser.jsp
    Created on : 5/Ago/2014, 10:21:55
    Author     : nelio_saturnino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/validations.js"></script>
        <script src="js/buttons.js"></script>
    </head>
    <body>
        <form name="formUpdateUser" action="PostUpdateUserServlet" method="POST">
            <input type="hidden" name="id" value="${user.id}"/>
            
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
                <c:forEach items="${user.phones}" var="phone">
                    <div>
                        <label for="phone">Phone : </label>
                        <input class="phone" type="text" name="phone" value="${phone}"/>
                        <span class="phone">${errors.phones}</span>
                    </div>
                </c:forEach>
            </div>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
