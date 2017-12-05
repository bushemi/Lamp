<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/lamp.png">
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" href="webjars/font-awesome/4.7.0/css">
    <link rel="stylesheet" href="/resources/core/my-style.css">
    <link rel="stylesheet" href="/resources/core/MyPage.css" />
    <link rel="stylesheet" href="/resources/core/style.css">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript" src="webjars/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="webjars/html5shiv/3.7.3/html5shiv.js"></script>
    <script type="text/javascript" src="/resources/core/MyPage.js"></script>



</head>
<body>

</body>
</html>
<input id="nav-toggle" type="checkbox" hidden="">
<nav class="nav">
    <label for="nav-toggle" class="nav-toggle" onclick=""></label>
    <h2 class="logo">
        <a href="#">BIG LOGO</a>
    </h2>
    <ul>
        <li><a id = "myPlaces" href="#">places</a></li>
        <li><a id = "myHobbies" href="#">hobbies</a></li>
        <li><a id = "myFriends" href="#">friends</a></li>
        <li><a id = "myPosts" href="#">myPosts</a></li>
        <li><a id = "allPosts" href="#">showAllPosts</a></li>
        <li><a id = "myMessages" href="#">messages</a></li>
        <li><a id = "exit"  href="#">exit</a></li>
    </ul>
</nav>
<main id="main" role="main">
    <article id = "my-info" class="person-info">
    </article>

    <article id = "my-places" class="info hidden">
    </article>

    <article id = "my-hobbies" class="info hidden">
    </article>

    <article id = "my-messages" class="info hidden">
    </article>

    <article id = "my-friends" class="info hidden">
    </article>

    <article id = "my-posts" class="info hidden">
    </article>

    <article id = "all-posts" class="info hidden">
    </article>

</main>
