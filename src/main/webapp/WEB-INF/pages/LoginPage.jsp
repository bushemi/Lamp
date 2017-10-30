<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/login.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue</h1>
            <div class="account-wall">
                <img class="profile-img" src="/resources/images/lamp.png" alt="">
                <div class="form-signin">
                    <input type="text" id="login" class="form-control" placeholder="login" required autofocus>
                    <input type="password" id="password" class="form-control" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitLogin">
                        Sign in</button>
                    <span class="clearfix"></span>

                </div>
            </div>
            <a href="registration" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/LoginPage.js"></script>

</body>
</html>