<%-- 
    Document   : listUser
    Created on : 4/Ago/2014, 16:58:06
    Author     : nelio_saturnino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show All Users</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phones</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.phones}</td>
                        <td><a href="GetUpdateUserServlet?id=<c:out value="${user.id}"/>">Update</a></td>
                        <td>
                            <form method="POST" action="DeleteUserServlet">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                                <input type="submit" value="Delete" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="GetCreateUserServlet">Add User</a></p>
    </body>
</html>
