var managerHolidayInfoApp = new Vue({
    el: '#managerHolidayInfo',
    data: {
        holidayInfo: '',
        getIt: false
    },
    created: function () {
        this.get_holiday_info_of_status("START");
        this.get_holiday_info_of_status("ACTIVATION");
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
        delete_holiday: function (holidayId) {
            if (confirm("是否确认您的操作\n销毁假期同时也会销毁相应的假期计划！") === true) {
                $.get("/manager/delete_holiday_info",
                    {
                        holidayId: holidayId
                    },
                    function (message) {
                        if (message === "success") {
                            alert("销毁成功！");
                            managerHolidayInfoApp.holidayInfo = '';
                        } else {
                            alert("error");
                        }
                    }
                );
            }
        },
        updated_holiday_status_START: function () {
            this.holidayInfo.holidayStatus = "START";
            this.updated_holiday_info();
            alert("假期初始为开实状态");
        },
        updated_holiday_info: function () {
            var managerHolidayInfoApp = this;
            $.post("/manager/updated_holiday_info",
                {
                    holidayId: managerHolidayInfoApp.holidayInfo.holidayId,
                    holidayName: managerHolidayInfoApp.holidayInfo.holidayName,
                    holidayStartTime: managerHolidayInfoApp.holidayInfo.holidayStartTime,
                    holidayEndTime: managerHolidayInfoApp.holidayInfo.holidayEndTime,
                    holidayStatus: managerHolidayInfoApp.holidayInfo.holidayStatus,
                    moreInfo: managerHolidayInfoApp.holidayInfo.moreInfo
                }
            );
        },
        get_holiday_info_of_status: function (holidayStatus) {
            $.get("/public/get_holidayInfo_of_Status",
                {
                    holidayStatus: holidayStatus
                },
                function getHolidayInfo(HolidayInfo) {
                    if (HolidayInfo.holidayName !== undefined) {
                        managerHolidayInfoApp.holidayInfo = HolidayInfo;
                        managerHolidayInfoApp.getIt = true;
                    }
                });
        }
    }
});


