var adminHolidayPlan0FStudent = new Vue({
    el: '#adminHolidayPlanOfStudent',
    data: {
        holidayPlan_list: ''
    }
});

var adminHolidayInfoAllApp = new Vue({
    el: '#adminHolidayInfoAll',
    data: {
        holidayInfo_list: null
    },
    created: function () {
        this.get_holiday_info_of_status('');
    },
    methods: {
        set_stat: function (holidayStatus) {
            this.get_holiday_info_of_status(holidayStatus);

        },
        get_holiday_info_of_status: function (holidayStatus) {
            $.get("/get_holidayInfo_all",
                {
                    holidayStatus: holidayStatus
                },
                function (HolidayInfoFromModel) {
                    adminHolidayInfoAllApp.holidayInfo_list = HolidayInfoFromModel;
                });
        },
        show_holiday_plan_of_student: function (index) {
            $.get("/get_holiday_plan_of_student",
                {
                    holidayId: adminHolidayInfoAllApp.holidayInfo_list[index].holidayId
                }, function (HolidayPlan) {
                    if (HolidayPlan !== null) {
                        adminHolidayPlan0FStudent.holidayPlan_list = HolidayPlan;
                        $("#HolidayPlanOfStudentModal").modal();
                    } else {
                        alert("无统计记录")
                    }
                });
        }
    }
});



