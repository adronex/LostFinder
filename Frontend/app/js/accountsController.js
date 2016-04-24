/**
 * Created by М on 19.04.2016.
 */

app.controller('accountsController',['$scope', '$http', function($scope, $http) {
    $http.get("http://localhost:8080/accounts")
        .then(function (response) {$scope.accounts = response.data;});
}]);