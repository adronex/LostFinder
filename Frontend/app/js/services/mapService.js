
app.factory('mapService', [ '$q','$rootScope', function($q, $rootScope){

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
        clearMap: function(){
            $rootScope.$broadcast('clearMap');
        },
        getAddress: function(latlng){
            var geocoder = new google.maps.Geocoder;
            var deferred = $q.defer();
            geocoder.geocode({'location': latlng}, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results[0].formatted_address.split(',').length <= 2) {
                        return deferred.resolve(results[1].formatted_address);
                    }
                    return deferred.resolve(results[0].formatted_address);
                }
                else if (status === google.maps.GeocoderStatus.OVER_QUERY_LIMIT){
                    console.log('geocoder status: over query limit!!!')
                }
                return deferred.reject();
            });
            return deferred.promise;
        }
    }
}]);