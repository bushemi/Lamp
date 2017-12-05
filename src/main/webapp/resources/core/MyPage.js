$(document).ready(function () {
    var myInfo;
    var listOfPersons;
    var allPosts;
    var allPlaces;
    var listOfHobbies;

    var friendsInterval = 0;
    var myPlacesInterval = 0;
    var myHobbiesInterval = 0;
    var myPostsInterval = 0;
    var myMessagesInterval = 0;
    var allPersonsInterval = 0;
    var allPostsInterval = 0;
    var allPlacesInterval = 0;
    var allHobbiesInterval = 0;

     $.getJSON("/myInfo", jsonMyInfoParser);

     $.getJSON("/allPosts", jsonAllPostsParser);
    // $.getJSON("/places", jsonAllPlacesParser);


    $('#exit').click(function () {
        exit();
    });
    $('#myPlaces').click(function () {
        showPlaces();
    });
    $('#myHobbies').click(function () {
        showHobbies();
    });
    $('#myFriends').click(function () {
        showFriends();
    });
    $('#myPosts').click(function () {
        showPosts();
    });
    $('#allPosts').click(function () {
        showAllPosts();
    });
    $('#myMessages').click(function () {
        showMessages();
    });
    function clearAllIntervals() {
        // window.clearAllIntervals();
        window.clearInterval(friendsInterval);
        // window.clearInterval(myPlacesInterval);
        // window.clearInterval(myHobbiesInterval);
        // window.clearInterval(myPostsInterval);
        // window.clearInterval(myMessagesInterval);
        // window.clearInterval(allPersonsInterval);
        // window.clearInterval(allPostsInterval);
        // window.clearInterval(allPlacesInterval);
        // window.clearInterval(allHobbiesInterval);
    }
    /////////////////////////////

    function hideDescriptions () {
        var slides = document.getElementsByClassName("info");
        for(var i = 0; i < slides.length; i++)
        {
            slides.item(i).classList.add("hidden");
        }
    }
    function showMenu(id) {
        document.getElementById(id).classList.remove("hidden");
    }

    function jsonPlacesParser(messages){
        var object = messages.object;
        clearElement("my-places");
        addHeader('#my-places','my places');

        $.each(object, function (key, message) {
            var title = message.title;
            var description = message.description;
            var latitude = message.latitude;
            var longitude = message.longitude;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML = '<p> '+title+'</p><p>'+ description+'</p><p>'+ latitude+', '+ longitude+'</p>';
            $('#my-places')[0].appendChild(section);
        });
        $.getJSON("/places", jsonNewPlaceParser);
    }
    function jsonHobbiesParser(messages){
        var object = messages.object;
        clearElement("my-hobbies");
        addHeader('#my-hobbies','my hobbies');
        $.each(object, function (key, message) {
            var title = message.title;
            var description = message.description;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML = '<p> '+title+'</p><p>'+ description+'</p>';
            $('#my-hobbies')[0].appendChild(section);
        });

        $.getJSON("/hobbies", jsonNewHobbyParser);
    }
    function jsonMessagesParser(messages){
        var object = messages.object;

        clearElement("my-messages");
        addHeader('#my-messages','my messages');
        $.each(object, function (key, message) {
            var nicknamePersonFrom = message.nicknamePersonFrom;
            var nicknamePersonTo = message.nicknamePersonTo;
            var content = message.content;
            var timeSent = message.timeSent;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML =
                '<p>sent by '+ nicknamePersonFrom+'</p>' +
                '<p>to '+nicknamePersonTo+'</p>' +
                '<p>'+content+'</p>' +
                '<p> sent at '+timeSent+'</p>';

            $('#my-messages')[0].appendChild(section);
        });
        $.getJSON("/persons", jsonNewMessageParser);
    }
    function jsonPostsParser(messages){
        var object = messages.object;
        clearElement("my-posts");
        addHeader('#my-posts','my posts');
        $.each(object, function (key, message) {
            var id = message.id;
            var title = message.title;
            var content = message.content;
            var ownerNickname = message.ownerNickname;
            var placeTime = message.placeTime;
            var countLikes = message.countLikes;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML = '<p> '+title+'</p>' +
                '<p> '+content+'</p>' +
                '<p>posted by "'+ownerNickname+'"</p>' +
                '<p>posted at '+placeTime+'</p>' +
                '<p>'+countLikes+' like(s)</p>';
            var button = document.createElement('button');
            button.className = "btn btn-primary";
            button.type = "submit";
            button.addEventListener("click", function(){
                createLike(id);
                window.setTimeout(function(){$.getJSON("/myPosts", jsonPostsParser);}, 500);
            });
            button.innerHTML = "Like!/Dislike";
            section.appendChild(button);
            $('#my-posts')[0].appendChild(section);
        });
        newPostForm();
    }

    function jsonNewPlaceParser(messages){
        var object = messages.object;

        var mainSection = document.createElement('section');
        mainSection.className = 'new-item';
        mainSection.innerHTML = '<p>If you want to add your Place you can choose one of the ours places</p>';

        var select = document.createElement('select');
        select.id = "PlaceSelect";
        var option = document.createElement('option');
        select.appendChild(option);
        $.each(object, function (key, message) {
            var id = message.id;
            var description = message.description;
            var title = message.title;
            option = document.createElement('option');
            option.title = description;
            option.value = id;
            option.innerHTML = title;
            select.appendChild(option);
        });

        mainSection.appendChild(select);
        var p = document.createElement('p');
        p.innerText='Or you can add your place to our set';
        mainSection.appendChild(p);

        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPlaceTitle';
        input.class = 'form-control';
        input.placeholder = "place title";
        mainSection.appendChild(input);

        input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPlaceDescription';
        input.class = 'form-control';
        input.placeholder = "place description";
        mainSection.appendChild(input);

        input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPlaceLatitude';
        input.class = 'form-control';
        input.placeholder = "place latitude";
        mainSection.appendChild(input);

        input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPlaceLongitude';
        input.class = 'form-control';
        input.placeholder = "place longitude";
        mainSection.appendChild(input);

        var button = document.createElement('button');
            button.className = "btn btn-primary";
            button.type = "submit";
            button.addEventListener("click", function(){
                newPlace();
            });
        button.innerHTML = "add place";
        mainSection.appendChild(button);
        $('#my-places')[0].appendChild(mainSection);

    }
    function newPostForm(){
        var mainSection = document.createElement('section');
        mainSection.className = 'new-item';
        mainSection.innerHTML = '<p>Creating new post</p>';

        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPostTitle';
        input.class = 'form-control';
        input.placeholder = "Title";
        mainSection.appendChild(input);

        input = document.createElement('input');
        input.type = 'text';
        input.id = 'textPostContent';
        input.class = 'form-control';
        input.placeholder = "Content";
        mainSection.appendChild(input);

        var button = document.createElement('button');
        button.className = "btn btn-primary";
        button.type = "submit";
        button.addEventListener("click", function(){
            newPost();
        });
        button.innerHTML = "Post it!";
        mainSection.appendChild(button);
        $('#my-posts')[0].appendChild(mainSection);

    }
    function ajaxPost(url, sendData){
        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: sendData,
            success: function (data) {
                if (data.status == "OK") {
                    // alert("ok");
                } else {
                }
            }
        });
    }

    function ajaxExit(theUrl)
    {
        $.ajax({
            type: "GET",
            url: "/exit",
            dateType:"text",
            success: function (data) {
                if (data=="OK"){
                    window.location.href = "/login";
                }
            }

        });
    }

    function ajaxGet(theUrl)
    {
        $.ajax({
            type: "GET",
            url: theUrl,
            dateType:"text",
            success: function (data) {
                if (data=="OK"){
                }else{
                }
            }

        });
    }

