
app.directive('googleMap', [ function(){

    var options = {
        LOCATION: 'location',
        GEOCODER: 'geocoder',
        DRAWING: 'drawing'
    };

    return {
        restrict: 'E',
        controller: 'mapController',
        link: function($scope, element, attr){

            $scope.map = new google.maps.Map(element[0], {
                center: {lat: 53.87844, lng: 27.46582},
                zoom: 6,
                streetViewControl: false
            });

            if(Object.keys(attr)){
                for(var key in attr){
                    switch (key){
                        case options.LOCATION: $scope.setLocationButton();
                            break;
                        case options.GEOCODER: $scope.setGeocoder();
                            break;
                        case options.DRAWING: $scope.setDrawingFunctions();
                            break;
                        default: break;
                    }
                }
            }

            if($scope.post){
                $scope.loadMarkers();
            }
        }
    }
}]);