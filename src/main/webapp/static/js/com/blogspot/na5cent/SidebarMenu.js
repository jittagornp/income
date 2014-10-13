/**
 * @author jittagorn pitakmetagoon
 * create 13/10/2014
 */
(function($) {
    function notInclude(str, notation) {
        var index = str.indexOf(notation);
        if (index === -1) {
            return str;
        }

        return str.substring(0, index);
    }

    $(function() {
        var $menuItem = $('.sidebar-menu li');
        $menuItem.each(function() {
            var $item = $(this);
            var $link = $item.find('a');
            var href = $link.attr('href');
            href = notInclude(href, '?');
            href = notInclude(href, ';');

            var url = IX.config.HOST + href;
            if (url === location.href) {
                $item.addClass('active');
            }
        });
    });
})(jQuery);