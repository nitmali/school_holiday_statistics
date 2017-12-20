var adminHolidayInfoAllApp = new Vue({
    el: '#adminHolidayInfoAll',
    data: {
        holidayInfo_list: null
    },
    created: function () {
        this.getHolidayInfo_all();
    },
    methods: {
        setStat: function (holidayStatus) {
            this.getHolidayInfo_of_Status(holidayStatus);

        },
        getAll: function (holidayStatus) {
            this.getHolidayInfo_all(holidayStatus);
        },
        getHolidayInfo_all:function () {
            $.get("/get_holidayInfo_all",
                {
                    holidayStatus: "START"
                },
                function (data) {
                    adminHolidayInfoAllApp.holidayInfo_list = data;
                });
        },
        getHolidayInfo_of_Status: function (holidayStatus) {
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

