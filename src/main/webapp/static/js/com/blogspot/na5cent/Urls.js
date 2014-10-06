/**
 * @author jittagorn pitakmetagoon
 * create 28/04/2014
 */
window.Urls = window.Urls || {
    /**/
    urlRegExPattern: /(\b(((https?|ftp|file):\/\/)|(www\.))+[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/ig,
    /**/
    detectLink: function(text) {
        var replace = text.replace(this.urlRegExPattern, function(currentUrl) {
                    var urlLink = currentUrl;
                    var urlText = currentUrl;
                    if (!urlLink.match(/\b(https?)/)) {
                        urlLink = "http://" + urlLink;
                    }
                    return "<a href='" + urlLink + "' target='_blank'>" + urlText + "</a>";
                });
        return replace;
    },
    /**/
    getParameterByName : function(name){
        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
        var regexS = "[\\?&]" + name + "=([^&#]*)";
        var regex = new RegExp(regexS);
        var results = regex.exec(window.location.search);
        //var results = regex.exec(window.location.hash);
        if(results == null)
            return "";
        else
            return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
};