/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($, window) {
    $(function() {
        var $accountInformation = $('#gtAccountInformation');
        var $accountInformationBox = $('#gtAccountInformationBox').attr('tabindex', -1);
        
        if ($accountInformationBox.length === 0) {
            $accountInformation.click(function() {
                window.location.href = window.MRA.config.CONTEXT_PATH + '/authen/';
            });
        } else {
            $accountInformation.click(function() {
                $accountInformationBox.show().focus();
            });

            var isPanelHover = false;
            $accountInformationBox.hover(function() {
                isPanelHover = true;
            }, function() {
                isPanelHover = false;
            });

            $accountInformationBox.blur(function() {
                if (!isPanelHover) {
                    $accountInformationBox.hide();
                }
            });
        }
    });
}).call(this, jQuery, window);