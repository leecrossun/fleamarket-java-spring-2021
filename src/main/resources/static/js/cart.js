$(document).ready(function () {
    $("#cart").submit(function (event) {
        $.ajax({
            type: "POST",
            url: "/order/create",
            data: {
                'myvalue': $('id').val()
            },
        });
        return false;
    });
});