/**
 * @author jittagorn pitakmetagoon
 * 
 * define class TooltipBinding
 * for bind tooltip to element DOM 
 * by .tooltip-[POSITON : {n, w, e, s}] style class 
 */
window.TooltipBinding = window.TooltipBinding || (function($, win, doc) {

    var TIPSY_ATTRIBUTE = 'original-title';
    var TIPSY = '.tipsy';
    var TIPSY_INNER = '.tipsy-inner';

    function removeAllTooltips() {
        $(TIPSY).remove();
    }

    function removeTipsy($element) {
        var title = $element.attr(TIPSY_ATTRIBUTE);
        $(TIPSY_INNER).each(function() {
            var $that = $(this);
            if ($that.text() !== title) {
                $that.parents(TIPSY).remove();
            }
        });
    }

    var timeout = null;
    $(doc).on('mouseover', function(event) {
        if (timeout) {
            clearTimeout(timeout);
        }

        timeout = setTimeout(function() {
            clearTimeout(timeout);

            var $el = $(event.target);
            var parents = $el.parents('[' + TIPSY_ATTRIBUTE + ']');
            if (parents.length) {
                removeTipsy(parents);
                return;
            }

            if ($el.is('[' + TIPSY_ATTRIBUTE + ']')) {
                removeTipsy($el);
                return;
            }

            removeAllTooltips();
        }, 50);
    });

    function bind(selectror, gravity) {
        $(selectror).each(function() {
            var $item = $(this);
            if (!$item.attr(TIPSY_ATTRIBUTE)) {
                $item.tipsy({
                    gravity: gravity
                });
            }
        });
    }

    function bindAndBlur(selectror, gravity) {
        win.addResponseListner(function() {
            var t = setTimeout(function() {
                clearTimeout(t);
                bind(selectror, gravity);
            }, 500);
        });
    }

    function bindTitle(selector, titleLabel) {
        var t = setTimeout(function() {
            clearTimeout(t);
            $(selector).each(function() {
                var $that = $(this);
                var title = $that.attr('title');
                if (!title) {
                    $that.attr('title', titleLabel);
                }
            });
        }, 50);
    }

    return {
        /**
         * {string} selectror
         * {string} gravity
         */
        bind: function(selectror, gravity) {
            bind(selectror, gravity);
            bindAndBlur(selectror, gravity);
        },
        /**
         * {string} selectror
         * {string} titleLabel
         */
        bindTitle: function(selector, titleLabel) {
            bindTitle(selector, titleLabel);
            win.addResponseListner(function(){
                bindTitle(selector, titleLabel);
            });
        }
    };
})(jQuery, window, document);