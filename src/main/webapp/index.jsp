<%-- 
    Document   : index
    Created on : 18-Apr-2017, 16:25:27
    Author     : eeu67d
--%>

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

        <h3>${message}</h3>
        
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
                        <!-- TODO: Organise web pages into nav links -->
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <li><a href="MenuServlet">Quiz Menu</a></li>
                        <li><a href="EditDict.jsp">Edit Dictionary</a></li>
                        <li><a href="ProfileServlet">View Profile</a></li>
                    </ul>
                    <!-- Login in button. TODO: Slightly shift to left? -->
                    <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#login-modal">Sign In</button>
                    <button type="button" class="btn btn-default navbar-btn navbar-right" data-toggle="modal" data-target="#register-modal">Register</button>
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
                        <input type="submit" name="login" class="usermodal-submit" value="Register">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
