<%-- 
    Document   : ViewProfile
    Created on : 18-Apr-2017, 17:26:40
    Author     : eeu68b
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Academi Gyraeg: View Profile</title>
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
                        <!-- TODO: Organise web pages into nav links -->
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="MenuServlet">Quiz Menu</a></li>
                        <li><a href="EditDict.jsp">Edit Dictionary</a></li>
                        <li active="true"><a href="ProfileServlet">View Profile</a></li>
                    </ul>
                    <p class="navbar-text">Welcome, ${user}</p>
                </div>
            </div>
        </nav>
        
        <!-- code for tables -->
        <div clas="container" style="margin-top:50px">
        <div class="panel panel-default">
        <div style="margin:7px">
            <div class="col-xs-6 pull-right form-group">
                <!-- Search bar for teacher to input student username - disabled by default, enabled when security is teacher. -->
                <input type="text" class="form-control" style="border-radius:0px" placeholder="Search" disabled>
            </div>
        </div>
       
            <div class="panel-body" style="padding:0px">
                <table class="table table-striped table-bordered" style="margin:0px">
                    <thread>
                        <tr>
                            <th>Test ID</th>
                            <th>Result</th>
                            <th>Out Of</th>
                            <th>Date</th>
                        </tr>
                    </thread>
                    <tbody>
                    <td></td>
                    </tbody>
                    </table>
            </div>
            
            <div class="panel-footer">
                <div class="col-xs-3"><div class="dataTables_info" id="example_info"> Showing 51-60 of 100 total results</div></div>
                <div class="col-xs-6">
                    <div class="dataTables_paginate paging_bootstrap">
                        <ul class="pagination pagination-sm" style="margin:0 !important">
                            <li class="prev disabled"><a href="#"> <-- Previous </a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li class="next disabled"><a href="#">Next --></a></li></ul>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown">
                        10<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" style="min-width: 0px">
                        <li><a href="#">5</a></li>
                        <li class="active"><a href="#">10</a></li>
                        <li><a href="#">15</a></li>
                        <li><a href="#">15</a></li>
                    </ul>
                    <span>
                        Items per page
                    </span>
                </div>
            </div>
        </div>
    </body>
</html>
