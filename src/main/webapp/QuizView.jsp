<%-- 
    Document   : QuizView
    Created on : 18-Apr-2017, 16:47:19
    Author     : eeu67d
--%>

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
                        <li><a href="MenuServlet">Quiz Menu</a></li>
                        <li><a href="EditDict.jsp">Edit Dictionary</a></li>
                        <li><a href="ProfileServlet">View Profile</a></li>
                    </ul>
                    <c:choose>
                        <c:when test="${empty user}">
                            <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#login-modal" style="margin-right:10px">Sign In</button>
                            <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#register-modal" style="margin-right:10px">Register</button>
                        </c:when>    
                        <c:otherwise>
                            <p class="navbar-text">Welcome, ${user}</p>
                        </c:otherwise>
                    </c:choose>
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
