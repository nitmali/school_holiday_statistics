var that = new Vue({
    el: '#studentHolidayPlan',
    data: {
        get_holidayPlan: '',
        set_holidayPlan: {
            leaveTime: undefined,
            backTime: undefined,
            whereToGo: undefined
        },
        get_holidayInfo: true,
        watchWork:0
    },
    created: function () {
        this.get_holiday_plan_api();
    },
    watch: {
        get_holidayPlan: {
            handler: function () {
                that.watchWork++;
                that.get_holidayInfo = false;
                if(!that.get_holidayPlan.leaveTime)
                {
                    that.get_holidayPlan.whereToGo = "留校";
                    that.set_holidayPlan.whereToGo = "留校";
                    that.get_holidayPlan.leaveTime = that.get_holidayPlan.holidayStartTime;
                    that.get_holidayPlan.backTime = that.get_holidayPlan.holidayEndTime;
                }
                if (that.get_holidayPlan.whereToGo !== "留校") {
                    that.set_holidayPlan.whereToGo = that.get_holidayPlan.whereToGo;
                    that.get_holidayPlan.whereToGo = "离校";
                    that.set_holidayPlan.leaveTime = that.get_holidayPlan.leaveTime;
                    that.set_holidayPlan.backTime = that.get_holidayPlan.backTime;
                }
            }
        }
    },
    methods: {
        change_time:function () {
            that.set_holidayPlan.leaveTime = that.get_holidayPlan.leaveTime;
            that.set_holidayPlan.backTime = that.get_holidayPlan.backTime;
        },
        change_whereToGo:function () {
            
            if (that.get_holidayPlan.whereToGo !== "留校") {
                that.set_holidayPlan.whereToGo = "";
                that.set_holidayPlan.leaveTime = that.get_holidayPlan.leaveTime;
                that.set_holidayPlan.backTime = that.get_holidayPlan.backTime;
            } else {
                that.set_holidayPlan.whereToGo = "留校";
                that.set_holidayPlan.leaveTime = undefined;
                that.set_holidayPlan.backTime = undefined;
                that.get_holidayPlan.leaveTime = that.get_holidayPlan.holidayStartTime;
                that.get_holidayPlan.backTime = that.get_holidayPlan.holidayEndTime;
            }
        },
        set_holiday_plan_api:function () {
            $.post("/set_holiday_plan",
                {
                    whereToGo: that.set_holidayPlan.whereToGo,
                    leaveTime: that.set_holidayPlan.leaveTime,
                    backTime: that.set_holidayPlan.backTime
                },
                function (message) {
                    if(message === "success") {
                        window.location.href = "/success";
                    }else if (message === "Time Error")
                    {
                        alert("请填写有效时间区间");
                    }else {
                        alert("请填写有效信息");
                    }
                }
            );
        },
        get_holiday_plan_api: function () {
            $.get("/get_holiday_plan",
                function (get_holidayPlanFormModel) {
                    that.get_holidayPlan = get_holidayPlanFormModel;
                }
            )
        }
    }
});