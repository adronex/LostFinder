/**
 * Created by М on 19.04.2016.
 */

'use strict';

var app = angular.module('lostfinder',
                        ['ngRoute',
                            'ngAnimate',
                            'ngMaterial',
                            'lostfinder.services']);

var services = angular.module('lostfinder.services', []);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '../layout/home.html',
            controller: 'homeController',
            uri: '/home/'
        })
        .when('/dictionaries', {
            templateUrl: '../layout/dictionaryTable.html',
            //controller: 'dictionaryController',
            uri: '/dictionaries/'
        })
        .when('/accounts', {
            templateUrl: '../layout/accounts.html',
            controller: 'accountsController',
            uri: '/accounts/'
        })
        .when('/dictionaries/hashTags', {
            templateUrl: '../layout/dictionaryTable.html',
            controller: 'dictionaryController',
            uri: '/hashTags/'
        })
        .when('/dictionaries/postTypes', {
            templateUrl: '../layout/dictionaryTable.html',
            controller: 'dictionaryController',
            uri: '/postTypes/'
        })
        .when('/posts', {
            templateUrl: '../layout/posts.html',
            controller: 'postsController',
            uri: '/posts/'
        })
        .when('/map', {
            templateUrl: '../layout/map.html',
            controller: 'mapController',
            uri: '/map/'
        })
        .otherwise('/');
}]);