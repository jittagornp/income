/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
window.Windows = window.Windows || (function($, win, DELAY_TIMEOUT) {

    var $widow = $(win);

    return {
        resize: function(callback) {
            var timeout;

            $widow.resize(function() {
                if (timeout) {
                    clearTimeout(timeout);
                }

                timeout = setTimeout(function() {
                    clearTimeout(timeout);

                    if (typeof callback === 'function') {
                        callback();
                    }
                }, DELAY_TIMEOUT);
            });
        }
    };
})(jQuery, window, 500);