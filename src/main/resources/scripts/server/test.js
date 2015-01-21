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

        name: 'Nashorn',
        calc: function () {
            return 2 + 4;
        },
        list: [
            'point 1',
            'point 2',
            'point 3'
        ],
        listObj: [
            { name: 'Vasja'},
            { name: 'Petja'},
            { name: 'Serega'}
        ]
    };

    var requestBody = Mustache.render(

        '<h1>Hello {{ name }}!<h1>' +
        '<h3>Calculated value: {{ calc }}<h3>' +
        '<ul>' +
        '   {{ #list }}' +
        '   <li> {{ . }} </li>' +
        '   {{ /list }}' +
        '</ul>' +
        '<br>' +
        '<ul> ' +
        '   {{ #listObj }}' +
        '   <li> {{ name }} </li> ' +
        '   {{ /listObj }}' +
        '</ul>', view);

    return requestBody;

})();
