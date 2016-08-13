'use strict';

app.controller('postViewController', ['$scope', '$routeParams', 'dictionaryService', function ($scope, $routeParams, dictionaryService) {

    $scope.imageLink = 'http://fakeimg.pl/500x350/?text=Some&font=lobster"';

    dictionaryService.getById(uri.posts, $routeParams.id).then(function(data){
        $scope.post = data;
        $scope.post.date = new Date($scope.post.date);
        $scope.post.postType = $scope.$parent.postTypes[$scope.post.postType];
        $scope.$broadcast('postLoaded', data);
    });

}]);