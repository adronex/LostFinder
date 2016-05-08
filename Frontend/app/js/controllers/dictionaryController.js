/**
 * Created by М on 19.04.2016.
 */

'use strict';

app.controller('dictionaryController', ['$scope', '$route', 'dictionaryService', function ($scope, $route, dictionaryService) {

    $scope.items = [];
    $scope.item = {};
    $scope.editItem = {};
    $scope.editMode = false;

    $scope.uri = $route.current.$$route.uri;

    $scope.loadItems = function () {
        dictionaryService.getAll($scope.uri).success(function (data) {
            $scope.items = data;
        });
        $scope.item = {};
        $scope.editItem = {};
    };

    $scope.sort = function (x) {
        $scope.order = x;
    };

    $scope.addSubmit = function (itemForPut) {
        if (itemForPut == undefined || Object.getOwnPropertyNames(itemForPut).length === 0 || itemForPut == null) return;
        dictionaryService.putItem($scope.uri, itemForPut).success(function (data) {
            $scope.loadItems();
        });
    };

    $scope.onActionDelete = function (itemForDelete) {
        dictionaryService.deleteItem($scope.uri, itemForDelete).success(function (data) {
            $scope.loadItems();
        });
        $scope.editMode = false;
    };

    $scope.showControlButtons = function (x) {
        if ($scope.items[x].ctrlButtons == undefined) {
            $scope.items[x].ctrlButtons = false;
        }
        $scope.items[x].ctrlButtons = !$scope.items[x].ctrlButtons;
    };

    $scope.onActionEdit = function (item) {
        $scope.editMode = true;
        $scope.editItem = angular.copy(item);
    };

    $scope.onEditSubmit = function () {
        $scope.addSubmit($scope.editItem);
        $scope.editMode = false;
    };

    $scope.onEditCancel = function () {
        $scope.editItem = {};
        $scope.editMode = false;
    }

}]);