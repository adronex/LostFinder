/**
 * Created by М on 19.04.2016.
 */

'use strict';

app.controller('accountsController',['$scope', '$route', 'dictionaryService', function($scope, $route, dictionaryService) {

    $scope.uri = $route.current.$$route.uri;

    $scope.accounts = [];

    //todo: change, if possible
    var accTemplate = {
        login: '',
        email: '',
        password: '',
        accountDetail: {
            name: '',
            description: ''
        }
    };
    $scope.editAcc = accTemplate;
    $scope.confirmPass = '';

    $scope.editMode = '';
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideForm = true;

    $scope.loadAccounts = function(){
        dictionaryService.getAll('/api/accounts').success(function(data){
            $scope.accounts =  data;
        });
    };

    $scope.newAccount = function() {
        $scope.editMode = 'newAcc';
        $scope.hideForm = false;
        $scope.incomplete = true;
        clearForm();
    };

    $scope.$watch('editAcc.password',function() {$scope.test();});
    $scope.$watch('confirmPass',function() {$scope.test();});
    $scope.$watch('editAcc.accountDetail.name', function() {$scope.test();});

    //todo: simplify
    $scope.test = function() {
        if($scope.editAcc.password !== $scope.confirmPass) {
            $scope.error = true;
        } else {
            $scope.error = false;
        }

        $scope.incomplete = false;

        //todo: change
        if ($scope.editAcc && (!$scope.editAcc.accountDetail.name.length ||
            !$scope.editAcc.password.length || !$scope.confirmPass.length)) {
            $scope.incomplete = true;
        }
    };

    $scope.onSaveChanges = function(){
        dictionaryService.putItem($scope.uri, $scope.editAcc).success(function(data){
            $scope.loadAccounts();
            clearForm();
            $scope.hideForm = true;
        });
    };

    $scope.onActionDelete = function(itemForDelete) {
        dictionaryService.deleteItem($scope.uri, itemForDelete).success(function(data){
            clearForm();
            $scope.hideForm = true;
            $scope.loadAccounts();
        })
    };

    $scope.onActionEdit = function(account){
        $scope.editMode = 'editAcc';
        $scope.hideForm = false;
        $scope.editAcc = angular.copy(account);
        $scope.confirmPass = account.password;
    };

    $scope.onEditCancel = function() {
        clearForm();
        $scope.editMode = '';
        $scope.hideForm = true;
    };

    var clearForm = function(){
        $scope.editAcc = angular.copy(accTemplate);
        $scope.confirmPass = '';
    }

}]);