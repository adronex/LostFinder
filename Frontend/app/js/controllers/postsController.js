/**
 * Created by М on 19.04.2016.
 */

'use strict';

app.controller('postsController',['$scope', '$route', 'dictionaryService', function($scope, $route, dictionaryService) {

    $scope.uri = $route.current.$$route.uri;

    $scope.posts = [];

    //todo: change, if possible
    var postTemplate = {
        title: '',
        description: '',
        hashTags: []
    };
    $scope.editPost = postTemplate;
    $scope.inputHashTag = '';

    $scope.editMode = '';
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideForm = true;

    $scope.loadPosts = function(){
        dictionaryService.getAll('/posts').success(function(data){
            $scope.posts =  data;
        });
    };

    $scope.newPost = function() {
        $scope.editMode = 'newPost';
        $scope.hideForm = false;
        $scope.incomplete = true;
        clearForm();
    };

    $scope.$watch('editPost.title',function() {$scope.test();});
    $scope.$watch('editPost.description', function() {$scope.test();});
    $scope.$watch('editPost.accountId', function() {$scope.test();});

    $scope.test = function() {
        if (!$scope.editPost.title.length || !$scope.editPost.description.length) {
            $scope.incomplete = true;
        } else $scope.incomplete = false;
    };

    $scope.onSaveChanges = function(){
        dictionaryService.putItem($scope.uri, $scope.editPost).success(function(data){
            $scope.loadPosts();
            clearForm();
            $scope.hideForm = true;
        });
    };

    $scope.onActionDelete = function(itemForDelete) {
        dictionaryService.deleteItem($scope.uri, itemForDelete).success(function(data){
            clearForm();
            $scope.hideForm = true;
            $scope.loadPosts();
        })
    };

    $scope.onActionEdit = function(post){
        $scope.editMode = 'editPost';
        $scope.hideForm = false;
        $scope.editPost = angular.copy(post);
    };

    $scope.onEditCancel = function() {
        clearForm();
        $scope.editMode = '';
        $scope.hideForm = true;
    };

    var clearForm = function(){
        $scope.editPost = angular.copy(postTemplate);
    };

    $scope.hashTagsCreate = function(keyCode){
        // 8 - backspace
        if (keyCode == 8){
            $scope.editPost.hashTags.pop();
        }
        if(testSeparator($scope.inputHashTag[0])){
            $scope.inputHashTag = null;
            return;
        }
        if(testSeparator(keyCode)){
            if(keyCode != 32) $scope.inputHashTag = $scope.inputHashTag.slice(0, -1);
            $scope.editPost.hashTags.push({name: $scope.inputHashTag });
            $scope.inputHashTag = null;
        }
    };

    var testSeparator = function(c){
        // 32 - ' ' 188 - ',' 190 - '.'
        var codes = [32, 188, 190, ',', '.', '/', undefined]; //todo: change!
        var contain = false;
        angular.forEach(codes, function(code) {
            if(c == code) contain = true;
        });
        return contain;
    };

    $scope.deleteHashTag = function(index){
        $scope.editPost.hashTags.splice(index, 1);
        var df = 0;
    };

}]);