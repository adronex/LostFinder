'use strict';

app.controller('allPostController',['$scope', 'dictionaryService', function($scope, dictionaryService) {

    $scope.imageLink = 'http://fakeimg.pl/200x450/00AF71,128/000,255/?text=Some&font=lobster';

    dictionaryService.getAll(uri.posts, {}).then(function(data){
        if(data.content.length) $scope.posts = data.content;
        else $scope.nothingToShow = true;
    });
}]);