'use strict';

app.controller('allPostController',['$scope', 'dictionaryService', function($scope, dictionaryService) {

    $scope.imageLink = 'http://fakeimg.pl/200x450/00AF71,128/000,255/?text=Some&font=lobster';
    $scope.types = { all:{ name: "все", color: ""}, lost:{ name: "ищу", color: "#EF5350"}, find:{name: "нашел", color: "#5C6BC0"}};

    dictionaryService.getAll(uri.posts).then(function(data){
        if(data.length) $scope.posts = data;
        else $scope.nothingToShow = true;
    });
}]);