<html>
<head>
    <title>Registration</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/Registration.css">

    <%--<jsp:useBean id="personInfo" scope="session"--%>
                 <%--type="com.bushemi.model.PersonInfo" />--%>
</head>
<body>
<div class="container">
    <div id="login-form" class="row window">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Registration</h1>
            <div class="account-wall">
                <img class="profile-img" src="/resources/images/lamp.png" alt="">
                <div class="form-signup">
                    <input type="text" id="login" class="form-control" placeholder="Login" required autofocus>
                    <input type="password" id="password" class="form-control" placeholder="Password" required>
                    <input type="email" id="email" class="form-control" placeholder="Email" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit-reg">
                        New User Registration</button>

                    <span class="clearfix"></span>
                </div>
            </div>
            <a href="login" class="text-center new-account">Already registered</a>
        </div>
    </div>
    <div id="credentials-form" class="window hidden">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div  class="info">
                <input type="text" id="first-name" class="form-control" placeholder="first-name" required autofocus>
                <input type="text" id="last-name" class="form-control" placeholder="last-name" required>
                <input type="date" id="birthday" class="form-control" placeholder="birthday(YYYY-MM-DD)" required>
                <input type="text" id="nickname" class="form-control" placeholder="nickname" required>
                <input type="text" id="photo" class="form-control" placeholder="urlphoto">
                Url to your photo is unnecessary. You use link to your own photo is for your own risk.
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit-info">
                    Add User Info</button>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/Registration.js"></script>

</body>
</html>