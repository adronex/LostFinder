'use strict';

var app = angular.module('lostfinder',
    [
        'ngRoute',
        'ngAnimate',
        'ngMaterial',
        'ngMessages',
        'lostfinder.services',
        'satellizer',
        'blockUI'
    ]);

var services = angular.module('lostfinder.services', []);

app.config(['$routeProvider', '$httpProvider', '$authProvider',
        function ($routeProvider, $httpProvider, $authProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: '../layout/allPosts.html',
                    controller: 'allPostController',
                    uri: '/home/'
                })
                .when('/map', {
                    templateUrl: '../layout/globalMap.html',
                    controller: 'globalMapController',
                    uri: '/map/'
                })
                .when('/posts/:id', {
                    templateUrl: '../layout/postView.html',
                    controller: 'postViewController',
                    uri: '/posts/'
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

    .run(['$rootScope', '$location', 'authService', 'loginDialog',
        function ($rootScope, $location, authService, loginDialog) {

        $rootScope.$on("$routeChangeStart", function (event, next) {
            if (next.requires && next.requires.login) {
                if (!authService.isAuthenticated()) {
                    $location.path("/");
                    loginDialog.show();
                }
            }
        });
    }]);