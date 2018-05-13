
var studentPersonalApp = new Vue({
    el: '#studentPersonal',
    data: {
        studentInfo: ''
    },
    created: function () {
        this.get_student_personal();
    },
    methods:{
        get_student_personal: function () {
            $.get("/student/get_student_personal",
                {},
                function getHolidayInfo(userModel) {
                    studentPersonalApp.studentInfo = userModel;
                });
        }
    }
});
