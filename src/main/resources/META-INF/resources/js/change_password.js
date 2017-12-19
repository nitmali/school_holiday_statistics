function changePassword() {
    var oldPassword = $("#oldPassWord");
    var newPassWordAgin = $("#newPassWordAgin");
    var newPassWord = $("#newPassWord");
    var oldPassWordSpawn = $("#oldPassWordSpawn");
    var newPassWordAginSpan = $("#newPassWordAginSpan");

    var inputList = $("input");
    var inputFlag = true;
    for (var i = 0; i < inputList.length; i++) {
        if (inputList[i].value === "") {
            inputList[i].focus();
            inputFlag = false;
        }
    }

    if (inputFlag) {
        if (newPassWord.val() === newPassWordAgin.val()) {
            if (newPassWordAgin.val().length >= 6 && newPassWordAgin.val().length <= 16) {
                $.post("/change_password",
                    {
                        oldPassword: md5(oldPassword.val()),
                        newPassword: md5(newPassWordAgin.val())
                    },
                    function (data) {
                        if (data === "old error") {
                            oldPassWordSpawn.html("原密码错误！")
                        } else {
                            alert("密码修改成功！");
                            window.location.href = "/student_home";
                        }
                    }
                );
            } else {
                newPassWordAginSpan.html("请输入6至16位密码！")
            }
        }
        else {
            newPassWordAginSpan.html("两次密码输入不一致！")
        }
    }else {
        alert("请填写完整信息！")
    }

}

function cleanOldSpan() {
    $("#oldPassWordSpawn").html("")
}

function cleanNewSpan() {
    $("#newPassWordAginSpan").html("")
}