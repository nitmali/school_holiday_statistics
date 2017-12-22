var whereToGo = jQuery("#whereToGo");
var leaveTime = jQuery("#leaveTime");
var backTime = jQuery("#backTime");
var goAway = jQuery("#goAway");


function changeStatus() {
    if (whereToGo.val() === "留校") {
        goAway.attr("type","hidden");
        leaveTime.prop("disabled", true);
        backTime.prop("disabled", true);

    } else {
        goAway.val("");
        goAway.attr("type","text");
        leaveTime.prop("disabled", false);
        backTime.prop("disabled", false);
    }
    return changeStatus;
}

whereToGo.change(changeStatus());

function setminbackTime(mintime) {
    backTime.attr("min",mintime);
}


