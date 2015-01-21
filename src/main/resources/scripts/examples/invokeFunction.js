/**
 * User: Constantine Solovev
 * 19.01.15 9:41
 */

'use strict';


function a() {
    return 'function a'
}


function b(text) {
    return 'function b params = ' + text;
}


function c(array) {

    var someVar = 'someVar';

    return {
        _id: '123456',
        name: 'granted authority',
        roles: array,
        someFunc: function(param) {
            return someVar + " " + param;
        }

    }

}


