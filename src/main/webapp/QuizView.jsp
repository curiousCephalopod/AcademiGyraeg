<%-- 
    Document   : QuizView
    Created on : 18-Apr-2017, 16:47:19
    Author     : eeu67d
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Academi Gymraeg: Quiz View</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="manifest" href="/manifest.json">
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
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
                        <li class="active"><a href="MenuServlet">Quiz Menu</a></li>
                        </c:if>
                        <c:if test="${userType == 1}">
                        <li><a href="DictionaryServlet">Edit Dictionary</a></li>
                        </c:if>
                        <c:if test="${!empty user}">
                        <li><a href="ProfileServlet">View Grades</a></li>
                        </c:if> 
                    </ul>
                    <p class="navbar-text">Welcome, ${user}</p>
                    <a type="button" href="LogoutServlet" class="btn btn-default navbar-btn navbar-right" style="margin-right:10px">Logout</a>
                </div>
            </div>
        </nav>

        <br><br>
        <h1>${quizName}</h1>
        Current question: ${current} out of ${outOf}.
        Previous answer was: ${answer}
        <form action="QuizServlet" method="POST">
            ${firstLabel}: ${question} <br>
            ${secondLabel}: <input type="text" name="answer" autofocus>
            <input type="hidden" name="url" value="view">
            <input type="submit" name="next" class="btn" value="Next">
        </form>
    </body>
</html>
