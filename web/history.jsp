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
        <title>History Page</title>
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

            <form class="searchform cf" action="searchHistory" method="POST">
                <h2>Search Exam</h2>

                <font color="black">
                Subject
                </font>
                <select name="subject">
                    <c:set var="subjectOption" value="${param.subject}"/>
                    <option <c:if test="${param.subjectOption == 'All'}"> selected</c:if>>All</option>
                    <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                        <option value="${subject.idSubject}" <c:if test="${subject.idSubject == subjectOption}">selected</c:if>>${subject.idSubject}</option>
                    </c:forEach>
                </select> 

                <button type="submit">Search</button>
            </form>

        </header>



        <c:set var="listQuest" value="${requestScope.LIST_EXAM}"/>
        <c:if test="${not empty listQuest}">
            <h3>Your Exam</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Exam ID</th>
                        <th>Date</th>
                        <th>Subject</th>
                        <th>Marks</th>
                        <th>Grades</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${listQuest}">
                        <tr>
                            <td>${list.examID}</td>
                            <td>${list.date}</td>
                            <td>${list.subjectID}</td>
                            <td>${list.numberCorrect}</td>
                            <td>${list.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <c:if test="${empty listQuest}">
            <h1>Nothing in History Found!!</h1>
        </c:if>                   
        <div class="paging">
            <c:forEach var="i" begin="1" end="${requestScope.END_PAGE}">
                <a href="HistoryServlet?indexHistory=${i}&subject=${subjectOption}">${i}</a>
            </c:forEach>
        </div>
        <form action="userPage">
            <input type="submit" value="Home Page"/>
        </form>
    </body>
</html>

