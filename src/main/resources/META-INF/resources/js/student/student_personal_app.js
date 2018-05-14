var studentPersonalApp = new Vue({
    el: '#studentPersonal',
    data: {
        studentInfo: '',
        message: {
            messageCss: '',
            messageHtml: '',
            display: false
        },
        emailInfo: {
            email: '',
            emailFormat: /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/,
            emailToken: ''
        },
        flag: {
            sendEmail: false,
            inputToken: false,
            getToken: false
        }
    },
    created: function () {
        this.get_student_personal();
    },
    watch: {
        'studentInfo.email': function () {
            if (studentPersonalApp.studentInfo.email === studentPersonalApp.emailInfo.email) {
                studentPersonalApp.flag.inputToken = false;
                studentPersonalApp.flag.getToken = false;

            } else {
                studentPersonalApp.flag.getToken = true;
            }
        }
    },
    methods: {
        get_student_personal: function () {
            try {
                $.get("/student/get_student_personal",
                    {},
                    function getHolidayInfo(userModel) {
                        studentPersonalApp.studentInfo = userModel;
                        studentPersonalApp.emailInfo.email = userModel.email;
                    });
            }catch (error) {
                studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                studentPersonalApp.message.messageHtml = "系统错误！稍后刷新页面或稍后再试<br/>ERROR:"+error.message;
                this.message.display = true;
            }
        },
        set_student_personal: function () {
            try {
                if (this.flag.sendEmail || (this.emailInfo.emailToken !== undefined && this.emailInfo.emailToken !== "")) {
                    if (this.emailInfo.emailToken.length !== 7) {
                        $.get("/openApi/bind_email",
                            {
                                email: studentPersonalApp.studentInfo.email,
                                token: studentPersonalApp.emailInfo.emailToken
                            }, function (data) {
                                if (data === "success") {
                                    $.get("/student/set_student_phone",
                                        {
                                            phone: studentPersonalApp.studentInfo.phone
                                        }, function (data) {
                                            if (data === "success") {
                                                studentPersonalApp.message.display = true;
                                                studentPersonalApp.message.messageCss = "alert alert-success fade in";
                                                studentPersonalApp.message.messageHtml = "修改成功！";
                                                studentPersonalApp.emailInfo = studentPersonalApp.studentInfo.email;
                                                studentPersonalApp.flag.inputToken = false;
                                                studentPersonalApp.flag.getToken = false;
                                            }
                                        }
                                    );
                                } else if (data === "time out") {
                                    studentPersonalApp.message.display = true;
                                    studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                                    studentPersonalApp.message.messageHtml = "邮箱验证码超时，请重新获取验证码！";
                                } else if (data === "token error") {
                                    studentPersonalApp.message.display = true;
                                    studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                                    studentPersonalApp.message.messageHtml = "邮箱验证码错误！";
                                } else if (data === "error") {
                                    studentPersonalApp.message.display = true;
                                    studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                                    studentPersonalApp.message.messageHtml = "系统错误，请稍后再试！";
                                }
                            }
                        );
                    } else {
                        this.message.messageCss = "alert alert-danger fade in";
                        this.message.messageHtml = "请填写正确有效的7位邮箱验证码！";
                        this.message.display = true;
                    }


                } else {
                    if (this.studentInfo.phone.length === 11 || this.studentInfo.phone.length === 6) {
                        this.studentInfo.email = this.emailInfo.email;
                        $.get("/student/set_student_phone",
                            {
                                phone: studentPersonalApp.studentInfo.phone
                            }, function (data) {
                                if (data === "success") {
                                    studentPersonalApp.message.display = true;
                                    studentPersonalApp.message.messageCss = "alert alert-success fade in";
                                    studentPersonalApp.message.messageHtml = "修改成功！";
                                    studentPersonalApp.emailInfo = studentPersonalApp.studentInfo.email;
                                    studentPersonalApp.flag.inputToken = false;
                                    studentPersonalApp.flag.getToken = false;
                                }
                            }
                        );

                    } else {
                        this.message.messageCss = "alert alert-danger fade in";
                        this.message.messageHtml = "请填写正确的手机号码！";
                        this.message.display = true;
                    }
                }

            }catch (error) {
                studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                studentPersonalApp.message.messageHtml = "系统错误！稍后刷新页面或稍后再试<br/>ERROR:"+error.message;
                this.message.display = true;
            }
        },
        rest_message: function () {
            studentPersonalApp.message.display = false;
        },
        send_email: function () {
           try {
               if (studentPersonalApp.emailInfo.email !== studentPersonalApp.studentInfo.email) {
                   if (studentPersonalApp.emailInfo.emailFormat.test(studentPersonalApp.studentInfo.email)) {
                       studentPersonalApp.message.display = true;
                       studentPersonalApp.message.messageCss = "alert alert-success fade in";
                       studentPersonalApp.message.messageHtml = "邮件发送中，请稍等...";
                       $.get("/openApi/send_email",
                           {
                               email: studentPersonalApp.studentInfo.email,
                               emailType: 'bindEmail'
                           },
                           function (data) {

                               if (data === "success") {
                                   studentPersonalApp.message.messageCss = "alert alert-success fade in";
                                   studentPersonalApp.message.messageHtml = "邮件已发送至您的邮箱，请注意查收！";
                                   studentPersonalApp.flag.inputToken = true;
                               } else if (data === "emil is being use") {
                                   studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                                   studentPersonalApp.message.messageHtml = "此邮箱已被其他用户使用！"
                               } else {
                                   studentPersonalApp.message.messageCss = "alert alert-danger fade in";
                                   studentPersonalApp.message.messageHtml = "系统错误，请稍后再试！"
                               }
                           }
                       );
                   } else {
                       this.message.messageCss = "alert alert-danger fade in";
                       this.message.messageHtml = "请填写正确有效的邮箱地址！";
                       this.message.display = true;
                   }

               }
           }catch (error) {
               studentPersonalApp.message.messageCss = "alert alert-danger fade in";
               studentPersonalApp.message.messageHtml = "系统错误！稍后刷新页面或稍后再试<br/>ERROR:"+error.message;
               this.message.display = true;
           }
        }
    }
});
