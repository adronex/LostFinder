app.controller('headerController', ['$scope', '$location', function ($scope, $location) {

    $scope.$location = $location;
    $scope.searchText = '';
    $scope.ctrl = {};

}]);

