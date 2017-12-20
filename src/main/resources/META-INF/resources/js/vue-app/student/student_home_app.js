new Vue({
    el: '#studentHolidayInfo',
    data: {
        holidayInfo: '',
        getIt:false
    },
    created: function () {
        this.getHolidayInfo_of_Status(this, "START");
        this.getHolidayInfo_of_Status(this, "ACTIVATION");
    },
    methods:{
        getHolidayInfo_of_Status: function (that, holidayStatus) {
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
