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

    return {
        _id: '123456',
        name: 'granted authority',
        roles: array
    }

}







function checkCredentials(user) {

    var user =  {
        _id: '123456',
        name: 'Peter',
        roles: [ 'user' ],
        addRole: function(role) {
            user.roles.push(role)
        }
    };

    return user;

}


 
