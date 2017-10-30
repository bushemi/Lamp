
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

function ajaxPost(url, sendData){
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: sendData,
        success: function (data) {
            if (data.status === "OK") {
                //update status
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
function showPosts() {
    ajaxGet("/myPosts");
    hideDescriptions();
    showMenu("my-posts");
}
function showAllPosts() {
    ajaxGet("/myPosts");
    hideDescriptions();
    showMenu("all-posts");
}
function showFriends() {
    ajaxGet("/myFriends");
    hideDescriptions();
    showMenu("my-friends");
}
function showPlaces() {
    ajaxGet("/myPlaces");
    hideDescriptions();
    showMenu("my-places");
}
function showHobbies() {
    ajaxGet("/myHobbies");
    hideDescriptions();
    showMenu("my-hobbies");
}
function showMessages() {
    ajaxGet("/myMessages");
    hideDescriptions();
    showMenu("my-messages");
}
function exit() {
     ajaxExit("/exit");
}

function makeFriend() {
    var id = PeopleSelect.options[PeopleSelect.selectedIndex].value
    if (id != (-1)) {
        ajaxPost("/newFriend",'{"id": "' + id + '"}');
    }else{alert("you didn't choose who do you want to be friend with");}

}
function endFriendship(me,one,two){
    var nickname = (me===one)?two:one;
    ajaxPost("/endFriendship",'{"nickname": "' + nickname + '"}');
}
function createLike(id){
    ajaxPost("/like",'{"id": "' + id + '"}');
}
function newMessage(nicknamePersonFrom){
    // alert(nicknamePersonFrom);
    var nicknamePersonTo = PeopleMsgSelect.options[PeopleMsgSelect.selectedIndex].value;
    if (nicknamePersonTo != (-1)) {
        var content = $("#textMessageBody").val();
        ajaxPost("/newMessage",'{"content": "' + content +'", "nicknamePersonFrom": "' + nicknamePersonFrom +'","nicknamePersonTo": "' + nicknamePersonTo + '"}');
    }else{alert("you didn't choose who do you want to send");}
}
function newHobby(){
    var id = HobbySelect.options[HobbySelect.selectedIndex].value;
    if (id != (-1)){
        ajaxPost("/newHobby",'{"id": "' + id + '"}');
    }
    else {
        var title = $("#textHobbyTitle").val();
        var description = $("#textHobbyDescription").val();
        ajaxPost("/newHobby",
            '{"title": "' + title + '", "description": "' + description + '"}');
    }
}
function newPlace(){
    var id = PlaceSelect.options[PlaceSelect.selectedIndex].value;
    if (id != (-1)){
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
}

function newPost(){
    var title = $("#textPostTitle").val();
    var content = $("#textPostContent").val();
    ajaxPost("/newPost",'{"title": "' + title + '", "content": "' + content + '"}');
}
