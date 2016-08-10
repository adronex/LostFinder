'use strict';

app.directive('googleMap', [function () {

    return {
        restrict: 'E',
        controller: 'mapController',
        scope: {
            postMarkers: '=?',
            postArea: '=?',
            globalMapPosts: '=?',
            post: '=?'
        },
        link: function ($scope, element, attr, ctrl) {

            $scope.$watch('postMarkers', function (newVal) {
                if(newVal) ctrl.loadPostMarkers(newVal);
            });

            $scope.$watch('postArea', function(newVal){
                if(newVal) ctrl.loadPostArea(newVal);
            });

            $scope.$watch('globalMapPosts', function(newVal){
                if(newVal) ctrl.loadGlobalMapPosts(newVal);
            });
        }
    }
}]);