$(document).ready(function () {
    getlogin();
    remember();
    loginofenter();
    focus();
});

function remember() {
    $("#checkbox1").attr("checked", 'true');
    if ($.cookie("rmbUser") === "true" && window.location.pathname !== "/managelogin") {
        $("#ck_rmbUser").attr("checked", true);
        $("#inputUsername").val($.cookie("studentid"));
        $("#inputPassword").val($.cookie("df151bf2egf2hjl"));
    }
}

function focus() {
    if ($("#inputUsername").val() === "") {
        $("#inputUsername").attr("autofocus", "autofocus");
    } else {
        $("#inputPassword").attr("autofocus", "autofocus");
    }
}

function loginofenter() {

    $(document).keydown(function (event) {
        if (event.keyCode === 13 && $("#inputPassword").val() !== "") {
            loginin();
        }
    });
}

function loginin() {
    var str_userId = $("#inputUsername").val();
    var str_password = $("#inputPassword").val();
    var str_userType = null;
    var str_userName = null;
    // Remember Me
    if ($('#checkbox1').is(':checked')) {
        $.cookie("rmbUser", "true", {expires: 7});
        $.cookie("studentid", str_userId, {expires: 7});
        if (str_password.length <= 16) {
            $.cookie("df151bf2egf2hjl", md5(str_password), {expires: 7});
        }else
        {
            $.cookie("df151bf2egf2hjl", str_password, {expires: 7})
        }
    } else {
        $.cookie("studentid", "", {expires: -1});
    }
    if (str_password.length <= 16) {
        str_password = md5(str_password);
    }

    //登录验证
    if ($("#inputUsername").val() !== "" && $("#inputPassword").val() !== "") {
        $.post("/login",
            {
                userId: str_userId,
                password: str_password,
                userType: str_userType,
                userName:str_userName
            },
            function (userFromModel) {
                if (userFromModel.userName !== "") {
                    $.cookie("UserType", userFromModel.userType, {expires: 7});
                    $.cookie("Token", userFromModel.userId, {expires: 7});
                    $.cookie("UserName", userFromModel.userName, {expires: 7});
                    if (userFromModel.userType === "student") {
                        $.cookie("UserType", "student", {expires: 7});
                        window.location.href = "/student_home";
                    } else {
                        window.location.href = "/admin_home";
                        $.cookie("UserType", "admin", {expires: 7});
                    }
                }else {
                    $("#loginmessage").html("账号或者密码错误");
                }
            }, "json"
        );
    }
}

function getlogin() {
    $.get("/getlogin",
        function (data) {
            if (data === "error" && $.cookie("UserType") === "student") {
                logout();
            }
        }
    );
    initnav();
}

function initnav() {
    var student = $("#student");
    var admin = $("#admin");
    var entrance = $("#entrance");
    var personal = $("#personal");
    var userName = $("#userName");

    student.css("display", "none");
    admin.css("display", "none");
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

        if ($.cookie("UserType") === "admin") {
            admin.css("display", "list-item");
        }
    }
}

function logout() {
    $.cookie("UserType", "", {expires: -1});
    $.cookie("UserName", "", {expires: -1});
    $.cookie("Token", "", {expires: -1});
    initnav();
}

function agin() {
    $("#loginmessage").html("");
    initnav();
}