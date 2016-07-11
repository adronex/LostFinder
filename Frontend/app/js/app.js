
'use strict';

var app = angular.module('lostfinder',
    ['ngRoute',
        'ngAnimate',
        'ngMaterial',
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
            .when('/api/dictionaries', {
                templateUrl: '../layout/dictionaryTable.html',
                uri: 'api/dictionaries/'
            })
            .when('/api/accounts', {
                templateUrl: '../layout/accounts.html',
                controller: 'accountsController',
                uri: '/api/accounts/'
            })
            .when('/api/dictionaries/hashTags', {
                templateUrl: '../layout/dictionaryTable.html',
                controller: 'dictionaryController',
                uri: '/api/dictionaries/hashTags/'
            })
            .when('/api/dictionaries/postTypes', {
                templateUrl: '../layout/dictionaryTable.html',
                controller: 'dictionaryController',
                uri: '/api/dictionaries/postTypes/'
            })
            .when('/api/posts', {
                templateUrl: '../layout/posts.html',
                controller: 'postsController',
                uri: '/api/posts/'
            })
            .when('/map', {
                templateUrl: '../layout/map.html',
                controller: 'globalMapController',
                uri: '/map/'
            })
            .when('/post', {
                templateUrl: '../layout/postView.html',
                controller: 'postViewController',
                uri: '/post/'
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
    }]);