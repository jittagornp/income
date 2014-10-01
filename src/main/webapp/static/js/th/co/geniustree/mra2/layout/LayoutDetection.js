/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($, window, document) {
    window.sidebarCtrl = window.sidebarCtrl || {};
    window.sidebarCtrl.slideListeners = [];

    function forEach(array, callback, ctx) {
        var length = array.length;
        for (var i = 0; i < length; i++) {
            callback.call(ctx, array[i], i, array, length);
        }
    }

    $(function() {
        var DELAY = 200;
        var $window = $(window);
        var $toolbar = $('#gtToolbar');
        var $sidebar = $('#gtSidebar');
        var $sidebarHeader = $('#gtSidebarHeader');
        var $sidebarContent = $('#gtSidebarContent');
        var $sidebarCollapseButton = $('#gtSidebarCollapseButton');
        var $webBody = $('#gtBody');
        var $webBodyContent = $('#gtBodyContent');

        resizeLayout();
        Windows.resize(function() {
            resizeLayout();
        });


        var timeout = setTimeout(function() {
            window.clearTimeout(timeout);

            $webBody.addClass('gt-body-dark');
            $webBodyContent.addClass('gt-box-shadow');
        }, 2000);

        function resizeLayout() {
            var height = $window.height() - $sidebarHeader.height() - $toolbar.height();
            $sidebarContent.height(height - 10);
            $webBodyContent.css('min-height', height);
        }

        function layoutAnimate(distance, callback) {
            $sidebar.stop().animate({
                marginLeft: (distance - $sidebar.width())
            }, DELAY, function() {
                callback();
            });

            $webBody.animate({
                left: distance
            }, DELAY);
        }


        window.sidebarCtrl.addSlideListener = function(callback) {
            window.sidebarCtrl.slideListeners.push(callback);
        };

        $sidebarCollapseButton.click(function() {
            if (!$sidebar.hasClass('gt-callpase')) {
                layoutAnimate(0, function() {
                    $sidebar.addClass('gt-callpase');
                });

                forEach(window.sidebarCtrl.slideListeners, function(caller) {
                    caller('collapse', DELAY, $sidebar);
                });
            } else {
                layoutAnimate($sidebar.width(), function() {
                    $sidebar.removeClass('gt-callpase');
                });

                forEach(window.sidebarCtrl.slideListeners, function(caller) {
                    caller('expand', DELAY, $sidebar);
                });
            }
        });
    });
}).call(this, jQuery, window, document);