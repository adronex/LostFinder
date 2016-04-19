/**
 * Created by М on 19.04.2016.
 */

'use strict';
var app = angular.module('lostfinder', ['ngRoute', 'lostfinder.services', 'lostfinder.directives']);

var services = angular.module('ngRoute.services', []);
var directives = angular.module('ngRoute.directives', []);

app.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider
        .when('/accounts', {
            templateUrl: '../layout/accounts.html',
            controller: 'accountsController'
        })
        .when('/cities', {
            templateUrl: '../layout/cities.html',
            controller: 'citiesController'
        })
        .otherwise({
            redirectTo: '/phones'

        });

}]);