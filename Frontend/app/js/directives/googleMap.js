
app.directive('googleMap', [ function(){

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
            postMarkers: '=',
            postArea: '=',
            globalMapPosts: '='
        },
        link: function($scope, element, attr, ctrl){

            $scope.error = "";
            $scope.infoWindow = new google.maps.InfoWindow();

            $scope.map = new google.maps.Map(element[0], {
                center: mapCenter,
                zoom: 6,
                streetViewControl: false
            });
            $scope.map.addListener("click", function () {
                $scope.infoWindow.close();
            });

            $scope.$watch('attr', function () {
                for (var key in attr) {
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
            });

            if($scope.postMarkers || $scope.postArea) ctrl.loadPostMarkers($scope.postMarkers, $scope.postArea);
            if($scope.globalMapPosts) ctrl.loadGlobalMapPosts($scope.globalMapPosts);
        }
    }
}]);