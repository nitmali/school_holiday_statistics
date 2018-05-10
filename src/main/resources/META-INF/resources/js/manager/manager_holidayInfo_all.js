var managerHolidayPlan0fStudent = new Vue({
    el: '#managerHolidayPlanOfStudent',
    data: {
        holidayPlan_list: ''
    }
});

var managerHolidayInfoAllApp = new Vue({
    el: '#managerHolidayInfoAll',
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
            $.get("/manager/get_holidayInfo_all",
                {
                    holidayStatus: holidayStatus
                },
                function (HolidayInfoFromModel) {
                    managerHolidayInfoAllApp.holidayInfo_list = HolidayInfoFromModel;
                });
        },
        show_holiday_plan_of_student: function (index) {
            window.open("/manager/get_holiday_plan_of_student?holidayId="+index);
        }
    }
});

