/**
 * @author jittagorn pitakmetagoon
 * create 23/12/2013
 */
(function($, window) {
    $(function() {

        var leftMenuLink = {
            '.gt-leftMenu-report': window.MRA.config.CONTEXT_PATH + '/private/report',
            '.gt-leftMenu-download': window.MRA.config.CONTEXT_PATH + '/download',
            '.gt-leftMenu-contact' : window.MRA.config.CONTEXT_PATH + '/contact',
            '.gt-leftMenu-news' : window.MRA.config.CONTEXT_PATH + '/news',
            '.gt-leftMenu-faq' : window.MRA.config.CONTEXT_PATH + '/faq',
            '.gt-leftMenu-webboard' : window.MRA.config.CONTEXT_PATH + '/webboard',
            '.gt-leftMenu-about' : window.MRA.config.CONTEXT_PATH + '/about'
        };

        for (var property in leftMenuLink) {
            if (leftMenuLink.hasOwnProperty(property)) {
                $('#gtLeftMenu ' + property).click(function(event) {
                    if (event.preventDefault) {
                        event.preventDefault();
                    } else {
                        return false;
                    }

                    window.location.href = $(this).find('a').attr('href');
                }).find('a').attr('href', leftMenuLink[property]);
            }
        }

        var url = window.location.href;
        var indexOfQuestionMark = url.indexOf('?');
        if (indexOfQuestionMark !== -1) {
            url = url.substring(0, indexOfQuestionMark);
        }

        if (url[url.length - 1] === '/') {
            url = url.substring(0, url.length - 1);
        }

        var $accordion = $('#gtLeftMenu .ui-accordion');
        $accordion.find('.ui-accordion-content').each(function() {
            var $contentItem = $(this);
            $contentItem.find('a').each(function() {
                var $linkItem = $(this);
                var link = window.MRA.config.HOST + $linkItem.attr('href');

                if (link === url) {
                    var $accordionHeader = $contentItem.prev().click();
                    $accordionHeader.find('a').addClass('gt-header-menu-highlight');
                    $linkItem.addClass('gt-menu-highlight');
                }
            });
        });
    });
}).call(this, jQuery, window);