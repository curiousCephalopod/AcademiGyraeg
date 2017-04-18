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
        <h1>${quizName}</h1>
        Current question: ${current} out of ${outOf}.
        Previous answer was: ${answer}
        <form action="QuizServlet" method="POST">
            ${firstLabel}: ${question} <br>
            ${secondLabel}: <input type="text" name="answer">
            <input type="hidden" name="url" value="view">
            <input type="submit" name="next" class="btn" value="Next">
        </form>
    </body>
</html>
