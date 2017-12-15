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
    var str_studentid = $("#inputUsername").val();
    var str_password = $("#inputPassword").val();
    // Remember Me
    if ($('#checkbox1').is(':checked')) {
        $.cookie("rmbUser", "true", {expires: 7});
        $.cookie("studentid", str_studentid, {expires: 7});
        if (str_password.length < 16) {
            $.cookie("df151bf2egf2hjl", md5(str_password), {expires: 7});
        }else
        {
            $.cookie("df151bf2egf2hjl", str_password, {expires: 7})
        }
    } else {
        $.cookie("studentid", "", {expires: -1});
    }
    if (str_password.length < 16) {
        str_password = md5(str_password);
    }

    //登录验证
    if ($("#inputUsername").val() !== "" && $("#inputPassword").val() !== "") {
        $.post("/api/login",
            {
                studentId: str_studentid,
                password: str_password
            },
            function (data) {
                if (data.msg === "success") {
                    $.cookie("UserType", "student", {expires: 7});
                    $.cookie("Token", str_studentid, {expires: 7});
                    window.location.href = "/home"
                } else if (data.msg === "admin") {
                    $.cookie("UserType", "andmin", {expires: 7});
                    $.cookie("Token", str_studentid, {expires: 7});
                    window.location.href = "/admin"
                } else if (data.msg === "password error") {
                    $("#loginmessage").html("账号或者密码错误");
                } else if (data.msg === "not find student") {
                    $("#loginmessage").html("用户不存在");
                }
            }, "json"
        );
    }
}

function getlogin() {
    $.get("/api/getlogin",
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

    student.css("display", "none");
    admin.css("display", "none");
    personal.css("display", "none");

    if ($.cookie("UserType") === "") {
        entrance.css("display", "list-item");
    }
    else {
        personal.css("display", "list-item");
        if ($.cookie("UserType") === "student") {
            student.css("display", "list-item");
        }

        if ($.cookie("UserType") === "andmin") {
            admin.css("display", "list-item");
        }
    }
}

function logout() {
    $.cookie("UserType", "", {expires: 7});
    $.cookie("Token", "", {expires: -1});
    initnav();
}

function agin() {
    $("#loginmessage").html("");
    initnav();
}