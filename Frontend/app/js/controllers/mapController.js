'use strict';

app.controller('mapController', ['$scope', '$element','$timeout', '$q',
    function ($scope, $element, $timeout, $q) {

        var self = this;

        this.mapCenter = {lat: 53.87844, lng: 27.46582}; //Belarus

        this.gmap = new google.maps.Map($element[0], {
            center: self.mapCenter,
            zoom: 6,
            streetViewControl: false
        });

        this.infoWindow = new google.maps.InfoWindow();
        this.infoWindow.setOptions({
            maxWidth: 200
        });


        this.setMapCenter = function(position) {
            self.infoWindow.close();
            self.gmap.setOptions({
                center: position,
                zoom: 17
            });
        };

        this.setMapControls = function(button, position) {
            $timeout(function () {
                self.gmap.getDiv().appendChild(button);
                self.gmap.controls[position].push(button);
            }, 500);
        };

        this.getAddress = function(latitude, longitude){
            var geocoder = new google.maps.Geocoder();
            var deferred = $q.defer();
            geocoder.geocode({'location': {lat: latitude, lng: longitude}}, function(results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results[0].formatted_address.split(',').length <= 2) {
                        return deferred.resolve(results[1].formatted_address);
                    }
                    return deferred.resolve(results[0].formatted_address);
                }
                else if (status === google.maps.GeocoderStatus.OVER_QUERY_LIMIT){
                    console.log('geocoder status: over query limit!!!');
                }
                return deferred.reject();
            });
            return deferred.promise;
        };

    }]);