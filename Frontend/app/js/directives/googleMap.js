'use strict';

app.directive('googleMap', [function () { //todo: remake

    var options = {
        LOCATION: 'location',
        SEARCHBOX: 'searchbox',
        DRAWING: 'drawing'
    };

    var mapCenter = {lat: 53.87844, lng: 27.46582}; //Belarus

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

            $scope.infoWindow = new google.maps.InfoWindow();

            $scope.map = new google.maps.Map(element[0], {
                center: mapCenter,
                zoom: 6,
                streetViewControl: false
            });
            $scope.map.addListener("click", function () {
                $scope.infoWindow.close();
            });
            $scope.infoWindow.setOptions({
                maxWidth: 200
            });

            for (var key in attr) { //todo: remake
                switch (key) {
                    case options.LOCATION:
                        ctrl.setLocationButton();
                        break;
                    case options.SEARCHBOX:
                        ctrl.setSearchBox();
                        break;
                    case options.DRAWING:
                        ctrl.setDrawingFunctions();
                        break;
                    default:
                        break;
                }
            }

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