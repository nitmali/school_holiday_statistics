new Vue({
    el: '#studentHolidayInfo',
    data: {
        holidayInfo: ''
    },
    created: function () {
        var that = this;
        $.get("/get_stat_holiday_info",
            function (HolidayInfoFromModel) {
                that.holidayInfo = HolidayInfoFromModel;
            });
    }
});
