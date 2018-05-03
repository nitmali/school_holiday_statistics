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
            window.open("/get_holiday_plan_of_student?holidayId="+index);
        }
    }
});

