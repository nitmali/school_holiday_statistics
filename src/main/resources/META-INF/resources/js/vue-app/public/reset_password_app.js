var resetPassword = new Vue({
    el: '#resetPassword',
    data: {
        password:'',
        userId:'',
        messageCss:'',
        messageHtml:'',
        display:false
    },
    created: function () {
        this.userId = $("#userId").val();
    },
    methods: {
        reset_password:function () {
            if(resetPassword.password.length < 6 || resetPassword.password.length > 16){
                resetPassword.display =true;
                resetPassword.messageCss = "alert alert-danger fade in";
                resetPassword.messageHtml = "请输入6~16为有效密码";
            }
            if(!resetPassword.display){
                $.get("/openApi/reset_password",
                    {
                        userId: resetPassword.userId,
                        password:md5(resetPassword.password)
                    },
                    function (data) {
                        resetPassword.display =true;
                        if (data === "success") {
                            resetPassword.messageCss = "alert alert-success fade in";
                            resetPassword.messageHtml = "<a href='/' th:href='@{/}'>重置至密码成功点击我登陆</a>"
                        }else if(data === "error"){
                            resetPassword.messageCss = "alert alert-danger fade in";
                            resetPassword.messageHtml
                                = "<a href='/public/forget_password' th:href='@{/public/forget_password}'>" +
                                "重置密码链接已失效，点击我重新申请" +
                                "</a>"
                        }
                    }
                );
            }
        },
        input_password:function () {
            resetPassword.password = "";
            resetPassword.display = false;
        }
    }
});