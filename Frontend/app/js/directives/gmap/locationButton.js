'use strict';

app.directive('locationButton', [function () {

    return {
        restrict: 'A',
        require: '^googleMap',
        link: function ($scope, element, attr, ctrl) {

            var locationButton = document.createElement('div');
            locationButton.id = 'location-button';
            locationButton.title = 'Определить местоположение';
            locationButton.addEventListener('click', function () {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        ctrl.gmap.setOptions({
                            center: {
                                lat: position.coords.latitude,
                                lng: position.coords.longitude
                            },
                            zoom: 17
                        });
                    });
                }
                else {
                    alert("Geolocation is not supported by this browser.");
                }
            });
            ctrl.setMapControls(locationButton, google.maps.ControlPosition.RIGHT_BOTTOM);
        }
    }
}]);