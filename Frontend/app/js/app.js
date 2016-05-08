/**
 * Created by М on 19.04.2016.
 */

'use strict';

var app = angular.module('lostfinder', ['ngRoute', 'ngAnimate', 'lostfinder.services']);

var services = angular.module('lostfinder.services', []);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '../layout/home.html',
            //controller: 'accountsController',
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
        .when('/postTypes', {
            templateUrl: '../layout/dictionaryTable.html',
            controller: 'dictionaryController',
            uri: '/postTypes/'
        })
        .when('/hashTags', {
            templateUrl: '../layout/dictionaryTable.html',
            controller: 'dictionaryController',
            uri: '/hashTags/'
        })
        .when('/contactTypes', {
            templateUrl: '../layout/dictionaryTable.html',
            controller: 'dictionaryController',
            uri: '/contactTypes/'
        })
        .otherwise('/');
}]);