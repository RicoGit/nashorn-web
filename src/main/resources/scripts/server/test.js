/**
 * User: Constantine Solovev
 * 18.01.15 13:18
 */


var JsServlet = Java.type('com.solovev.nashorn.server.JsServlet');

(function () {
    'use strict';

    var mustachePath = JsServlet.getPath('libs/mustache');

    load(mustachePath);

    var view = {
        title: "Joe",
        calc: function () {
            return 2 + 4;
        }
    };

    var requestBody = Mustache.render("{{title}} spends {{calc}}", view);

    return requestBody;

})();
