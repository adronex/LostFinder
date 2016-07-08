
'use strict';

var app = angular.module('lostfinder',
                        ['ngRoute',
                            'ngAnimate',
                            'ngMaterial',
                            'lostfinder.services'
                        ]);

var services = angular.module('lostfinder.services', []);

app.config(['$routeProvider', function ($routeProvider) {

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
}]);