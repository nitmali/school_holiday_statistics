$(document).ready(function () {
    get_login();
    remember();
    loginofenter();
    focus();
});

function remember() {
    $("#checkbox1").attr("checked", 'true');
    if ($.cookie("rmbUser") === "true" && window.location.pathname !== "/manager/login") {
        $("#ck_rmbUser").attr("checked", true);
        $("#inputUsername").val($.cookie("studentId"));
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
            $("#login").click();
        }
    });
}

function loginIn(userType) {
    var str_userId = $("#inputUsername").val();
    var str_password = $("#inputPassword").val();
    var str_userType = userType;
    var str_userName = null;
    // Remember Me
    if ($('#checkbox1').is(':checked') && window.location.pathname !== "/manager/login") {
        $.cookie("rmbUser", "true", {expires: 7,path: '/'});
        $.cookie("studentId", str_userId, {expires: 7,path: '/'});
        if (str_password.length <= 16) {
            $.cookie("df151bf2egf2hjl", md5(str_password), {expires: 7,path: '/'});
        }else
        {
            $.cookie("df151bf2egf2hjl", str_password, {expires: 7,path: '/'})
        }
    } else {
        $.cookie("studentId", "", {expires: -1});
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
                    $.cookie("UserType", userFromModel.userType, {expires: 7,path: '/'});
                    $.cookie("Token", userFromModel.userId, {expires: 7,path: '/'});
                    $.cookie("UserName", userFromModel.userName, {expires: 7,path: '/'});
                    if (userFromModel.userType === "student") {
                        $.cookie("UserType", "student", {expires: 7,path: '/'});
                        window.location.href = "/student/home";
                    } else {
                        window.location.href = "/manager/home";
                        $.cookie("UserType", "manager", {expires: 7,path: '/'});
                    }
                }else {
                    $("#login_message").html("账号或者密码错误");
                }
            }, "json"
        );
    }
}

function get_login() {
    $.get("/get_login",
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

function again() {
    $("#login_message").html("");
}

function clean(id)
{
    $("#"+id).val("");
}