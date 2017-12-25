var that = new Vue({
    el: '#studentHolidayAddition',
    data: {
        set_holidayAddition: {
            expectedBackTime: ''

        }
    },
    created: function () {
        var myDate = new Date();
        this.set_holidayAddition.expectedBackTime = myDate.toLocaleDateString().replace(/\//g, "-");
    },
    methods: {
        set_holiday_addition_api: function () {
            $.post("/set_holiday_addition",
                {
                    expectedBackTime: new Date(that.set_holidayAddition.expectedBackTime.replace(/-/g, "/")),
                    addition: that.set_holidayAddition.addition
                },
                function (message) {
                    if(message=== "undefine holiday"){
                        alert("没有找到假日或未填写离校信息");
                    }
                    else {
                        window.location.href = "/success";
                    }
                });
        }
    }
});