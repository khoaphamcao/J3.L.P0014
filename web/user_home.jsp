<%-- 
    Document   : user_home
    Created on : Feb 17, 2021, 7:20:42 PM
    Author     : Khoa Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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

            <form class="searchform cf" action="getQuestionToExam" method="POST">

                <h2>Wanna To Check Your Brain :D</h2>
                <font color="black">
                Subject
                </font>
                <select name="subject">
                    <c:set var="subjectOption" value="${param.subject}"/>
                    <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                        <option value="${subject.idSubject}" <c:if test="${subject.idSubject == subjectOption}">selected</c:if>>${subject.idSubject}</option>
                    </c:forEach>
                </select>
                <button type="submit" style="align-items: center">Take a Quiz</button>
            </form>
        </header>
        <div class="center">
            <form action="history" method="POST">
                <input type="submit" value="View Your Work" />
            </form>
        </div>
    </body>
</html>