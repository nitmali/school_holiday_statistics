var userLogin = new Vue({
    el: '#userLoginApp',
    data: {
        userFromModel: {
            userId: '',
            password: '',
            userType: '',
            userName: null
        },
        message: {
            messageCss: '',
            messageHtml: '',
            display: false
        }
    },
    created: function () {
        this.rest_remember();
        this.get_focus();
    },
    methods: {
        key: function (type) {
            userLogin.login_in(type)
        },
        login_in: function (userType) {

            if (userLogin.userFromModel.userId !== '' && userLogin.userFromModel.password !== '') {
                userLogin.userFromModel.userType = userType;
                if (userLogin.userFromModel.password.length <= 16) {
                    userLogin.userFromModel.password = md5(userLogin.userFromModel.password);
                }
                $.ajax({
                    url: '/login',
                    type: 'POST',
                    data: JSON.stringify(userLogin.userFromModel, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (userInfo) {
                        if (userInfo.userName !== null) {
                            userLogin.login_success(userInfo);
                        } else {
                            userLogin.message.messageCss = "alert alert-danger fade in";
                            userLogin.message.messageHtml = "账号或者密码错误";
                            userLogin.message.display = true;
                        }
                    },
                    error: function () {
                        userLogin.message.messageCss = "alert alert-danger fade in";
                        userLogin.message.messageHtml = "系统错误，请稍后再试";
                        userLogin.message.display = true;
                    }
                });
            }
        },
        login_success: function (userInfo) {
            $.cookie("UserType", userInfo.userType, {expires: 7, path: '/'});
            $.cookie("Token", userInfo.userId, {expires: 7, path: '/'});
            $.cookie("UserName", userInfo.userName, {expires: 7, path: '/'});
            if (userInfo.userType === "student") {
                $.cookie("userId", userLogin.userFromModel.userId, {expires: 7});
                $.cookie("userId", userLogin.userFromModel.userId, {expires: 7, path: '/'});
                // $.cookie("userId", userLogin.userFromModel.userId, {expires: 7, path: '/student/login'});
                $.cookie("userId", userLogin.userFromModel.userId, {expires: 7, path: '/public/logout'});
                $.cookie("UserType", "student", {expires: 7, path: '/'});
                window.location.href = "/student/home";
            } else {
                if ($('#remember').get(0).checked) {
                    $.cookie("userId", userLogin.userFromModel.userId, {expires: 7});
                    $.cookie("password", userLogin.userFromModel.password, {expires: 7});
                } else {
                    $.cookie("userId", "", {expires: -1, path: '/manager/login'});
                    $.cookie("password", "", {expires: -1, path: '/manager/login'});
                }
                $.cookie("UserType", "manager", {expires: 7, path: '/'});
                window.location.href = "/manager/home";
            }

        },
        rest_input: function (inputType) {
            userLogin.message.display = false;
            if (inputType === "userId") {
                userLogin.userFromModel.userId = "";
            } else if (inputType === "password") {
                userLogin.userFromModel.password = "";
            }
        },
        get_focus: function () {
            if (this.userFromModel.userId === undefined || this.userFromModel.userId === "") {
                $("#inputUsername").attr("autofocus", "autofocus");
            } else if (this.userFromModel.password === undefined || this.userFromModel.password === "") {
                $("#inputPassword").attr("autofocus", "autofocus");
            }
        },
        rest_remember: function () {
            $("#remember ").attr("checked", 'true');
            this.userFromModel.userId = $.cookie("userId");
            this.userFromModel.password = $.cookie("password");
        }
    }
});