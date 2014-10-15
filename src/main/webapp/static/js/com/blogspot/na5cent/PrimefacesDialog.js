/**
 * @author jittagorn pitakmetagoon
 * create 19/04/2014
 * source http://na5cent.blogspot.com/2014/04/primefaces-dialog-listener-javascript.html
 */
window.PrimefacesDialog = window.PrimefacesDialog || (function(global, $) {

    var listener = [];

    function isString(data) {
        return typeof data === 'string';
    }

    function isFunction(data) {
        return typeof data === 'function';
    }

    function forEachProperty(object, callback, ctx) {
        for (var prop in object) {
            if (object.hasOwnProperty(prop)) {
                callback.call(ctx, object[prop], prop, object);
            }
        }
    }

    function forEachIndex(array, callback, ctx) {
        var length = array.length;
        for (var i = 0; i < length; i++) {
            callback.call(ctx, array[i], i, array, length);
        }
    }

    function forEachDialog(callback, ctx) {
        forEachProperty(global, function(value, prop, object) {
            if (isDialog(value)) {
                callback.call(ctx, value, prop, object);
            }
        });
    }

    function isDialog(object) {
        try {
            return object && object.jq
                    && object.jq.attr
                    && object.jq.attr('class')
                    && object.jq.attr('class').indexOf('ui-dialog') !== -1;
        } catch (e) {
            return false;
        }
    }

    function bind(type, name) {

        return function() {
            var that = this;

            forEachIndex(listener, function(list) {
                if (list.type !== type) {
                    return;
                }

                if (list.dialog) {
                    list.dialog === name && list.callback.call(that, name);
                } else {
                    list.callback.call(that, name);
                }
            });
        };
    }

    function initialize() {
        forEachDialog(function(dialog, name) {
            dialog.cfg.onShow = bind('show', name);
            dialog.cfg.onHide = bind('hide', name);
        });
    }

    $(function() {
        setTimeout(initialize, 100);
    });

    return {
        /**/
        initialize: initialize,
        /**
         * for listen dialog event
         * 
         * @param {String} type - (show, hide)
         * @param {String} dialogWidgetVar_opt - optional
         * @param {Function} callback
         */
        on: function(type, dialogWidgetVar_opt, callback) {

            if (isFunction(dialogWidgetVar_opt)) {
                callback = dialogWidgetVar_opt;
                dialogWidgetVar_opt = undefined;
            }

            if (!isString(type)) {
                throw new Error('require type is string.');
            }

            if (dialogWidgetVar_opt && !isString(dialogWidgetVar_opt)) {
                throw new Error('require dialogWidgetVar_opt is string.');
            }

            if (!isFunction(callback)) {
                throw new Error('require callback is function.');
            }

            listener.push({
                type: type,
                dialog: dialogWidgetVar_opt,
                callback: callback
            });

            return PrimefacesDialog;
        }
    };
})(PrimeFaces.widgets, jQuery);