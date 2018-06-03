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
            window.open("/manager/get_holiday_plan_of_student?holidayId=" + index);
        },
        delete_holiday:function (holidayId) {
            if (confirm("是否确认您的操作\n销毁假期同时也会销毁相应的假期计划！") === true) {
                $.get("/manager/delete_holiday_info",
                    {
                        holidayId: holidayId
                    },
                    function (message) {
                        if (message === "success") {
                            alert("销毁成功！");
                            managerHolidayInfoAllApp.get_holiday_info_of_status('');
                        } else {
                            alert("error");
                        }
                    }
                );
            }
        }
    }
});

