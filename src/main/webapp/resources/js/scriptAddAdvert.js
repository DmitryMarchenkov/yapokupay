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

    /*$("#advertForm").submit(function() {
        // alert('go');
        // debugger;
        // var token = $('#csrfToken').val();
        // var header = $('#csrfHeader').val();
        // event.preventDefault();
        var form_data = $(this).serialize(); //собераем все данные из формы
        $.ajax({
            // url: "/advert/add",
            url : "/advert/add",
            type: "POST", //Метод отправки
            crossDomain: true,
            dataType: 'json',
            async : true,
            data: form_data,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                // xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + password));
                // xhr.setRequestHeader(header, token);
            },
            complete: function(dd) {
                // alert('Ваше сообщение отправлено!');
              /!*  setTimeout(function () {
                    $(".shadow").css('display', 'none');
                    window.history.pushState(null, null, '/');
                    $("#form")[0].reset();
                }, 2000);*!/
            },
        });
    })*/
});