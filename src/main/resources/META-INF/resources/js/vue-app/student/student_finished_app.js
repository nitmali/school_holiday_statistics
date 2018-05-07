var student_finished_app = new Vue({
    el:'#student_finished',
    data:{
        finished_info_all:'',
        finished_info_one:''
    },
    created:function () {
        this.get_holidayPlan_api();
    },
    methods:{
        get_holidayPlan_api:function () {
            $.get("/student/get_holidayPlan_of_student",
                function (get_holidayPlan_of_student) {
                    student_finished_app.finished_info_all = get_holidayPlan_of_student;
                }
            )
        }
    }

});