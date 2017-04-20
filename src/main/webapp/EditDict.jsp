<%-- 
    Document   : EditDict
    Created on : 18-Apr-2017, 15:47:35
    Author     : eeu68b
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
    A user with teacher security can use this page, all other user types will
    be redirected back to the index page. 
    Words can be created, edited or deleted from the database using the form.
-->
<html>
    <head>
        <title>Academi Gyraeg: Edit Dictionary</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="manifest" href="/manifest.json">
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="css/snippets.css" type="text/css">
        <link rel="stylesheet" href="css/custom.css" type="text/css">
    </head>

    <body>
        <!-- Start Navigation Bar script -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Academi Gymraeg</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Home</a></li>
                        <c:if test="${userType == 0}">
                            <li><a href="MenuServlet">Quiz Menu</a></li>
                        </c:if>
                        <c:if test="${userType == 1}">
                            <li class="active"><a href="DictionaryServlet">Edit Dictionary</a></li>
                        </c:if>
                        <li><a href="ProfileServlet">View Grades</a></li>
                    </ul>
                    <p class="navbar-text">Welcome, ${user}</p>
                    <a type="button" href="LogoutServlet" class="btn btn-default navbar-btn navbar-right" style="margin-right:10px">Logout</a>
                </div>
            </div>
        </nav>

        <form action="EditWordServlet" method="POST">
            <div class="container" style="margin-top:50px">
                <div class="panel panel-default">
                    <div class="panel-body" style="padding:0px">
                        <table class="table table-striped table-bordered" style="margin:0px">
                            <thread>
                                <tr>
                                    <th>Word ID</th>
                                    <th>English</th>
                                    <th>Welsh</th>
                                    <th>Type</th>
                                    <th>Gender</th>
                                    <th><input type="radio" name="wordID" value="new" checked="true"> New Word</th>
                                </tr>
                            </thread>
                            <tbody>
                                <c:forEach items="${words}" var="word">
                                    <tr>
                                        <c:forEach items="${word}" var="item">
                                            <td>
                                                ${item}
                                            </td>
                                        </c:forEach>
                                        <td>
                                            <input type="radio" name="wordID" value="${word[0]}"> Select
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <input type="hidden" name="url" value="dictionary">
            <input type="submit" class="btn btn-info" name="submit" value="Submit">
            <input type="submit" class="btn btn-info" name="submit" value="Delete">
        </form>
    </body>
</html>

