/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($, Ws) {
    $(function() {
        reposition();
        Ws.resize(reposition);

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
})(jQuery, Windows);