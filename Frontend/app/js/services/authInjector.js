/**
 * Default class description.
 *
 * @author P.Sinitsky
 * @version 1.0
 * @date 14.04.2016
 */
services
    .factory('authInjector', function() {

        return {
            request: function (config) {
                var requestOnAuthUrl = config.url.indexOf(serverUrl + '/api/login') !== -1;
                if (!requestOnAuthUrl && sessionStorage.getItem('access_token') != undefined) {
                    config.headers['Authorization'] = sessionStorage.getItem('access_token');
                }
                return config;
            },
            responseError: function(rejection) {
                var requestOnAuthUrl = rejection.config.url.indexOf(serverUrl + '/api/login') !== -1;
                if (rejection.status == 401 && !requestOnAuthUrl) {
                    window.location.href = "/";
                }
                return {};
            }
        };
    });