$( document ).ready(function() {
    var currentURL = window.location.href;

    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }
    })

    $("#advertForm").submit(function(e) {
        e.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var obyavleniye = {
            title: $("input[name = 'title']").val(),
            price: $("input[name = 'price']").val(),
            description: $("input[name = 'description']").val(),
            date: $("input[name = 'date']").val(),
            authorid: $("input[name = 'authorid']").val(),
            category: $("input[name = 'category']").val(),
            state: $("input[name = 'state']").val(),
            img1: $("input[name = 'img1']").val(),
            img2: $("input[name = 'img2']").val(),
            img3: $("input[name = 'img3']").val(),
            img4: $("input[name = 'img4']").val(),
        };
        var post_data = JSON.stringify(obyavleniye);

        console.log(post_data);
        $.ajax({
            url : "/upload",
            type: "POST",
            dataType: 'json',
            data: post_data,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
                xhr.setRequestHeader(header, token);
            },
            complete: function() {
                console.log("Sent");
            },
            success: function (response) {
                console.log("success");
                console.log("response" + response);
            },
            error: function (data) {
                console.log("error");
                console.log(data);
            }
        });
    })
});