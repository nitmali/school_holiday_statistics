var adminHolidayInfoAllApp = new Vue({
    el: '#adminHolidayInfoAll',
    data: {
        holidayInfo_list: null
    },
    created: function () {
        this.get_holiday_info_all();
    },
    methods: {
        set_stat: function (holidayStatus) {
            this.get_holiday_info_of_status(holidayStatus);

        },
        get_all: function (holidayStatus) {
            this.get_holiday_info_all(holidayStatus);
        },
        get_holiday_info_all:function () {
            $.get("/get_holidayInfo_all",
                {
                    holidayStatus: "START"
                },
                function (data) {
                    adminHolidayInfoAllApp.holidayInfo_list = data;
                });
        },
        get_holiday_info_of_status: function (holidayStatus) {
                $.get("/get_holidayInfo_all",
                    {
                        holidayStatus: holidayStatus
                    },
                    function (HolidayInfoFromModel) {
                        adminHolidayInfoAllApp.holidayInfo_list = HolidayInfoFromModel;
                    });
            }
    }
});

