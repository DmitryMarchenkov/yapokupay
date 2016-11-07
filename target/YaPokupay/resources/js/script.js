$( document ).ready(function() {
    var currentURL = window.location.href;

    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        // debugger;
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }

    })

    alert("werwer");

});