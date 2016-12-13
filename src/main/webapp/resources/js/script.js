$( document ).ready(function() {
    $(".dropdown-menu a").on("click", function () {
        var url = $(this).attr("href");
        if (url == '#') {
            history.replaceState('', '', '/');
        } else {
            history.pushState('', '', url);
        }
    })

    $('#searchLine input').keyup(function(e){
        if(e.keyCode == 13)
        {
            var searchQuery = this.value;
            console.log(searchQuery);
            location.href = '/search/' + searchQuery;
        }
    });
});