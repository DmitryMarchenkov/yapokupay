$( document ).ready(function() {
    var path = window.location.pathname;
    // window.ifUserPage = false;
    // window.user = "not";
    if (path === "/" || path === "/all") {
        getAdverts("Самые новые");
    }

    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }
    });

    $('#searchLine input').keyup(function(e){
        if(e.keyCode == 13)
        {
            var searchQuery = this.value;
            console.log(searchQuery);
            if (searchQuery.replace(/^\s+|\s+$/g, '') != '') {
                var category = $('.dropdown-toggle p')[0].textContent;
                searchAdverts(searchQuery, category);
            } else {
                getAdverts("Самые новые");
            }
        }
    });

    $("#chooseSort button").on("click", function (event) {
        event.preventDefault();
        // var searchQuery = $('#searchLine input').value;
        //
        // if (searchQuery.replace(/^\s+|\s+$/g, '') != '') {
        //     var category = $('.dropdown-toggle p')[0].textContent;
        //     searchAdverts(searchQuery, category);
        // } else {
        //     getAdverts("Самые новые");
        // }
        getAdverts(this.innerText);
    })
});


function getAdverts(sortBy) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");


    var category;
    var dropDown = $('.dropdown-toggle p')[0];
    if (dropDown != null) {
        category = dropDown.textContent;
    } else {
        category = "Все категории";
    }
    $.ajax({
        type : 'GET',
        url : '/getAdverts/' + category + '/' + sortBy + '/' + 'not',
        dataType : 'json',
        contentType : 'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            if (data != null) {
                showAdverts(data, false);
            }
        },
        error : function(response) {
            console.log('error get adverts');
        }
    });
}

function searchAdverts(searchQuery, category, sortBy) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type : 'GET',
        url : '/search/' + searchQuery + "/" + category,
        dataType : 'json',
        contentType : 'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            showAdverts(data, true);
        },
        error : function(response) {
            console.log('error search');
        }
    });
}

function showAdverts(data, ifSearch) {
    var page = $('.page');
    page.empty();
    if (data.length == 0) {
            page.append("<p>В категории <strong>" + $('.dropdown-toggle p')[0].textContent + "</strong> ничего не найдено.</p>");
    } else {
        if (ifSearch) {
            page.append("<p>Результаты поиска в категории <strong>" + $('.dropdown-toggle p')[0].textContent + "</strong></p>");
        } else {
            page.append("<p>Все товары в категории <strong>" + $('.dropdown-toggle p')[0].textContent + "</strong></p>");
        }

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
                "<div class='advertPrice'><p>" + advert.price + "грн </p></div></div>");
        }
    }
}
