$( document ).ready(function() {
    setTimeout(increaseViewCounter, 2000);

    $(document).ready(function() {
        $(".slider").each(function () { // обрабатываем каждый слайдер
            var obj = $(this);
            $(obj).append("<div class='nav'></div>");
            $(obj).find("li").each(function () {
                $(obj).find(".nav").append("<span rel='"+$(this).index()+"'></span>"); // добавляем блок навигации
                $(this).addClass("slider"+$(this).index());
            });
            $(obj).find("span").first().addClass("on"); // делаем активным первый элемент меню
        });
    });
    function sliderJS (obj, sl) { // slider function
        var ul = $(sl).find("ul"); // находим блок
        var bl = $(sl).find("li.slider"+obj); // находим любой из элементов блока
        var step = $(bl).width(); // ширина объекта
        $(ul).animate({marginLeft: "-"+step*obj}, 500); // 500 это скорость перемотки
    }
    $(document).on("click", ".slider .nav span", function() { // slider click navigate
        var sl = $(this).closest(".slider"); // находим, в каком блоке был клик
        $(sl).find("span").removeClass("on"); // убираем активный элемент
        $(this).addClass("on"); // делаем активным текущий
        var obj = $(this).attr("rel"); // узнаем его номер
        sliderJS(obj, sl); // слайдим
        return false;
    });
})

function increaseViewCounter() {
    // debugger;
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var advertId = $("input[name='advertId']").attr("value");

    $.ajax({
        url : "/increaseViewCount/" + advertId,
        type: "POST",
        // dataType: 'text',
        // data: 'increaseViewCount',
        // processData: false,
        // contentType: false,
        // cache: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        // complete: function() {
        //     console.log("Sent");
        // },
        // success: function (response) {
        //     console.log("success");
        //     console.log("response: " + response);
        // },
        // error: function (data) {
        //     console.log("error");
        //     console.log(data);
        // }
    });
}
