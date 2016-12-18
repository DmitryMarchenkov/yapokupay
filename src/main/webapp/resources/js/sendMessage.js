$( document ).ready(function() {
    $(".send_message_to_author").on("click", function(e) {
        $("#shadowBackground").css("display", "block");
    });

    $(document).mouseup(function (e){
        var div = $("#messageToAuthor");
        if (!div.is(e.target)
            && div.has(e.target).length === 0) {
            $("#shadowBackground").css('display', 'none');
        }
    });

    $("#messageForm").on("submit", function(e) {
        e.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        debugger;
        var sendMessage = {
            email: $("#inputEmail")[0].value,
            message: $("#message")[0].value,
            authName: $("#authName")[0].value
        };

        $.ajax({
            url : "/messageToAuthor",
            type: "POST",
            dataType: 'text',
            data: sendMessage,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            complete: function() {
                console.log("Sent");
                $("#shadowBackground").css('display', 'none');
            },

            success: function (response) {
                console.log("success");
                console.log("response: " + response);
                alert("Ваше сообщение отправлено!");
            },
            error: function (data) {
                console.log("error");
                console.log(data);
            }
        });
    })
})