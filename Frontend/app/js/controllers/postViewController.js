'use strict';

app.controller('postViewController', ['$scope', '$routeParams', 'dictionaryService', function ($scope, $routeParams, dictionaryService) {

    $scope.imageLink = 'http://fakeimg.pl/500x350/?text=Some&font=lobster"';

    dictionaryService.getAll(uri.posts + '/' + $routeParams.id).then(function(data){
        $scope.post = data;
        $scope.post.date = new Date($scope.post.date);
    });

}]);