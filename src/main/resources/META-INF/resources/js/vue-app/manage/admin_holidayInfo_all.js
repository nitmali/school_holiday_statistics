var adminHolidayPlan0fStudent = new Vue({
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
            // $.get("/get_holiday_plan_of_student",
            //     {
            //         holidayId: adminHolidayInfoAllApp.holidayInfo_list[index].holidayId
            //     }, function (HolidayPlanOfStudent) {
            //         if (HolidayPlanOfStudent !== null) {
            //             studentOfPlanApp.holidayPlan_list = HolidayPlanOfStudent;
            //             // $("#adminHolidayInfoAll").hide();
            //             // $("#HolidayPlanOfStudentModal").modal();
            //             window.open("/holiday_plan_of_student");
            //         } else {
            //             alert("无统计记录")
            //         }
            //     });

            window.open("/get_holiday_plan_of_student?holidayId="+index);
        }
    }
});


// var studentOfPlanApp = new Vue({
//     el: '#studentOfPlan',
//     data: {
//         holidayPlan_list: ''
//     }
// });
//
