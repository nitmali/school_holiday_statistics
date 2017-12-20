var adminHolidayInfoApp =new Vue({
    el: '#adminHolidayInfo',
    data: {
        holidayInfo: '',
        getIt:false
    },
    created: function () {
        this.getHolidayInfo_of_Status(this, "START");
        this.getHolidayInfo_of_Status(this, "ACTIVATION");
    },
    methods: {
        updated_moreInfo: function () {
            this.post_to_api();
            alert("更新公告成功");
        },
        updated_holidayStatus_OVER: function () {
            this.holidayInfo.holidayStatus = "OVER";
            this.post_to_api();
            alert("更新假期状态成功");
        },
        updated_holidayStatus_ACTIVATION: function () {
            this.holidayInfo.holidayStatus = "ACTIVATION";
            this.post_to_api();
            alert("更新假期状态成功");
        },
        updated_holidayStatus_DELETE: function () {
            alert("暂不支持销毁假期");
        },
        updated_holidayStatus_START: function () {
            this.holidayInfo.holidayStatus = "START";
            this.post_to_api();
            alert("假期初始为开实状态");
        },
        post_to_api: function () {
            var that = this;
            $.post("/updated_holidayInfo",
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


