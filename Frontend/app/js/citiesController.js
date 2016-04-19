/**
 * Created by М on 19.04.2016.
 */

app.controller('citiesController', ['$scope', '$http',  function($scope, $http) {

    $scope.cities = [];
    $scope.city = {id: null, name: ''};

    $http.get("http://localhost:8080/cities")
        .then(function (response) {$scope.cities = response.data;});

    $scope.sort = function(x){
        $scope.order = x;
    }

    $scope.submit = function(itemForPut) {
        var httpRequest = $http.put("http://localhost:8080/cities/", itemForPut).success(function(data, status) {
        });
        $scope.cancelModes();
    };

    $scope.cancelModes = function() {
        $scope.city = {id: null, name: ''};
        $scope.getAll();
    };

    $scope.getAll = function() {
        $http.get("http://localhost:8080/cities/").success(function(data, status) {
            $scope.cities = data;
        });
    };

    $scope.deleteItem = function(itemForEdit) {
        $http.delete("http://localhost:8080/cities/" + itemForEdit.id, itemForEdit).success(function(data, status) {
            $scope.getAll();
        });
    };
}]);