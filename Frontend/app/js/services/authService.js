/**
 * Created by FruityDevil on 09.07.2016.
 */
"use strict";

services
    .factory('authService', [ function () {
        return {

            logout: function() {
                sessionStorage.removeItem('access_token');
            },
            setToken: function (data) {
                sessionStorage.setItem('access_token', data.token_type + ' ' + data.access_token);
            },
            isAuthenticated: function () {
                return sessionStorage.getItem('access_token') !== null
                    ? sessionStorage.getItem('access_token') !== ''
                    : false;
            }
        }
    }]);