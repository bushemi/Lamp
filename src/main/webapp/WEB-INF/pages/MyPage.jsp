<!DOCTYPE html >
<%@ page pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/my-style.css">
    <link rel="stylesheet" href="/resources/core/MyPage.css" media="screen">
    <link rel="stylesheet" href="/resources/core/style.css">


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Author">
    <meta name="description" content="Боковая панель с элементами меню, выдвигающаяся по клику, на чистом css, без использoвания javascript">
    <meta name="keywords" content="боковое меню, выдвижное меню, выдвигающееся по клику, боковая панеь, css3, html, css">
    <link rel="shortcut icon" href="/resources/images/lamp.png">

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <script src="/resources/core/MyPage.js"></script>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:useBean id="personInfo" scope="session"
                 type="com.bushemi.model.PersonInfo" />



</head>


<body>
<!--
Скрытый checkbox, отвечающий за переключение панели, должен быть в верхней части документа, лучше сразу после тега `<body>`

`id` атрибут предназначен для связки с атрибутом `for` тега <label>

Атрибут `hidden` указывает состояние «скрытый» у текущего элемента
-->
<input id="nav-toggle" type="checkbox" hidden="">
<!--
Выдвижную панель размещаете ниже
флажка (checkbox), но не обязательно
непосредственно после него, например
можно и в конце страницы
-->
<nav class="nav">
    <!--
Метка с именем `id` чекбокса в `for` атрибуте
Символ Unicode 'TRIGRAM FOR HEAVEN' (U+2630)
Пустой атрибут `onclick` используем для исправления бага в iOS < 6.0
См: http://timpietrusky.com/advanced-checkbox-hack
-->
    <label for="nav-toggle" class="nav-toggle" onclick=""></label>
    <!--
Здесь размещаете любую разметку,
если это меню, то скорее всего неупорядоченный список <ul>
-->
    <h2 class="logo">
        <a href="#">BIG LOGO</a>
    </h2>
    <ul>
    <%--<li><a id = "myInfo"  href="#">my info</a></li>--%>
    <li><a id = "myPlaces" onclick="showPlaces()" href="#">places</a></li>
    <li><a id = "myHobbies" onclick="showHobbies()" href="#">hobbies</a></li>
    <li><a id = "myFriends" onclick="showFriends()" href="#">friends</a></li>
    <li><a id = "myPosts" onclick="showPosts()" href="#">myPosts</a></li>
    <li><a id = "All posts" onclick="showAllPosts()" href="#">showAllPosts</a></li>
    <li><a id = "myMessages" onclick="showMessages()" href="#">messages</a></li>
    <li><a id = "exit" onclick="exit()" href="#">exit</a></li>
    </ul>
</nav>
<!--
Маска (затемнение) основного контента при включенной панели
по-умолчанию данная фишка не используется, если оно вам надо,
    просто расккоментируйте div-контейнер ниже
-->
<%--<div class="mask-content"></div>--%>


