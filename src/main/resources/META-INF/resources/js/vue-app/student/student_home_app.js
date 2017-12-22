new Vue({
    el: '#studentHolidayInfo',
    data: {
        holidayInfo: '',
        getIt:false
    },
    created: function () {
        this.get_holiday_info_of_status(this, "START");
        this.get_holiday_info_of_status(this, "ACTIVATION");
    },
    methods:{
        get_holiday_info_of_status: function (that, holidayStatus) {
            $.get("/get_holidayInfo_of_Status",
                {
                    holidayStatus: holidayStatus
                },
                function getHolidayInfo(HolidayInfo) {
                    if (HolidayInfo.holidayName !== undefined) {
                        that.holidayInfo = HolidayInfo;
                        that.getIt = true;
                    }
                });
        }
    }
});
