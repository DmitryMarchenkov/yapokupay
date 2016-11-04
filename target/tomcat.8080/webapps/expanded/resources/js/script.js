$( document ).ready(function() {
    var currentURL = window.location.href;

    if (/home$/.test(currentURL)) {
        $('.pg_home').css( "left", "0px" );
        $('.pg_home').addClass("visible");
    }

    if (/about$/.test(currentURL)) {
        $('.pg_about').css( "left", "0px" );
        $('.pg_about').addClass("visible");
    }

    if (/products$/.test(currentURL)) {
        $('.pg_products').css( "left", "0px" );
        $('.pg_products').addClass("visible");
    }

    if (/contacts$/.test(currentURL)) {
        $('.pg_contacts').css( "left", "0px" );
        $('.pg_contacts').addClass("visible");
    } else {
        history.pushState('', 'Online Shop', "/home");
        $('.pg_home').css( "left", "0px" );
        $('.pg_home').addClass("visible");
    }


    $("#menu a").on("click", function () {
        var pgURL = window.location.href;
        var url = $(this).attr('href');
        if (pgURL.endsWith(url)) {
            event.preventDefault();
            return;
        }

        var from = $(".visible");

        if (/home$/.test(url)) {
            changePage(from, $('.pg_home'));
        }

        if (/about$/.test(url)) {
            changePage(from, $('.pg_about'));
        }

        if (/products$/.test(url)) {
            $.ajax({
                url:     url + '/listProducts',
                success: function(response){
                    $('#products').empty();
                    $('<table id="product_table"></table>').appendTo('#products');
                    $('<tr>').append(
                        $('<td>').text("ID"),
                        $('<td>').text("Name"),
                        $('<td>').text("Price")
                    ).appendTo('#product_table');

                    $(function() {
                        $.each(response, function(i, item) {
                            $('<tr>').append(
                                $('<td>').text(item.id),
                                $('<td>').text(item.name),
                                $('<td>').text(item.price)
                            ).appendTo('#product_table');
                        });
                    });
                    changePage(from, $('.pg_products'));
                }
            });
        }

        if (/contacts$/.test(url)) {
            changePage(from, $('.pg_contacts'));
        }

        if(url != window.location){
            window.history.pushState(null, null, url);
        }
        return false;
    })

    $(window).bind('popstate', function() {
        $.ajax({
            url:     location.pathname,
            success: function(data) {
                $('#content').html(data);
            }
        });
    });

});

function changePage(from, to) {
    to.css( "left", "100%" );
    from.animate({"left": "-=100%"}, "slow");
    to.animate({"left": "0px"}, "slow", function () {
        to.addClass("visible");
        from.removeClass("visible");
    });
}