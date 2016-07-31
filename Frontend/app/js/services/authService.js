/**
 * Created by FruityDevil on 09.07.2016.
 */
"use strict";

services
    .factory('authService', ['$http', function authService($http) {
        return {
            login: function(email, password) {
                return $http({
                    method: 'post',
                    url: serverUrl + '/api/login',
                    headers: {
                        "Authorization": "Basic bG9zdEZpbmRlcjpQb3NodWtheSptbmUqdHV0KjA1MDY=",
                        "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: 'grant_type=password&username=' + email + '&password=' + password
                })
            },
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