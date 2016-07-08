
app.controller('mapController', ['$scope', '$timeout', function($scope, $timeout) {

    var geocoder = new google.maps.Geocoder;
    var infoWindow = new google.maps.InfoWindow();
    var drawingManager;

    var allMarkers = [];
    var circle;

    $scope.error = "";

    $scope.loadMarkers = function(){
        $scope.post.latlng.forEach(function(coords){
            var marker = new google.maps.Marker({
                map: $scope.map,
                position: coords
            });
            setOnClickListener(marker);
        });
    };

    $scope.setDrawingFunctions = function () {

        drawingManager = new google.maps.drawing.DrawingManager({
            drawingControl: true,
            drawingControlOptions: {
                position: google.maps.ControlPosition.RIGHT_TOP,
                drawingModes: [
                    google.maps.drawing.OverlayType.MARKER,
                    google.maps.drawing.OverlayType.CIRCLE
                ]
            },
            circleOptions: {
                strokeWeight: 3,
                clickable: true,
                editable: true,
                zIndex: 1,
                map: $scope.map
            },
            markerOptions: {
                map: $scope.map,
                animation: google.maps.Animation.DROP,
                draggable: true
            }
        });
        drawingManager.setMap($scope.map);

        google.maps.event.addListener($scope.map, "click", function(){
            infoWindow.close();
        });

        drawingManager.addListener('markercomplete', function (marker) {
            infoWindow.close();
            setOnClickListener(marker);
            
            marker.addListener('rightclick', function(){
                marker.setMap(null);
                allMarkers = allMarkers.filter(function(marker){
                    return marker.getMap();
                });
            });
            marker.addListener('dragstart', function(){
                infoWindow.close();
            });

            if(allMarkers.length == 3) {
                marker.setMap(null);
                $scope.error = "error";
                $timeout(function () {
                    $scope.error = "";
                }, 2000);
                drawingManager.setDrawingMode(null);
                $scope.$apply();
            }
            else allMarkers.push(marker);
        });

        drawingManager.addListener('circlecomplete', function (newCircle) {
            infoWindow.close();
            newCircle.addListener('rightclick', function(){
                newCircle.setMap(null);
            });
            newCircle.addListener('click', function(){
                var center = newCircle.getCenter();
                infoWindow.setOptions({maxWidth: 200, position: center, content: getAddress(center)}); // todo: remake
                $scope.map.setOptions({center: center, zoom: 17});
                infoWindow.open(newCircle.getMap(), newCircle);
            });
            newCircle.addListener('center_changed', function(){
                infoWindow.close();
            });

            var temp = circle;
            circle = newCircle;
            if (temp) temp.setMap(null);
            drawingManager.setDrawingMode(null);
        });

        var deleteMarkersButton = document.createElement('div');
        deleteMarkersButton.id = 'deleteMarkersButton';
        deleteMarkersButton.innerHTML = 'Очистить карту';
        deleteMarkersButton.addEventListener('click', function(){
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
        });

        setMapControls(deleteMarkersButton, google.maps.ControlPosition.TOP_RIGHT)
    };
    
    function setOnClickListener(element){
        element.addListener('click', function(){
            infoWindow.setOptions({maxWidth: 200, content: getAddress(element.position)}); //todo: remake
            $scope.map.setOptions({center: element.position, zoom: 17});
            infoWindow.open(element.map, element);
        });
    }
    
    $scope.setGeocoder = function(){
        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'placeAutocomplete';
        input.placeholder = 'Введите адрес';
        var autocomplete = new google.maps.places.SearchBox(input);

        autocomplete.addListener('places_changed', function() {
            autocomplete.bindTo('bounds', $scope.map);
            var places = autocomplete.getPlaces();

            if (places.length == 0) return;
            if (places[0].geometry.viewport) {
                $scope.map.fitBounds(places[0].geometry.viewport);
                $scope.map.setZoom(12);
            } else {
                $scope.map.setCenter(places[0].geometry.location);
                $scope.map.setZoom(17);
            }
        });
        setMapControls(input, google.maps.ControlPosition.TOP_CENTER);
    };

    $scope.setLocationButton = function(){
        var locationButton = document.createElement('div');
        locationButton.id = 'locationButton';
        locationButton.innerHTML = 'O';
        locationButton.addEventListener('click', function(){
            getLocation();
        });
        setMapControls(locationButton, google.maps.ControlPosition.TOP_RIGHT);
    };

    function getLocation(){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(setPosition, showError);
        } else {
            $scope.error = "Geolocation is not supported by this browser.";
            $timeout(function () { $scope.error = ""; }, 2000);
        }
    }

    function getAddress(latlng){
        geocoder.geocode({'location': latlng}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    infoWindow.setContent(results[0].formatted_address); // todo: remake
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

    function setPosition(position){
        $scope.map.setOptions({center: {lat: position.coords.latitude, lng: position.coords.longitude}, zoom: 17});
    }

    function setMapControls(button, position){
        $timeout(function(){
            $scope.map.getDiv().appendChild(button);
            $scope.map.controls[position].push(button);
        }, 500);
    }

// not necessary
    function showError(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                $scope.error = "User denied the request for Geolocation.";
                break;
            case error.POSITION_UNAVAILABLE:
                $scope.error = "Location information is unavailable.";
                break;
            case error.TIMEOUT:
                $scope.error = "The request to get user location timed out.";
                break;
            case error.UNKNOWN_ERROR:
                $scope.error = "An unknown error occurred.";
                break;
        }
        $timeout(function () { $scope.error = ""; }, 2000);
        $scope.$apply();
    }

}]);