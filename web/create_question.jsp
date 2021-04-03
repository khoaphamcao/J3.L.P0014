<%-- 
    Document   : create_question
    Created on : Feb 1, 2021, 9:04:34 AM
    Author     : Khoa Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <title>Create Page</title>
        <style> <%@include file="css/search.css" %></style>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT.role != 'admin'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <form action="create" method="POST">

            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
            <div class="container">
                <h2 class="text-center">Question Form</h2>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-8 col-lg-6 pb-5">


                        <!--Form with header-->

                        <div class="card border-primary rounded-0">
                            <div class="card-header p-0">
                                <div class="bg-info text-white text-center py-2">
                                    <h3><i class="fa fa-envelope"></i> Quiz</h3>
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
                                        <textarea class="form-control"  rows="3" placeholder="Question Content" name="txtQuestionContent" required></textarea>
                                    </div>
                                </div>

                                <!--Answer 1-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">A&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 1" name="txtAnswer1" required></textarea>
                                    </div>
                                </div>

                                <!--Answer 2-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">B&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 2" name="txtAnswer2" required></textarea>
                                    </div>
                                </div>


                                <!--Answer 3-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">C&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 3" name="txtAnswer3" required></textarea>
                                    </div>
                                </div>

                                <!--Answer 4-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">D&nbsp;<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        <textarea class="form-control"  rows="3" placeholder="Answer 4" name="txtAnswer4" required></textarea>
                                    </div>
                                </div>


                                <!--Right Answer-->
                                <table border="2" style="width: 100%">
                                    <thead>
                                        <tr style="text-align:center">
                                            <th>True Answer</th>
                                            <th>Subject</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr style="text-align:center">
                                            <td><select name="cbTrueAnswer" >
                                                    <option>A</option>
                                                    <option>B</option>
                                                    <option>C</option>
                                                    <option>D</option>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="cbSubject">
                                                    <c:forEach var="subject" items="${sessionScope.SUBJECT}">
                                                        <option>${subject.idSubject} - ${subject.subjectName}</option>
                                                    </c:forEach>
                                                </select>       
                                            </td>
                                        </tr>
                                    </tbody>
                                </table> <br/>
                                <div style="text-align: center">
                                    <font color="red">
                                    ${requestScope.NOTI}
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
