$( document ).ready(function() {
    var activeMenu = $(".active");

    if (activeMenu[0].innerText === "Мои объявления") {
        getAdverts("Самые новые");
    }

    $(".menu li a").on("click", function (event) {
        event.preventDefault();

        $(".menu li").removeClass("active");
        $(this).parent().addClass("active");

        var url = $(this).attr("href");
        if (url == 'adverts') {
            $(".page").css("display", "block");
            $(".settings").css("display", "none");
        }

        if (url == 'settings') {
            $(".page").css("display", "none");
            $(".settings").css("display", "block");
        }
    });
})


function getAdverts(sortBy) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var user = $("input[name='user']").attr("value");

    var category;
    var dropDown = $('.dropdown-toggle p')[0];
    if (dropDown != null) {
        category = dropDown.textContent;
    } else {
        category = "Все категории";
    }
    $.ajax({
        type : 'GET',
        url : '/getAdverts/' + category + '/' + sortBy + '/' + user,
        dataType : 'json',
        contentType : 'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            if (data != null) {
                showAdverts(data);
            }
        },
        error : function(response) {
            console.log('error get adverts');
        }
    });
}


function showAdverts(data) {
    var page = $('.page');
    page.empty();
    if (data.length == 0) {
        page.append("<p>У Вас пока еще нет объявлений!</p>");
    } else {
        page.append("<p>Ваши объявления</p>");

        for ( var i = 0, len = data.length; i < len; i++) {
            var advert = data[i];
            var advertImage;

            if (advert.imagesCount > 0) {
                advertImage = "<div class='advertImage'><a href='/obyavleniye/" + advert.id + "'><img src='/imageDisplay?advertId=" + advert.id + "&user=" + advert.authorUsername + "&fileName=" + "" +  "'/></a></div>";
            } else {
                advertImage = "<div class='advertImage'><a href='/obyavleniye/" + advert.id + "'><img src='/resources/images/image_not_found.png'></a></div>";
            }

            page.append("<div class='advertItem'>" + advertImage +
                "<div class='advertInfo'>" +
                "<a href='/obyavleniye/" + advert.id + "'><h2>" + advert.title + "</h2></a>" +
                "<p>" + advert.date + "</p></div>" +
                "<div class='advertPrice'><p>" + advert.price + "грн </p></div> " +
                "<div class='deleteAdvert'><a href='" + advert.id + "'>Удалить</a></div></div>");
        }
    }

    $(".deleteAdvert a").on("click", function (event) {
        event.preventDefault();
        deleteAdvert($(this).attr("href"));
    })


}

function deleteAdvert(advertId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type : 'GET',
        url : '/deleteAdvert/' + advertId,
        dataType : 'text',
        contentType : 'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            console.log("response: " + data);
            getAdverts("Самые новые");
        },
        error : function(response) {
            console.log('error delete adverts');
        }
    });
}