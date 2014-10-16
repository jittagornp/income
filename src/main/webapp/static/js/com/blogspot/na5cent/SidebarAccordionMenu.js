/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($, win, config) {

    function notInclude(str, notation) {
        var index = str.indexOf(notation);
        if (index === -1) {
            return str;
        }

        return str.substring(0, index);
    }

    function filterUrl(url) {
        url = notInclude(url, '?');
        url = notInclude(url, ';');

        if (url[url.length - 1] === '/') {
            url = url.substring(0, url.length - 1);
        }

        return url;
    }

    function bindMenu(menus) {
        for (var prop in menus) {
            if (menus.hasOwnProperty(prop)) {
                var callback = function(event) {
                    if (event.preventDefault) {
                        event.preventDefault();
                    } else {
                        return false;
                    }

                    $(this).find('.ui-icon')
                            .removeClass('ui-icon-triangle-1-s')
                            .addClass('ui-icon-triangle-1-e');
                    
                    win.location.href = $(this).attr('data-href');
                };

                $('.sidebar-accordion-menu ' + prop)
                        .on('click', callback)
                        .attr('data-href', menus[prop]);
            }
        }
    }

    $(function() {
        if (config.sidebarMenus) {
            bindMenu(config.sidebarMenus);
        }

        var url = filterUrl(win.location.href);

        var $accordion = $('.sidebar-accordion-menu .ui-accordion');
        $accordion.find('.ui-accordion-content').each(function() {
            var $item = $(this);
            $item.find('a').each(function() {
                var $link = $(this);
                var href = filterUrl(config.HOST + $link.attr('href'));

                if (href === url) {
                    var $header = $item.prev().click();
                    $header.addClass('header-menu-highlight');
                    $link.addClass('menu-highlight');
                }
            });
        });

        $accordion.find('.ui-accordion-header').each(function() {
            var $item = $(this);
            var href = filterUrl(config.HOST + $item.attr('data-href'));

            if (href === url) {
                $item.addClass('header-menu-highlight');
                $item.find('.ui-icon')
                        .removeClass('ui-icon-triangle-1-e')
                        .addClass('ui-icon-triangle-1-s');
            }
        });
    });
})(jQuery, window, App.config);