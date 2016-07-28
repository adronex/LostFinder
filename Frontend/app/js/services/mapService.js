
app.factory('mapService', [ '$q','$rootScope', function($q, $rootScope){

    var geocoder = new google.maps.Geocoder;

    return {

        setLocations: function(markers){
            if(markers.length){
                var location = markers.map(function(item){
                    return {
                        lat: item.position.lat(),
                        lng: item.position.lng(),
                        address: "no result"
                    }
                });
                $rootScope.$broadcast("locationsUpdate", location);
            }
        },
        setArea: function(circle){
            if(Object.keys(circle).length){
                var area = {
                    lat: circle.center.lat(),
                    lng: circle.center.lng(),
                    radius: circle.radius,
                    address: "no results"
                };
                $rootScope.$broadcast('areaUpdate', area);
            }
        },
        getAddress: function(latlng){
            var deferred = $q.defer();
            geocoder.geocode({'location': latlng}, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results[0].formatted_address.split(',').length <= 2) {
                        return deferred.resolve(results[1].formatted_address);
                    }
                    return deferred.resolve(results[0].formatted_address);
                }
                return deferred.reject();
            });
            return deferred.promise;
        }
    }
}]);