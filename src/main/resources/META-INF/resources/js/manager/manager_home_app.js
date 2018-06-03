var managerHolidayInfoApp = new Vue({
    el: '#managerHolidayInfo',
    data: {
        holidayInfo: '',
        getIt: false
    },
    created: function () {
        this.get_holiday_info_of_status(this, "START");
        this.get_holiday_info_of_status(this, "ACTIVATION");
    },
    methods: {
        updated_more_info: function () {
            this.updated_holiday_info();
            alert("更新公告成功");
        },
        updated_holiday_status_OVER: function () {
            this.holidayInfo.holidayStatus = "OVER";
            this.updated_holiday_info();
            alert("更新假期状态成功");
        },
        updated_holiday_status_ACTIVATION: function () {
            this.holidayInfo.holidayStatus = "ACTIVATION";
            this.updated_holiday_info();
            alert("更新假期状态成功");
        },
        updated_holiday_status_DELETE: function () {
            $.get("/manager/delete_holiday_info",
                {
                    holidayId: managerHolidayInfoApp.holidayInfo.holidayId
                },
                function (message) {
                    if (message === "success") {
                        alert(message);
                    } else {
                        alert("error");
                    }
                }
            );

        },
        updated_holiday_status_START: function () {
            this.holidayInfo.holidayStatus = "START";
            this.updated_holiday_info();
            alert("假期初始为开实状态");
        },
        updated_holiday_info: function () {
            var that = this;
            $.post("/manager/updated_holiday_info",
                {
                    holidayId: that.holidayInfo.holidayId,
                    holidayName: that.holidayInfo.holidayName,
                    holidayStartTime: that.holidayInfo.holidayStartTime,
                    holidayEndTime: that.holidayInfo.holidayEndTime,
                    holidayStatus: that.holidayInfo.holidayStatus,
                    moreInfo: that.holidayInfo.moreInfo
                }
            );
        },
        get_holiday_info_of_status: function (that, holidayStatus) {
            $.get("/public/get_holidayInfo_of_Status",
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


