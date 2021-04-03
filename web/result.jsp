<%-- 
    Document   : history
    Created on : Feb 17, 2021, 8:51:29 PM
    Author     : Khoa Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <style> <%@include file="css/user_home.css" %></style>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT.role != 'student'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <header>
            <h1>Welcome, ${sessionScope.ACCOUNT.fullname} </h1>
            <form action="logout" method="POST">
                <input type="submit" value="Log Out" />
            </form>

            
            <c:set var="correctAns" value="${requestScope.CORRECT_ANSWERS}"/>
            <c:set var="mark" value="${requestScope.MARKS}"/>
            
            <table border="1" style="margin: 0">
                <thead>
                    <tr>
                        <th>Number Of Correct</th>
                        <th>Marks</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${correctAns}</td>
                        <td>${mark}</td>
                    </tr>
                </tbody>
            </table>
        <form action="userPage">
            <input type="submit" value="Home Page"/>
        </form>
    </body>
</html>

