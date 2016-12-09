$( document ).ready(function() {
    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }
    });


    //
    //
    //     maxFileSize = 1000000; // максимальный размер фалйа - 1 мб.
    //
    //
    // // Обрабатываем событие Drop
    // dropZone[0].ondrop = function(event) {
    //     event.preventDefault();
    //     dropZone.removeClass('hover');
    //     dropZone.addClass('drop');
    //
    //     var file = event.dataTransfer.files[0];
    //
    //     // Проверяем размер файла
    //     if (file.size > maxFileSize) {
    //         dropZone.text('Файл слишком большой!');
    //         dropZone.addClass('error');
    //         return false;
    //     }
    //
    //     // Создаем запрос
    //     var xhr = new XMLHttpRequest();
    //     xhr.upload.addEventListener('progress', uploadProgress, false);
    //     xhr.onreadystatechange = stateChange;
    //     xhr.open('POST', '/upload.php');
    //     xhr.setRequestHeader('X-FILE-NAME', file.name);
    //     xhr.send(file);
    // };
    //
    // // Показываем процент загрузки
    // function uploadProgress(event) {
    //     var percent = parseInt(event.loaded / event.total * 100);
    //     dropZone.text('Загрузка: ' + percent + '%');
    // }
    //
    // // Пост обрабочик
    // function stateChange(event) {
    //     if (event.target.readyState == 4) {
    //         if (event.target.status == 200) {
    //             dropZone.text('Загрузка успешно завершена!');
    //         } else {
    //             dropZone.text('Произошла ошибка!');
    //             dropZone.addClass('error');
    //         }
    //     }
    // }
    //



    // var imagesFiles;
    // var imagesFilesTmp;
    // $("#uploadImages").on("change", function (event) {
    //     debugger;
    //     imagesFiles = event.target.files;
    //
    //     $(imagesFiles).each(function () {
    //         imagesFilesTmp = imagesFiles;
    //     })
    // })


    // $("#uploadImages").on("change",function(event) {
    //     debugger;
    //     files = event.target.files;
    // });


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
            description: $("input[name = 'description']").val(),
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
                // var advert = $.parseJSON(response);
                sendImages(user, title);
                console.log("success");
                console.log("response: " + response);

            },
            error: function (data) {
                console.log("error");
                console.log(data);
            }
        });

    });

});




function sendImages(user, title) {
    debugger;
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var formData = new FormData();
    var files = $(".file")[0].files;
    debugger;

    $.each(files, function (i, val) {
        console.log("ind: " + i + ", val = " + val);
        formData.append("files", val);
    });

    console.log("123:" + formData.getAll("files"));
    $.ajax({
        url : "/uploadImages/" + user + "/" + title,
        type: "POST",
        dataType: 'text',
        data: formData,
        processData: false,
        contentType: false,
        // scriptCharset: "utf-8",
        cache: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        complete: function() {
            console.log("Sent");
        },
        success: function (response) {
            console.log("success");
            console.log("response: " + response);
        },
        error: function (data) {
            console.log("error");
            console.log(data);
        }
    });


}