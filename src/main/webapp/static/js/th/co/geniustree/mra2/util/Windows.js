/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
window.Windows = window.Windows || (function($, window) {
    var __$widow = $(window);
    var __DEFAULT_DELAY_TIMEOUT = 500;

    return {
        resize: function(callback) {
            var timeoutReference;

            __$widow.resize(function() {
                if (timeoutReference) {
                    window.clearTimeout(timeoutReference);
                }

                timeoutReference = window.setTimeout(function() {
                    window.clearTimeout(timeoutReference);

                    if (typeof callback === 'function') {
                        callback();
                    }
                }, __DEFAULT_DELAY_TIMEOUT);
            });
        }
    };
}).call(this, jQuery, window);