<%-- 
    Document   : EditDict
    Created on : 18-Apr-2017, 15:47:35
    Author     : eeu68b
--%>

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
                        <li><a href="MenuServlet">Quiz Menu</a></li>
                        <li class="active"><a href="EditDict.jsp">Edit Dictionary</a></li>
                        <li><a href="ProfileServlet">View Profile</a></li>
                    </ul>
                    <p class="navbar-text">Welcome, ${user}</p>
                </div>
            </div>
        </nav>
        
       <!--Submit Buttons Created -->
        <div class="container">
            <br>
            <h2> Dictionary Editor</h2>
            <form>
                <!-- error check for NOT NULL and Single characters -->
                Select One: <!--First choose is edit, new or delete --><br>
                <input type="radio" name="option" value="new" checked> New Entry 
                <input type="radio" name="option" value="edit"> Edit Entry
                <input type="radio" name="option" value="delete"> Delete Entry<br><br>
                
                Word ID: <!--disabled until delete button is checked --> <br>
                <input type="text" name="wordIDtb" disabled><br><br>
                
                <!-- Lookup words for entries of word is checked -->
                
                English: <br>
                <input type="text" name="english"><br><br>
                
                Welsh: <br>
                <input type="text" name="welsh"><br><br>
                
                Word Type:<br>
                <input type="radio" name="type" value="adjectives"> Adjectives
                <input type="radio" name="type" value="noun" checked> Noun<br><br>
                
                Word Gender:<br>
                <input type="radio" name="gender" value="female"> Female
                <input type="radio" name="gender" value="male"> Male
                <input type="radio" name="gender" value="neutral" checked> Neutral<br><br>
                
                
            </form>
            <!-- reference needed to delete and submit-->
            <input type="submit" class="btn btn-info" value="Submit">
            
        </div>
    </body>
</html>