<!--
Далее размещаем любую разметку,
много слов, картинки и т.д...
-->
<main id="main" role="main">
    <%--<article id = "some-text">--%>
        <%--<header>--%>
            <%--<h1 class="header__title">Просто Демо:</h1>--%>
            <%--<h2>Выдвигающееся боковое меню на чистом CSS</h2>--%>
        <%--</header>--%>
        <%--<section id = "menuDescription" class="menuDescription">--%>
        <%--<p>Нажмите на "гамбургер-иконку" в левом или правом верхнем углу, в зависимости от выбранного вами варианта, и вы увидите выдвигающуюся боковую панель в действии. Чаще всего в таких панельках размещают меню навигации по сайту, что собственно мы и сделали. Вы же можете использовать боковую панель, для любого другого элемента, который по вашему разумению, должен быть изначально скрыт, например, форму подписки, блок кнопок социальных сетей, и т.д. и т.п...--%>
        <%--</p><p>Работа данного меню построена на чистом CSS, без подключения javascript. Переключение осуществляется с помощью скрытого флажка (checkbox) с использованием псевдокласса :checked из обоймы CSS3</p>--%>
            <%--<p>С переклюателем панели особо не стал заморачиваться и использовал символ Unicode 'TRIGRAM FOR HEAVEN' (U+2630), с простейшей заменой символа на 'Знак Умножения' (U+2715), когда панель раскрыта. Вам ничего не мешает испльзовать любой другой значок, иконку или просто текст.--%>
            <%--</p></section>--%>
        <%--<hr>--%>
    <%--</article>--%>
    <article id = "my-info" class="person-info">
        <img src='${personInfo.photoURL}' height="128" width="128">
        <header>
            <h4>my info</h4>
        </header>
        <section id ="my-description" class="description">
            information about me:
            <p>${personInfo.lastName} ${personInfo.nickname} ${personInfo.firstName}</p>
            <p>${personInfo.birthday}</p>
        </section>
    </article>

    <article id = "my-friends" class="info hidden">
        <header>
            <h4>my friends</h4>
        </header>
        <c:forEach var="friendshipInfo" items="${friendshipsInfo}">
        <section id ="my-friends-description" class="description">
            <p>The friendship between ${friendshipInfo.personNickname} and ${friendshipInfo.friendNickname}</p>
            <p>since ${friendshipInfo.friendFrom}</p>
            <button class="btn btn-primary" type="submit"
                    onclick="endFriendship('${personInfo.nickname}','${friendshipInfo.personNickname}','${friendshipInfo.friendNickname}')">
                End this friendship</button>
        </section>
        </c:forEach>
        <section>
            <select id="PeopleSelect" onchange="">
                <option value="">select person</option>
                <%--<option value="ANALYST">Analyzing Staff</option>--%>
                <c:forEach var="listOfPeople" items="${listOfPersons}">
                    <option title='${listOfPeople.firstName} ${listOfPeople.lastName}' value='${listOfPeople.id}'>
                        <%--<img src ="${listOfPeople.photoURL}" class="icon" alt="/resources/images/monkey.png">--%>
                            ${listOfPeople.nickname}</option>
                </c:forEach>
            </select>
            <%--<input type="text" id="textFriendship" class="form-control" placeholder="potential friend" >--%>
            and
            <button class="btn btn-primary" type="submit" id="Friends" onclick="makeFriend('${listOfPeople.nickname}')">Friend it!</button>
        </section>

    </article>


    <article id = "my-places" class="info hidden">
        <header>
            <h4>my places</h4>
        </header>
        <c:forEach var="placeInfo" items="${placesInfo}">
        <section id ="my-places-description" class="description">
            <p> ${placeInfo.title}</p>
            <p> ${placeInfo.description}</p>
            <p> ${placeInfo.latitude}, ${placeInfo.longitude}</p>
        </section>
        </c:forEach>
        <section class="new-item">
            If you want to add your Place you can choose one of the ours places
            <select id="PlaceSelect" onchange="">
                <option value="-1">Select place</option>
                <%--<option value="ANALYST">Analyzing Staff</option>--%>
                <c:forEach var="listOfPlaces" items="${listOfPlaces}">
                    <option title='${listOfPlaces.description}' value='${listOfPlaces.id}'>${listOfPlaces.title}</option>
                </c:forEach>
            </select>
            <%--<input type="text" id="textDBPlace" class="form-control" placeholder="selector places in db" >--%>
            or you can add your own place.
            <input type="text" id="textPlaceTitle" class="form-control" placeholder="place title" >
            <input type="text" id="textPlaceDescription" class="form-control" placeholder="places description" >
            <input type="text" id="textPlaceLatitude" class="form-control" placeholder="places latitude" >
            <input type="text" id="textPlaceLongitude" class="form-control" placeholder="places longitude" >

            <button class="btn btn-primary" type="submit" id="addMyPlace" onclick="newPlace()">Add Place!</button>
            <%--<input type="radio" name="EmpJob" value="" onclick="">--%>

        </section>
    </article>
    <article id = "my-hobbies" class="info hidden">
        <header>
            <h4>my hobbies</h4>
        </header>
        <c:forEach var="hobbyInfo" items="${hobbiesInfo}">
        <section id ="my-hobbies-description" class="description">
            <p> ${hobbyInfo.title}</p>
            <p> ${hobbyInfo.description}</p>
        </section>
        </c:forEach>
        <section>
            If you want to add your hobby you can choose one of the ours hobbies
            <select id="HobbySelect" onchange="">
                <option value="-1">List of Hobbies</option>
                <%--<option value="ANALYST">Analyzing Staff</option>--%>
                <c:forEach var="listOfHobbies" items="${listOfHobbies}">
                    <option title='${listOfHobbies.description}' value='${listOfHobbies.id}'>${listOfHobbies.title}</option>
                </c:forEach>
            </select>
            or you can add your own hobby.
            <%--<input type="text" id="textDBHobby" class="form-control" placeholder="selector Hobbies in db" >--%>
            <input type="text" id="textHobbyTitle" class="form-control" placeholder="Hobbies title" >
            <input type="text" id="textHobbyDescription" class="form-control" placeholder="Hobbies description" >
            <button class="btn btn-primary" type="submit" onclick="newHobby()">New hobby.</button>
        </section>
    </article>
    <article id = "my-posts" class="info hidden">
        <header>
            <h4>my posts</h4>
        </header>
        <c:forEach var="postInfo" items="${postsInfo}">
        <section id ="my-posts-description" class="description">
            <p> ${postInfo.title}</p>
            <p> ${postInfo.content}</p>
            <p>posted by ${postInfo.ownerNickname}</p>
            <p>posted at ${postInfo.placeTime}</p>
            <p>${postInfo.countLikes} like(s)</p>
            <button class="btn btn-primary" type="submit"  onclick="createLike('${postInfo.id}')">Like!/Dislike</button>
        </section>
        </c:forEach>
        <section>
            Creating new post
            <input type="text" id="textPostTitle" class="form-control" placeholder="Title" >
            <input type="text" id="textPostContent" class="form-control" placeholder="Content" >
            <button class="btn btn-primary" type="submit" onclick="newPost()">Post it!</button>
        </section>
    </article>
        <article id = "all-posts" class="info hidden">
            <header>
                <h4>all posts</h4>
            </header>
            <c:forEach var="postInfo2" items="${listAllPosts}">
                <section id ="my-posts-description" class="description">
                    <p> ${postInfo2.title}</p>
                    <p> ${postInfo2.content}</p>
                    <p>posted by ${postInfo2.ownerNickname}</p>
                    <p>posted at ${postInfo2.placeTime}</p>
                    <p>${postInfo2.countLikes} like(s)</p>
                    <button class="btn btn-primary" type="submit"  onclick="createLike('${postInfo2.id}')">Like!/Dislike</button>
                    <%--<button class="btn btn-primary" type="submit" onclick='createLike("${postInfo2.id}")'>TEST Like!</button>--%>
                </section>
            </c:forEach>

        </article>
    <article id = "my-messages" class="info hidden">
        <header>
            <h4>my messages</h4>
        </header>
        <c:forEach var="messageInfo" items="${messagesInfo}">
        <section id ="my-messages-description" class="description">
            <p>sent by ${messageInfo.nicknamePersonFrom}</p>
            <p>to ${messageInfo.nicknamePersonTo}</p>
            <p>${messageInfo.content}</p>
            <p>sent at ${messageInfo.timeSent}</p>
        </section>
        </c:forEach>
        <section>
            Send message to
            <%--<c:forEach var="listOfPeoples" items="${listOfPersons}">--%>
                <%--<p>${listOfPeoples.firstName}</p>--%>
            <%--</c:forEach>--%>
            <select id="PeopleMsgSelect" onchange="">
                <option value="-1">List of Persons</option>
                <%--<option value="ANALYST">Analyzing Staff</option>--%>
                <c:forEach var="listOfPeople" items="${listOfPersons}">
                    <option title='${listOfPeople.firstName} ${listOfPeople.lastName}' value='${listOfPeople.nickname}'>
                        <%--<img src = "${listOfPeople.photoURL}" class="icon" alt="">--%>
                            ${listOfPeople.nickname}</option>
                </c:forEach>
            </select>
            <%--<input type="text" id="textMessageDBPerson" class="form-control" placeholder="selector db persons" >--%>
            <input type="text" id="textMessageBody" class="form-control" placeholder="message text" >
            <button class="btn btn-primary" type="submit" onclick="newMessage('${personInfo.nickname}')">Send msg!</button>

        </section>
    </article>
</main>


</body>
</html>