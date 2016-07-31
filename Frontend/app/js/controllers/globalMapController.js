'use strict';

app.controller('globalMapController', [ '$scope', 'dictionaryService', function($scope, dictionaryService){

    dictionaryService.getAll(uri.posts).then(function(data){
        $scope.posts = data;
    });
}]);