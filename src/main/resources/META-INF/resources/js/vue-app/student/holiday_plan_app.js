new Vue({
    el: '#studentHolidayPlan',
    data: {
        holidayPlan: '',
        whereToGo:''
    },
    created: function () {
        this.get_holiday_plan(this);
    },
    methods: {

        set_holiday_plan:function () {
            var that = this;
            if (that.holidayPlan.whereToGo === "留校")
            {
                that.whereToGo = that.holidayPlan.whereToGo;
                alert(that.whereToGo);
            }
            $.post("/set_holiday_plan",
                {
                    whereToGo:that.whereToGo,
                    leaveTime:that.holidayPlan.leaveTime,
                    backTime:that.holidayPlan.backTime
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
                    that.holidayPlan = holidayPlanFormModel;
                    if (that.holidayPlan.leaveTime === null)
                    {
                        that.holidayPlan.leaveTime = holidayPlanFormModel.holidayStartTime;
                    }
                    if (that.holidayPlan.backTime === null)
                    {
                        that.holidayPlan.backTime = holidayPlanFormModel.holidayEndTime;
                    }
                    if(holidayPlanFormModel.whereToGo !== "留校")
                    {
                        that.whereToGo = holidayPlanFormModel.whereToGo;
                    }
                    if(that.holidayPlan.whereToGo !== "留校")
                    {
                        that.holidayPlan.whereToGo = "离校"
                    }
                }
            )
        }
    }
});