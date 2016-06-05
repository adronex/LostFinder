/**
 * Created by М on 26.05.2016.
 */

app.controller('mapController', function($scope, $timeout) {

    var map;
    var geocoder;
    var infoWindow;
    var drawingManager;

    var allMarkers = [];
    var circle;

    $scope.error = "";

    $scope.initialize = function () {

        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 53.87844, lng: 27.46582},
            zoom: 6,
            streetViewControl: false
        });

        infoWindow = new google.maps.InfoWindow();
        geocoder = new google.maps.Geocoder;

        drawingManager = new google.maps.drawing.DrawingManager({
            drawingControl: true,
            drawingControlOptions: {
                position: google.maps.ControlPosition.TOP_CENTER,
                drawingModes: [
                    google.maps.drawing.OverlayType.MARKER,
                    google.maps.drawing.OverlayType.CIRCLE
                ]
            },
            circleOptions: {
                strokeWeight: 5,
                clickable: true,
                editable: true,
                zIndex: 1,
                map: map
            },
            markerOptions: {
                map: map,
                animation: google.maps.Animation.DROP,
                draggable: true
            }
        });
        drawingManager.setMap(map);

        google.maps.event.addListener(map, "click", function(event){
            infoWindow.close();
        });

        drawingManager.addListener('markercomplete', function (marker) {
            infoWindow.close();
            marker.addListener('click', function(){
                infoWindow.setOptions({maxWidth: 200, content: getAddress(marker.position)});
                map.setOptions({center: marker.position, zoom: 16});
                infoWindow.open(marker.map, marker);
            });
            marker.addListener('rightclick', function(){
                marker.setMap(null);
                allMarkers = allMarkers.filter(function(marker){
                    return marker.getMap();
                });
            });
            if(allMarkers.length == 3) {
                marker.setMap(null);
                $scope.error = "error";
                $scope.$apply();
                $timeout(function () {
                    $scope.error = "";
                    $scope.$apply();
                }, 2000);
                drawingManager.setDrawingMode(null);
            }
            else allMarkers.push(marker);
        });

        drawingManager.addListener('circlecomplete', function (c) {
            infoWindow.close();
            c.addListener('rightclick', function(){
                c.setMap(null);
            });
            c.addListener('click', function(){
                var center = c.getCenter();
                infoWindow.setOptions({maxWidth: 200, position: center, content: getAddress(center)});
                map.setOptions({center: center, zoom: 16});
                infoWindow.open(c.getMap(), c);
            });
            var temp = circle;
            circle = c;
            if (temp) temp.setMap(null);
            drawingManager.setDrawingMode(null);
        });
    };

    function getAddress(latlng){
        geocoder.geocode({'location': latlng}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[1]) {
                    infoWindow.setContent(results[1].formatted_address);
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

    $scope.deleteAllMarkers = function (){
        if (allMarkers.length){
            allMarkers.forEach(function(mk){
                mk.setMap(null);
            });
            allMarkers = [];
        }
        if (circle){
            circle.setMap(null);
            circle = '';
        }
    };

    $scope.getLocation = function(){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(setPosition, showError);
        } else {
            $scope.error = "Geolocation is not supported by this browser.";
            $timeout(function () { $scope.error = ""; }, 2000);
        }
    };

    function setPosition(position){
        map.setOptions({center: {lat: position.coords.latitude, lng: position.coords.longitude}, zoom: 16});
    }

// not necessary
    function showError(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                $scope.error = "User denied the request for Geolocation.";
                $scope.$apply();
                break;
            case error.POSITION_UNAVAILABLE:
                $scope.error = "Location information is unavailable.";
                $scope.$apply();
                break;
            case error.TIMEOUT:
                $scope.error = "The request to get user location timed out.";
                $scope.$apply();
                break;
            case error.UNKNOWN_ERROR:
                $scope.error = "An unknown error occurred.";
                $scope.$apply();
                break;
        }
        $timeout(function () { $scope.error = ""; }, 2000);
        $scope.$apply();
    }

});