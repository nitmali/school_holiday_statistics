new Vue({
    el: '#studentHolidayPlan',
    data: {
        holidayPlan: '',
        set_whereToGo: '',
        set_leaveTime: undefined,
        set_backTime: undefined
    },
    created: function () {
        this.get_holiday_plan(this);
    },
    methods: {
        set_holiday_plan:function () {
            var that = this;
            if (that.holidayPlan.whereToGo === "留校")
            {
                that.set_whereToGo = that.holidayPlan.whereToGo;
            } else {
                that.set_leaveTime = that.holidayPlan.leaveTime;
                that.set_backTime = that.holidayPlan.backTime;
            }
            $.post("/set_holiday_plan",
                {
                    whereToGo: that.set_whereToGo,
                    leaveTime: that.set_leaveTime,
                    backTime: that.set_backTime
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
        get_holiday_plan: function (that) {
            $.get("/get_holiday_plan",
                function (holidayPlanFormModel) {
                    if (holidayPlanFormModel !== "") {
                        that.holidayPlan = holidayPlanFormModel;
                        if (that.holidayPlan.leaveTime === null) {
                            that.holidayPlan.leaveTime = holidayPlanFormModel.holidayStartTime;
                        }
                        if (that.holidayPlan.backTime === null) {
                            that.holidayPlan.backTime = holidayPlanFormModel.holidayEndTime;
                        }
                        if (holidayPlanFormModel.whereToGo !== "留校") {
                            that.set_whereToGo = holidayPlanFormModel.whereToGo;
                        }
                        if (that.holidayPlan.whereToGo === null) {
                            that.holidayPlan.whereToGo = "留校";
                        }
                        if (that.holidayPlan.whereToGo !== "留校") {
                            that.holidayPlan.whereToGo = "离校";
                        }
                    } else {
                        that.holidayPlan = {
                            "holidayName": null,
                            "holidayStartTime": new Date().toLocaleDateString().replace(/\//g, "-"),
                            "holidayEndTime": new Date().toLocaleDateString().replace(/\//g, "-"),
                            "leaveTime": new Date().toLocaleDateString().replace(/\//g, "-"),
                            "backTime": new Date().toLocaleDateString().replace(/\//g, "-"),
                            "whereToGo": null
                        };

                    }
                }
            )
        }
    }
});