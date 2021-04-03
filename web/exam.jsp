<%-- 
    Document   : exam
    Created on : Feb 18, 2021, 7:26:12 PM
    Author     : Khoa Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
        <style><%@include file="css/exam.css" %></style>
        <script>
            function counterTime() {
                var time = document.getElementById("remainingTimeText").value;
                var min = Math.floor(time / 60);
                var sec = Math.floor(time % 60);
                document.getElementById("timer").innerHTML = min + " : " + sec;

                var x = window.setInterval(timerFunction, 1000);

                function timerFunction() {
                    var time = document.getElementById("remainingTimeText").value;
                    time--;
                    var min = Math.floor(time / 60);
                    var sec = Math.floor(time % 60);
                    // Display the result in the element with id="demo"
                    document.getElementById("remainingTimeText").value = time;
                    if (time <= 0) {
                        clearInterval(x);
                        document.getElementById("timer").innerHTML = "00 : 00";
                        window.location.href = "submit";
                    }
                    document.getElementById("timer").innerHTML = min + " : " + sec;
                }
            }
            window.onload = counterTime();
        </script>
    </head>
    <body onload="counterTime()">
        <c:if test="${sessionScope.ACCOUNT.role != 'student'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        
        <header>
            <div>
                <h2> Quiz Online : ${sessionScope.ID_SUBJECT}</h2>
            </div>
        </header>

        <div class="text-center">
            <div class="row">
                <div class="col-12">
                    <form action="submit" method="POST">
                        
                        <h3><span class="border border-primary" id="timer" ></span></h3>
                        <input type="submit" id="button-submit" class="btn btn-outline-secondary" name="btAction" value="Submit" onclick="return confirm('Do you want to submit?')" />
                    </form>
                </div>
            </div>
        </div>

        <c:set var="question" value="${sessionScope.QUESTION}"/>
        <c:set var="answerList" value="${sessionScope.STUDENT_ANSWER_LIST}"/>
        <c:set var="questionNumber" value="${requestScope.CURRENT_QUESTION}"/>




        <div class="container">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 col-lg-10">
                    <div class="border" id="question-form">
                        <form action="nextQuestion">
                            <input type="hidden" id="remainingTimeText" name="time"value="${sessionScope.TIME}">
                            <div class="d-flex flex-row align-items-center question-title">
                                <h3 class="text-danger">Q. ${questionNumber + 1}</h3>
                            </div>
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>Answer</th>
                                        <th>Question : ${question.question_Content}
                                            <input type="hidden" name="idQuest" value="${question.id_Question}" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td> 
                                            <div class="ml-3">
                                                A<input type="radio" name="answer" value="A" <c:if test="${answerList.get(questionNumber) == 'A'}">checked</c:if> /> 
                                                </div>
                                                <div class="ml-3">
                                                    B<input type="radio" name="answer" value="B" <c:if test="${answerList.get(questionNumber) == 'B'}">checked</c:if> /> 
                                                </div>
                                                <div class="ml-3">
                                                    C<input type="radio" name="answer" value="C" <c:if test="${answerList.get(questionNumber) == 'C'}">checked</c:if> /> 
                                                </div>
                                                <div class="ml-3">
                                                    D<input type="radio" name="answer" value="D" <c:if test="${answerList.get(questionNumber) == 'D'}">checked</c:if> /> 
                                                </div>
                                            </td>
                                            <td>
                                                <div>${question.answer_Content_1}
                                                </div>
                                                <div>${question.answer_Content_2}
                                                </div>
                                                <div>${question.answer_Content_3}
                                                </div>
                                                <div>${question.answer_Content_4}
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>


                                <div class="float-right">
                                    <input type="hidden" id="timeRemaining" name="timeRemain" value="${sessionScope.TIME}" />
                                <c:if test="${questionNumber == (answerList.size() - 1)}">
                                    <input type="hidden" name="questionNumber" value="0" />
                                </c:if>
                                <c:if test="${questionNumber != (answerList.size() - 1)}">
                                    <input type="hidden" name="questionNumber" value="${questionNumber + 1}" />
                                </c:if>
                                <input type="hidden" name="currentQuestion" value="${questionNumber}" />
<!--                                <input type="submit" id="button-next" class="btn btn-primary border-success" value="Next" />-->
                            </div> <br/>
                            <hr> <br/>
                            <div id="question-list">
                                <nav>
                                    <ul class="pagination nav justify-content-center">
                                        <c:forEach var="i" begin="1" end="${answerList.size()}">
                                            <c:choose>
                                                <c:when test="${(questionNumber + 1) == i}">
                                                    <input type="button" class="btn btn-outline-secondary" value="${i}" disabled style="margin: 5px"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="submit" class="btn btn-outline-secondary" value="${i}" name="questNum" style="margin: 5px"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div>
                        </form> 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