////////////////////////////


    function showPlaces() {
        clearAllIntervals();
        $.getJSON("/myPlaces", jsonPlacesParser);
        hideDescriptions();
        showMenu("my-places");
    }
    function showHobbies() {
        clearAllIntervals();
        $.getJSON("/myHobbies", jsonHobbiesParser);
        hideDescriptions();
        showMenu("my-hobbies");
    }

    function showMessages() {
        clearAllIntervals();
        $.getJSON("/myMessages", jsonMessagesParser);
        hideDescriptions();
        showMenu("my-messages");
    }

    function showPosts() {
        clearAllIntervals();
        $.getJSON("/myPosts", jsonPostsParser);
        hideDescriptions();
        showMenu("my-posts");
    }

    function showAllPosts() {
        clearAllIntervals();
        $.getJSON("/allPosts", jsonAllPostsParser);
        hideDescriptions();
        showMenu("all-posts");
    }
    function jsonAllPostsParser(messages){
        var object = messages.object;
        clearElement("all-posts");
        addHeader('#all-posts','all posts')

        $.each(object, function (key, message) {
            var id = message.id;
            var title = message.title;
            var content = message.content;
            var ownerNickname = message.ownerNickname;
            var placeTime = message.placeTime;
            var countLikes = message.countLikes;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML = '<p> '+title+'</p>' +
                '<p> '+content+'</p>' +
                '<p>posted by "'+ownerNickname+'"</p>' +
                '<p>posted at '+placeTime+'</p>' +
                '<p>'+countLikes+' like(s)</p>';
            var button = document.createElement('button');
            button.className = "btn btn-primary";
            button.type = "submit";
            button.addEventListener("click", function(){
                createLike(id);
                window.setTimeout(function(){$.getJSON("/allPosts", jsonAllPostsParser);}, 500);
            });
            button.innerHTML = "Like!/Dislike";
            section.appendChild(button);
            $('#all-posts')[0].appendChild(section);
        });
    }
    function showFriends() {
        clearAllIntervals();
        $.getJSON("/myFriends", jsonFriendsParser);
        hideDescriptions();
        showMenu("my-friends");
    }
    function jsonFriendsParser(messages){
        var object = messages.object;
        clearElement("my-friends");
        addHeader('#my-friends','my friends');

        $.each(object, function (key, message) {
            var personNickname = message.personNickname;
            var friendNickname = message.friendNickname;
            var friendFrom = message.friendFrom;

            var section = document.createElement('section');
            section.className = 'description';
            section.innerHTML = '<p>The friendship between ' + personNickname + ' and '+ friendNickname + '</p><p>since ' + friendFrom +'</p>';

            var button = document.createElement('button');
            button.className = "btn btn-primary";
            button.type = "submit";
            button.addEventListener("click", function(){
                endFriendship(myInfo.nickname, personNickname, friendNickname);
            });
            button.innerHTML = "End this friendship";
            section.appendChild(button);
            $('#my-friends')[0].appendChild(section);
        });
        $.getJSON("/persons", jsonNewFriendParser);
    }
    function jsonNewFriendParser(messages){
        listOfPersons = messages.object;
        var section = document.createElement('section');

        var select = document.createElement('select');
        select.id = "PeopleSelect";
        var option = document.createElement('option');
        select.appendChild(option);
        var i;
        for (i = 0; i < listOfPersons.length; ++i) {
             message = listOfPersons[i];
                var firstName = message.firstName;
                var lastName = message.lastName;
                var nickname = message.nickname;
                option = document.createElement('option');
                option.title = firstName +' '+lastName;
                option.value = message.id;
                option.innerHTML = nickname;
                select.appendChild(option);
            }


        section.appendChild(select);
        var p = document.createElement('p');
        p.innerHTML="and";
        section.appendChild(p);

        var button = document.createElement('button');
        button.className = "btn btn-primary";
        button.id = "Friends";
        button.type = "submit";
        button.addEventListener("click", function(){
            makeFriend();
        });
        button.innerHTML = "Friend it!";
        section.appendChild(button);
        $('#my-friends')[0].appendChild(section);
    }
    function exit() {
        ajaxExit("/exit");
    }

    function makeFriend() {
        var id = PeopleSelect.options[PeopleSelect.selectedIndex].value
        if (id != ("")) {
            ajaxPost("/newFriend",'{"id": "' + id + '"}');
        }else{alert("you didn't choose who do you want to be friend with");}
        window.setTimeout(function(){$.getJSON("/myFriends", jsonFriendsParser);}, 1000);

    }
    function endFriendship(me,one,two){
        var nickname = (me===one)?two:one;
        ajaxPost("/endFriendship",'{"nickname": "' + nickname + '"}');
        window.setTimeout(function(){$.getJSON("/myFriends", jsonFriendsParser);}, 1000);

    }
    function createLike(id){
        ajaxPost("/like",'{"id": "' + id + '"}');
    }
    function newMessage(nicknamePersonFrom){
        // alert(nicknamePersonFrom);
        var nicknamePersonTo = PeopleMsgSelect.options[PeopleMsgSelect.selectedIndex].value;
        if (nicknamePersonTo != ("")) {
            var content = $("#textMessageBody").val();
            ajaxPost("/newMessage",'{"content": "' + content +'", "nicknamePersonFrom": "' + nicknamePersonFrom +'","nicknamePersonTo": "' + nicknamePersonTo + '"}');
        }else{alert("you didn't choose who do you want to send");}
        window.setTimeout(function(){$.getJSON("/myMessages", jsonMessagesParser);}, 1500);
    }
    function newHobby(){
        var id = HobbySelect.options[HobbySelect.selectedIndex].value;
        if (id != ("")){
            ajaxPost("/newHobby",'{"id": "' + id + '"}');
        }
        else {
            var title = $("#textHobbyTitle").val();
            var description = $("#textHobbyDescription").val();
            ajaxPost("/newHobby",
                '{"title": "' + title + '", "description": "' + description + '"}');
        }
        window.setTimeout(function(){$.getJSON("/myHobbies", jsonHobbiesParser);}, 1500);

    }
    function newPlace(){
        var id = PlaceSelect.options[PlaceSelect.selectedIndex].value;
        if (id != ("")){
            ajaxPost("/newPlace",'{"id": "' + id + '"}');
        }
        else {
            var title = $("#textPlaceTitle").val();
            var description = $("#textPlaceDescription").val();
            var latitude = $("#textPlaceLatitude").val();
            var longitude = $("#textPlaceLongitude").val();
            ajaxPost("/newPlace",
                '{"title": "' + title + '", "description": "' + description + '",'+'"latitude": "' + latitude + '", "longitude": "' + longitude + '"}');
        }
        window.setTimeout(function(){$.getJSON("/myPlaces", jsonPlacesParser);}, 1500);
    }

    function newPost(){
        var title = $("#textPostTitle").val();
        var content = $("#textPostContent").val();
        ajaxPost("/newPost",'{"title": "' + title + '", "content": "' + content + '"}');
        window.setTimeout(function(){$.getJSON("/myPosts", jsonPostsParser);}, 1500);

    }
    function clearElement(id){
        var myNode = document.getElementById(id);
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
    }
    function addHeader(id,title){
        var header = document.createElement('header');
        header.innerHTML = '<h4>'+title+'</h4>';
        $(id)[0].appendChild(header);
    }
    function jsonMyInfoParser(messages){
        myInfo = messages.object;

        clearElement("my-info");
        var img = document.createElement('img');
        img.src = myInfo.photoURL;
        img.height = 128;
        img.width = 128;
        img.innerHTML = '<h4>my places</h4>';
        $('#my-info')[0].appendChild(img);

        addHeader('#my-info','my info');

        // alert(object.toString());

        var firstName = myInfo.firstName;
        var lastName = myInfo.lastName;
        var birthday = myInfo.birthday;
        var nickname = myInfo.nickname;
        var section = document.createElement('header');
        section.className = 'description';
        section.innerHTML = 'information about me:' +
            '<p>'+ lastName +' '+nickname+' '+firstName+'</p>' +
            '<p>'+birthday+'</p>';
        $('#my-info')[0].appendChild(section);
    }
    function jsonNewMessageParser(messages){
        listOfPersons = messages.object;
        var section = document.createElement('section');
        section.innerHTML = '<p>Send message to</p>';

        var select = document.createElement('select');
        select.id = "PeopleMsgSelect";
        var option = document.createElement('option');
        select.appendChild(option);
        $.each(listOfPersons, function (key, message) {
            var firstName = message.firstName;
            var lastName = message.lastName;
            var nickname = message.nickname;
            option = document.createElement('option');
            option.title = firstName +' '+lastName;
            option.value = nickname;
            option.innerHTML = nickname;
            select.appendChild(option);
        });

        section.appendChild(select);

        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'textMessageBody';
        input.class = 'form-control';
        input.placeholder = "message text";
        section.appendChild(input);

        var button = document.createElement('button');
        button.className = "btn btn-primary";
        button.type = "submit";
        button.addEventListener("click", function(){
            newMessage(myInfo.nickname);
        });
        button.innerHTML = "Send msg!";
        section.appendChild(button);
        $('#my-messages')[0].appendChild(section);
    }

    function jsonNewHobbyParser(messages){
        listOfHobbies = messages.object;

        var mainSection = document.createElement('section');
        mainSection.innerHTML = '<p>If you want to add your hobby you can choose one of the ours hobbies</p>';

        var select = document.createElement('select');
        select.id = "HobbySelect";
        var option = document.createElement('option');
        select.appendChild(option);
        $.each(listOfHobbies, function (key, message) {
            var id = message.id;
            var description = message.description;
            var title = message.title;
            option = document.createElement('option');
            option.title = description;
            option.value = id;
            option.innerHTML = title;
            select.appendChild(option);
        });

        mainSection.appendChild(select);
        var p = document.createElement('p');
        p.innerText='or you can add your own hobby.';
        mainSection.appendChild(p);

        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'textHobbyTitle';
        input.class = 'form-control';
        input.placeholder = "Hobbies title";
        mainSection.appendChild(input);

        input = document.createElement('input');
        input.type = 'text';
        input.id = 'textHobbyDescription';
        input.class = 'form-control';
        input.placeholder = "Hobbies description";
        mainSection.appendChild(input);

        var button = document.createElement('button');
        button.className = "btn btn-primary";
        button.type = "submit";
        button.addEventListener("click", function(){
            newHobby();
        });
        button.innerHTML = "New hobby.";
        mainSection.appendChild(button);
        $('#my-hobbies')[0].appendChild(mainSection);
    }


});
