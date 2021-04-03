<%-- 
    Document   : update
    Created on : Feb 14, 2021, 2:05:39 PM
    Author     : Khoa Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Update Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT.role != 'admin'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <form action="update" method="POST">
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
            
            <input type="hidden" name="id_question" value="${param.id_question}" />
            
            <div class="container">
                <h2 class="text-center">Question Form</h2>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-8 col-lg-6 pb-5">

                        <!--Form with header-->
                        <div class="card border-primary rounded-0">
                            <div class="card-header p-0">
                                <div class="bg-info text-white text-center py-2">
                                    <h3><i class="fa fa-envelope"></i> Update Question</h3>
                                    <p class="m-0">Creating knowledge questions</p>
                                </div>
                            </div>
                            <div class="card-body p-3">

                                <!--Body-->
                                <!--Question content-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text"><i class="fas fa-question-circle"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Question Content" name="txtQuestionContent" required>${param.txtQuestionContent}</textarea>
                                    </div>
                                </div>

                                <!--Answer 1-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">A&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 1" name="txtAnswer1" required>${param.txtAnswer1}</textarea>
                                    </div>
                                </div>

                                <!--Answer 2-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">B&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 2" name="txtAnswer2" required>${param.txtAnswer2}</textarea>
                                    </div>
                                </div>


                                <!--Answer 3-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">C&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 3" name="txtAnswer3" required>${param.txtAnswer3}</textarea>
                                    </div>
                                </div>

                                <!--Answer 4-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">D&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 4" name="txtAnswer4" required>${param.txtAnswer4}</textarea>
                                    </div>
                                </div>


                                <!--Right Answer-->
                                <table border="3" style="width: 100%">
                                    <thead style="text-align:center">
                                        <tr>
                                            <th>True Answer</th>
                                            <th>Subject</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody style="text-align:center">
                                        <tr>
                                            <td>
                                                <select name="cbTrueAnswer" >
                                                    <c:set var="answerCorrect" value="${requestScope.QUESTION.answer_Correct}"/>
                                                    <c:forEach var="answer" items="${sessionScope.ANSWER}">
                                                        <option <c:if test="${answer == answerCorrect}">selected</c:if>>${answer}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="cbSubject">
                                                    <c:set var="questionSubject" value="${requestScope.QUESTION.id_Subject}"/>
                                                    <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                                                        <option <c:if test="${questionSubject == subject.idSubject}">selected</c:if>>${subject.idSubject} - ${subject.subjectName}</option>
                                                    </c:forEach>
                                                </select>       
                                            </td>
                                            <td>
                                                <select name="cbStatus">
                                                    <c:set var="questionStatus" value="${requestScope.QUESTION.status}"/>
                                                    <c:forEach var="status" items="Active,deActive">
                                                        <option <c:if test="${questionStatus == status}">selected</c:if>>${status}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table> <br/>
                                <div style="text-align: center">
                                    <font color="red">
                                    ${requestScope.UPDATE_NOTI}
                                    </font>
                                </div>

                                <!--Subject-->
                                <div class="text-center">
                                    <input type="submit" value="Submit" class="btn btn-info btn-block rounded-0 py-2">
                                </div>
                            </div>

                        </div>
                        <!--Form with header-->

                    </div>
                </div>
            </div>
        </form>
        <form action="defaultSearch" metho="POST">
            <div class="text-center">
                <input type="submit" value="Home page" class="btn btn-info btn-block rounded-0 py-2">
            </div>
        </form>
    </body>
</html>
