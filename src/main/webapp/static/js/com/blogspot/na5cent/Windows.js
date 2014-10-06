/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
window.Windows = window.Windows || (function($, window) {
    var $widow = $(window);
    var DEFAULT_DELAY_TIMEOUT = 500;

    return {
        resize: function(callback) {
            var timeoutReference;

            $widow.resize(function() {
                if (timeoutReference) {
                    window.clearTimeout(timeoutReference);
                }

                timeoutReference = window.setTimeout(function() {
                    window.clearTimeout(timeoutReference);

                    if (typeof callback === 'function') {
                        callback();
                    }
                }, DEFAULT_DELAY_TIMEOUT);
            });
        }
    };
}).call(this, jQuery, window);