'use strict';

var app = angular.module('lostfinder',
    ['ngRoute',
        'ngAnimate',
        'ngMaterial',
        'ngMessages',
        'lostfinder.services',
        'satellizer'
    ]);

var services = angular.module('lostfinder.services', []);

app.config(['$routeProvider', '$httpProvider', '$authProvider',
        function ($routeProvider, $httpProvider, $authProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: '../layout/home.html',
                    controller: 'homeController',
                    uri: '/home/'
                })
                .when('/map', {
                    templateUrl: '../layout/globalMap.html',
                    controller: 'globalMapController',
                    uri: '/map/'
                })
                .when('/post', {
                    templateUrl: '../layout/postView.html',
                    controller: 'postViewController',
                    uri: '/post/'
                })
                .when('/createPost', {
                    templateUrl: '../layout/createPostPage.html',
                    controller: 'createPostController',
                    uri: '/createPost/',
                    requires: { login: true }
                })
                .otherwise('/');

            $authProvider.facebook({
                clientId: '1133747650021651',
                url: 'api/oauth/facebook',
                authorizationEndpoint: 'https://www.facebook.com/v2.6/dialog/oauth'
            });

            $authProvider.google({
                clientId: '159014909196-hntjb149b661tds2q6aiasuc6b6htshm.apps.googleusercontent.com',
                url: 'api/oauth/google'
            });

            $httpProvider.interceptors.push('authInjector');
        }])

    .run(['$rootScope', '$location', 'authService', function ($rootScope, $location, authService) {

        $rootScope.$on("$routeChangeStart", function (event, next) {
            if (next.requires && next.requires.login) {
                if (!authService.isAuthenticated()) {
                    $location.path('/');
                }
                //if (next.requires.role && !authService.hasRole(next.requires.role)) {
                //    $location.path('/404');
                //}
            }
        });
    }]);