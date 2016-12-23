$( document ).ready(function() {
    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }
    });

    $("#advertForm").submit(function(e) {

        e.preventDefault();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var categories = document.getElementById("categories");
        var category = categories.options[categories.selectedIndex].text;
        var user = $("#profile").html();

        var files = $(".file")[0].files;

        var state = document.getElementById("state");
        var choosedState = state.options[state.selectedIndex].text;
        var title = $("input[name = 'title']").val();
        debugger;
        var obyavleniye = {
            title: title,
            price: $("input[name = 'price']").val(),
            description: $("#description").val(),
            authorid: $("input[name = 'authorid']").val(),
            category: category,
            state: choosedState,
            imagesCount: files.length,
        };

        var post_data = JSON.stringify(obyavleniye);
        console.log(post_data);

        $.ajax({
            url : "/saveAdvert",
            type: "POST",
            dataType: 'text',
            data: post_data,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            complete: function() {
                console.log("Sent");
            },
            success: function (response) {
                debugger;
                console.log("success");
                console.log("response: " + response);
                var advert = $.parseJSON(response);
                sendImages(advert.id);

            },
            error: function (data) {
                console.log("error");
                console.log(data);
            }
        });

    });
});

function sendImages(advertid) {
    debugger;
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var formData = new FormData();
    var files = $(".file")[0].files;

    // var bar = $('.bar');
    // var percent = $('.percent');
    // var status = $('#status');

    $.each(files, function (i, val) {
        console.log("ind: " + i + ", val = " + val);
        formData.append("files", val);
    });
    console.log("123:" + formData.getAll("files"));
    $.ajax({
        url : "/uploadImages/" + advertid,
        type: "POST",
        dataType: 'text',
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){
                myXhr.upload.addEventListener('progress',progress, false);
            }
            return myXhr;
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
            // status.empty();
            // var percentVal = '0%';
            // bar.width(percentVal);
            // percent.html(percentVal);

        },
        complete: function() {
            console.log("Sent");
        },
        success: function (response) {
            console.log("success");
            console.log("response: " + response);
            alert("Объявление успешно сохранено!");
            window.location = "/";
        },
        error: function (data) {
            console.log("error");
            console.log(data);
        }
    }).done(function (res) {
        console.log("res: " + res);
    });
}


function progress(e){

    if(e.lengthComputable){
        var max = e.total;
        var current = e.loaded;
        var progress = $(".progress");

        var Percentage = (current * 100)/max;
        progress.attr("value", Percentage);
        console.log(Percentage);


        if(Percentage >= 100)
        {
            // process completed SUCCESS SAVED
        }
    }
}