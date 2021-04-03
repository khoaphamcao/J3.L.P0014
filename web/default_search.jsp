<%-- 
    Document   : default_search
    Created on : Feb 16, 2021, 9:18:37 PM
    Author     : Khoa Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <style> <%@include file="css/search.css" %></style>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT.role != 'admin'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <header>
            <h1>Welcome, ${sessionScope.ACCOUNT.fullname} </h1>
            <form action="logout" method="POST">
                <input type="submit" value="Log Out" />
            </form>

            <form class="searchform cf" action="search" method="POST">
                <h2>Search question</h2>

                <div>
                    <font color="black">
                    Status
                    </font>

                    <select name="status">
                        <c:set var="statusOption" value="${param.status}"/>
                        <option <c:if test="${statusOption == 'All'}" >selected</c:if>>All</option>
                        <option <c:if test="${statusOption == 'Active'}" >selected</c:if>>Active</option>
                        <option <c:if test="${statusOption == 'DeActive'}" >selected</c:if>>DeActive</option>
                        </select>
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
                </div> <br/>
                <input type="text" placeholder="Is it me you’re looking for?" name="searchValue" value="${param.searchValue}">

                <button type="submit">Search</button>
            </form>

        </header>


        <form action="createPage" method="POST" >
            <input type="submit" value="Create Question" />
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Question</th>
                    <th>True Answer</th>
                    <th>Status</th>
                    <th>Subject</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <c:forEach var="list" items="${requestScope.LIST_QUEST}">
                <tbody>
                <form action="action" method="POST">
                    <td>
                        <p>${list.id_Question}</p>
                        <input type="hidden" name="id_question" value="${list.id_Question}"/>
                    </td>
                    <td>
                        <p>${list.question_Content}</p>
                        <input type="hidden" name="txtQuestionContent" value="${list.question_Content}" />
                        <p>${list.answer_Content_1}</p>
                        <input type="hidden" name="txtAnswer1" value="${list.answer_Content_1}" />

                        <p>${list.answer_Content_2}</p>
                        <input type="hidden" name="txtAnswer2" value="${list.answer_Content_2}" />

                        <p>${list.answer_Content_3}</p>
                        <input type="hidden" name="txtAnswer3" value="${list.answer_Content_3}" />

                        <p>${list.answer_Content_4}</p>
                        <input type="hidden" name="txtAnswer4" value="${list.answer_Content_4}" />

                    </td>
                    <td style="text-align: center">${list.answer_Correct}
                        <input type="hidden" name="txtAnswerCorrect" value="${list.answer_Correct}" />
                    </td>
                    <td>${list.status}
                        <input type="hidden" name="txtStatus" value="${list.status}" />
                    </td>
                    <td>${list.id_Subject}
                        <input type="hidden" name="txtStatus" value="${list.id_Subject}" />
                    </td>
                    <td>
                        <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="return confirm('Do you want to Delete this Question ?')" name="btAction" value="Delete">
                            Delete
                        </button>
                        <input type="hidden" name="txtSearchValue" value="${param.searchValue}" />
                    </td>
                    <td> 
                        <input type="submit" name="btAction" value="Update" />
                        <input type="hidden" name="idQuestion" value="${id_question}"/>
                        <input type="hidden" name="txtSearchValue" value="${param.searchValue}" />
                    </td>
                </form>
            </tbody>
        </c:forEach>
    </table>
    <div class="paging">
        <c:forEach var="i" begin="1" end="${requestScope.END_PAGE}">
            <a href="DefaultSearchServlet?index=${i}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>

