$(document).ready(function () {
    get_login();
    loginOfEnter();
    focus();
});

function loginOfEnter() {
    $(document).keydown(function (event) {
        if (event.keyCode === 13 && $("#inputPassword").val() !== "") {
            $("#login").click();
        }
    });
}


function get_login() {
    $.get("/openAPi/get_login",
        function (data) {
            if (data === "error" && $.cookie("UserType") === "student") {
                logout();
            }
        }
    );
    init_nav();
}

function init_nav() {
    var student = $("#student");
    var manager = $("#manager");
    var entrance = $("#entrance");
    var personal = $("#personal");
    var userName = $("#userName");

    student.css("display", "none");
    manager.css("display", "none");
    personal.css("display", "none");

    if (document.cookie.indexOf("UserType") === -1) {
        entrance.css("display", "list-item");
    }
    else {
        userName.html($.cookie("UserName"));
        personal.css("display", "list-item");
        if ($.cookie("UserType") === "student") {
            student.css("display", "list-item");
        }

        if ($.cookie("UserType") === "manager") {
            manager.css("display", "list-item");
        }
    }
}

function logout() {
    $.cookie("UserType", "", {expires: -1,path: '/'});
    $.cookie("Token", "", {expires: -1,path: '/'});
    init_nav();
}
