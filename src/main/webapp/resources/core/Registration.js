$(document).ready(function(){
    $("#submit-info").click(function () {
        newUserRegistration();
    })
    $("#submit-reg").click(function () {
        registration();
    })
});
function newUserRegistration(){
    var firstName = $("#first-name").val();
    var lastName = $("#last-name").val();
    var birthday = $("#birthday").val();
    var nickname = $("#nickname").val();
    var photoURL = $("#photo").val();

    $.ajax({
        type: "POST",
        url: "/submit-info",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"firstName": "' + firstName + '", "lastName": "' + lastName
        + '", "birthday": "' + birthday +'", "nickname": "'+ nickname  +'", "photoURL": "' + photoURL + '"}',
        success: function (data) {
            if (data.status === "OK") {
                window.location.href = "/page";
            } else {
                alert("Incorrect info!!!")
            }
        }
    });
}

function registration(){
    var login = $("#login").val();
    var password = $("#password").val();
    var email = $("#email").val();

    $.ajax({
        type: "POST",
        url: "/submit-reg",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: '{"login": "' + login + '", "password": "' + password + '", "email": "' + email + '"}',
        success: function (data) {
            if (data.status === "OK") {
                hideWindows ();
                displayTab("credentials-form")
            } else {
                alert("Incorrect credentials!!!")
            }
        }
    });
}


function hideWindows () {
    $(".window").each(function(index, elem) {
        $(elem).addClass("hidden");
    });
}

function displayTab(id) {
    $("#"+ id).removeClass("hidden");
}


