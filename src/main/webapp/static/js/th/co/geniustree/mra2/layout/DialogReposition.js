/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($) {
    $(function() {
        reposition();
        Windows.resize(function(){
            reposition();
        });

        function reposition() {
            var $dialogs = $('.ui-dialog');
            $dialogs.each(function() {
                var $dialog = $(this);
                var width = $dialog.width();
                var height = $dialog.height();

                $dialog.css({
                    'top': '50%',
                    'left': '50%',
                    'margin-left': (-width / 2) + 'px',
                    'margin-top': (-height / 2) + 'px'
                });
            });
        }
    });
}).call(this, jQuery);