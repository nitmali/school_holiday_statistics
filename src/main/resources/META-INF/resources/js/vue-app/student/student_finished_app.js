var student_finished_app = new Vue({
    el: '#student_finished',
    data: {
        holidayPlanAll: '',
        holidayPlanOne: ''
    },
    created: function () {
        this.get_holidayPlan_one();
    },
    methods: {
        get_holidayPlan_all: function () {
            $.get("/student/get_holidayPlan_all",
                function (get_holidayPlan_of_student) {
                    student_finished_app.holidayPlanOne = '';
                    student_finished_app.holidayPlanAll = get_holidayPlan_of_student;
                }
            )
        },
        get_holidayPlan_one: function () {
            $.get("/student/get_holidayPlan_one",
                function (get_holidayPlan_of_student) {
                    student_finished_app.holidayPlanAll = '';
                    student_finished_app.holidayPlanOne = get_holidayPlan_of_student;
                }
            )
        }
    }

});