var forgetPassword = new Vue({
    el: '#forgetPassword',
    data: {
        email: '',
        message:'',
        messageCss:'',
        display:false
    },
    created: function () {

    },
    methods: {
        send_email:function () {
            forgetPassword.display = true;
            forgetPassword.messageCss = "alert alert-success fade in";
            forgetPassword.message = "邮件发送中...请稍等...";
            $.get("/openApi/send_email",
                {
                    email: forgetPassword.email,
                    emailType:'restPassword'
                },
                function (data) {
                    forgetPassword.display = true;
                    if (data === "success") {
                        forgetPassword.messageCss = "alert alert-success fade in";
                        forgetPassword.message = "邮件已发送至您的邮箱，请注意查收"
                    } else if(data === "not find email"){
                        forgetPassword.messageCss = "alert alert-danger fade in";
                        forgetPassword.message = "没有找到相关账号，请确认输入正确"
                    }else {
                        forgetPassword.messageCss = "alert alert-danger fade in";
                        forgetPassword.message = "系统错误，请稍后再试"
                    }
                }
            );
        },
        inputEmail:function () {
            forgetPassword.display = false;
            forgetPassword.email = "";
        }
    }
});