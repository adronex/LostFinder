'use strict';

app.controller('globalMapController', [ '$scope', 'dictionaryService', function($scope, dictionaryService){

    $scope.displayedPost = {};

    dictionaryService.getAll(uri.posts, {}).then(function(data){
        $scope.posts = data.content;
        $scope.$broadcast('postsLoaded', data.content);
    });
}]);