var whereToGo = jQuery("#whereToGo");
var leaveTime = jQuery("#leaveTime");
var backTime = jQuery("#backTime");
var goAway = jQuery("#goAway")

function changeStatus() {
    if (whereToGo.val() === "STAY_IN_SCHOOL") {
        goAway.val("留校");
        goAway.hide();
        leaveTime.prop("disabled", true);
        backTime.prop("disabled", true);

    } else {
        goAway.val("");
        goAway.show();
        leaveTime.prop("disabled", false);
        backTime.prop("disabled", false);
    }
    return changeStatus;
}

whereToGo.change(changeStatus());

function setminbackTime(mintime) {
    backTime.attr("min",mintime);
}


