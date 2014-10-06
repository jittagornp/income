/**
 * for check browser query string match with key value or key/value pairs parameter
 * 
 * @author redcrow (jittagorn pitakmetagoon)
 * create 29/11/2013
 * link http://na5cent.blogspot.com/2013/12/query-string-matcher-javascript.html
 */
window.QueryStringMatcher = window.QueryStringMatcher || (function(Object, window) {
 
    function is(type, object) {
        return Object.prototype.toString.call(object) === '[object ' + type + ']';
    }
 
    function isFunction(object) {
        return is('Function', object);
    }
 
    function isArray(object) {
        return is('Array', object);
    }
 
    function isObject(object) {
        return is('Object', object);
    }
  
    function replace(string){
        return (string + '').replace(/\+/g, ' ');
    }
 
    function encode(string) {
        return encodeURIComponent(replace(string));
    }
 
    function decode(string) {
        return decodeURIComponent(replace(string));
    }
 
    function getQueryStringMap() {
        var queryStringMap = {};
        var queryStringRegExPattern = /([^&=]+)=?([^&]*)/g;
        var queryString = window.location.search.substring(1); //not include '?' notation
 
        var matchArray;
        while (matchArray = queryStringRegExPattern.exec(queryString)) {
            var key = decode(matchArray[1]);
            var value = decode(matchArray[2]);
 
            queryStringMap[key] = value;
        }
 
        return queryStringMap;
    }
 
    //hasElement implementations
    function hasOwnKey(object, value) {
        return object.hasOwnProperty(encode(value));
    }
 
    function hasOwnValue(object, value) {
        for (var property in object) {
            if (object[property] == encode(value)) {
                return true;
            }
        }
 
        return false;
    }
 
    function hasOwnKeyValue(object, value, key) {
        for (var property in object) {
            if (object[property] == encode(value) && property == encode(key)) {
                return true;
            }
        }
 
        return false;
    }
    //
 
    function matchBy(queryStrings, callback, hasElement) {
        var queryStringMap = getQueryStringMap();
        var matchAll = true;
 
        for (var index in queryStrings) {
            if (!hasElement(queryStringMap, queryStrings[index], index)) {
                matchAll = false;
                break;
            }
        }
 
        if (matchAll && isFunction(callback)) {
            callback();
        }
    }
 
    return {
        /**
         * @param {array} queryStringKeysArray
         * @param {function} callback
         */
        matchByKeys: function(queryStringKeysArray, callback) {
            if (!isArray(queryStringKeysArray)) {
                throw new Error('first parameter of QueryStringMatcher.matchByKeys({array} queryStringKeysArray, {function} callback) require \'array\' type.');
            }
 
            matchBy(queryStringKeysArray, callback, hasOwnKey);
        },
        /**
         * @param {array} queryStringValuesArray
         * @param {function} callback
         */
        matchByValues: function(queryStringValuesArray, callback) {
            if (!isArray(queryStringValuesArray)) {
                throw new Error('first parameter of QueryStringMatcher.matchByValues({array} queryStringValuesArray, {function} callback) require \'array\' type.');
            }
 
            matchBy(queryStringValuesArray, callback, hasOwnValue);
        },
        /**
         * @param {object} queryStringMap
         * @param {function} callback
         */
        matchByKeyValuePairs: function(queryStringMap, callback) {
            if (!isObject(queryStringMap)) {
                throw new Error('first parameter of QueryStringMatcher.matchByKeyValuePairs({object} queryStringMap, {function} callback) require \'object\' type.');
            }
 
            matchBy(queryStringMap, callback, hasOwnKeyValue);
        }
    };
}).call(this, Object, window);