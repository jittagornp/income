/**
 * @author jittagorn pitakmetagoon
 * create 25/12/2013
 */
(function($) {
    $(function() {
        var $webBody = $('#gtBody');
        $webBody.scroll(function() {
            var $calendarsPopup = $('.calendars-popup');
            var $inputFocus = $('.gt-calendar:focus');
            if ($inputFocus.length > 0){
                $calendarsPopup.css({
                    'top': ($inputFocus.offset().top - $calendarsPopup.height()) + 'px'
                });
            }
        });
    });
}).call(this, jQuery);