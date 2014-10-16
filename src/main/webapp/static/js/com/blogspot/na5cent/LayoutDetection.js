/**
 * @author jittagorn pitakmetagoon
 * create 15/10/2014
 */
(function($, win, DELAY) {
    win.sidebarListeners_ = [];
    win.addSidebarListener = function(listener) {
        win.sidebarListeners_.push(listener);
    };

    function forEach(array, callback, ctx) {
        var length = array.length;
        for (var i = 0; i < length; i++) {
            callback.call(ctx, array[i], i, array, length);
        }
    }

    $(function() {
        var $sidebar = $('.sidebar');
        var $toggle = $('.sidebar-toggle');
        var $container = $('.container');

        function animate(distance, callback) {
            $sidebar.stop().animate({
                marginLeft: (distance - $sidebar.width())
            }, DELAY, callback);

            $container.stop().animate({
                left: distance
            }, DELAY);
        }

        function expand() {
            animate($sidebar.width(), function() {
                $sidebar.removeClass('callpase');
            });

            forEach(win.sidebarListeners_, function(caller) {
                caller('expand', DELAY, $sidebar);
            });
        }

        function collapse() {
            animate(0, function() {
                $sidebar.addClass('callpase');
            });

            forEach(win.sidebarListeners_, function(caller) {
                caller('collapse', DELAY, $sidebar);
            });
        }

        $toggle.on('click', function() {
            $sidebar.hasClass('callpase')
                    ? expand()
                    : collapse();
        });
    });
})(jQuery, window, 200);