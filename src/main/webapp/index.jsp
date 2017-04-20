<%-- 
    Document   : index
    Created on : 18-Apr-2017, 16:25:27
    Author     : eeu67d
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Academi Gymraeg: Home</title>
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
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.js"></script>

        <br><br>
        
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
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <c:if test="${!empty user}">
                            <li><a href="MenuServlet">Quiz Menu</a></li>
                            <li><a href="ProfileServlet">View Profile</a></li>
                        </c:if>
                        <c:if test="${userType == 2}">
                            <li><a href="DictionaryServlet">Edit Dictionary</a></li>
                        </c:if>
                    </ul>
                    <c:choose>
                        <c:when test="${empty user}">
                            <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#login-modal" style="margin-right:10px">Sign In</button>
                            <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#register-modal" style="margin-right:10px">Register</button>
                        </c:when>
                        <c:otherwise>
                            <p class="navbar-text">Welcome, ${user}</p>
                            <a type="button" href="LogoutServlet" class="btn btn-default navbar-btn navbar-right" style="margin-right:10px">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
        
        <!-- Start login script -->
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" style="display: none;">
            <div class="modal-dialog">
                <div class="usermodal-container">
                    <h1>Login to Your Account</h1><br>
                    <form action="LoginServlet" method="POST">
                        <input type="text" name="user" placeholder="Username">
                        <input type="password" name="pass" placeholder="Password">
                        <input type="submit" name="login" class="usermodal-submit" value="Login">
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Start register script -->
        <div class="modal fade" id="register-modal" tabindex="-1" role="dialog" style="display: none;">
            <div class="modal-dialog">
                <div class="usermodal-container">
                    <h1>Register a New Account</h1><br>
                    <form action="RegisterServlet" method="POST">
                        <input type="text" name="user" placeholder="Username">
                        <input type="password" name="pass" placeholder="Password">
                        <input type="submit" name="register" class="usermodal-submit" value="Register">
                    </form>
                </div>
            </div>
        </div>
        
        <!-- add text to page so it's not empty -->
        <h3>${message}</h3>
        <div align="center">
            <h1>WELCOME TO ACADEMI GYRAEG</h1>
        </div>
    </body>
</html>
