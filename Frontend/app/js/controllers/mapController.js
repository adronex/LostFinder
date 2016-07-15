
app.controller('mapController', ['$scope', '$timeout','$compile', function ($scope, $timeout, $compile) {

    this.loadPostMarkers = function (markers, area) {
        if(markers.length){
            //var bounds = new google.maps.LatLngBounds();
            markers.forEach(function (location) {
                var marker = new google.maps.Marker({
                    map: $scope.map,
                    position: location.coords
                });
                marker.addListener('click', function () {
                    //setInfoWindowContent(marker.position);
                    $scope.infoWindow.setOptions({
                        maxWidth: 200,
                        content: location.address
                    });
                    setMapCenter(marker.position);
                    $scope.infoWindow.open($scope.map, marker);
                });
                //bounds.extend(marker.position);
            });
            //$scope.map.fitBounds(bounds);
        }
        if(area.address){ //check for not null
            var circle = new google.maps.Circle({
                map: $scope.map,
                radius: area.radius,
                center: area.coords
            });
            circle.addListener('click', function(){
                setMapCenter(area.coords)
            });
        }
        setMapCenter(area.coords);
    };

    this.loadGlobalMapPosts = function(posts){
        posts.forEach(function(post){
            var coords = post.area.address ? post.area.coords : post.location[0].coords;
            var marker = new google.maps.Marker({
               map: $scope.map,
               position: coords
            });
            marker.addListener('click', function(){
                $scope.post = post; //todo: pass post as parameter to post-tile directive
                var postTile = '<post-tile flex ></post-tile>';
                var compiled = $compile(postTile)($scope);
                $scope.$apply();
                $scope.infoWindow.setOptions({
                    maxWidth: 200,
                    content: compiled[0]
                });
                setMapCenter(marker.position);
                $scope.infoWindow.open($scope.map, marker);
            });
        });
    };

    function setMapCenter(position){
        $scope.infoWindow.close();
        $scope.map.setOptions({
            center: position,
            zoom: 17
        });
    }

    this.setSearchBox = function () {
        var input = document.createElement('input');
        input.type = 'text';
        input.id = 'searchBox';
        input.placeholder = 'Введите адрес';
        var searchBox = new google.maps.places.SearchBox(input);

        searchBox.addListener('places_changed', function () {
            searchBox.bindTo('bounds', $scope.map);
            var places = searchBox.getPlaces();

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

    this.setLocationButton = function () {
        var locationButton = document.createElement('div');
        locationButton.id = 'locationButton';
        locationButton.title = 'Определить местоположение';
        locationButton.addEventListener('click', function () {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    $scope.map.setOptions({
                        center: {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        },
                        zoom: 17
                    });
                });
            }
            else {
                $scope.error = "Geolocation is not supported by this browser.";
                $timeout(function () {
                    $scope.error = "";
                }, 2000);
            }
        });
        setMapControls(locationButton, google.maps.ControlPosition.RIGHT_BOTTOM);
    };

    function setMapControls(button, position) {
        $timeout(function () {
            $scope.map.getDiv().appendChild(button);
            $scope.map.controls[position].push(button);
        }, 500);
    }

    this.setDrawingFunctions = function () {

        var drawingManager;
        var allMarkers = [];
        var circle;
        var MAX_MARKERS = 3;

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
                clickable: true,
                editable: true,
                map: $scope.map,
                radius: 10
            },
            markerOptions: {
                map: $scope.map,
                animation: google.maps.Animation.DROP,
                draggable: true
            }
        });
        drawingManager.setMap($scope.map);

        drawingManager.addListener('markercomplete', function (marker) {
            $scope.infoWindow.close();

            marker.addListener('click', function(){
                $scope.infoWindow.setOptions({
                    maxWidth: 200
                });
                setInfoWindowContent(marker.position);
                $scope.map.setOptions({
                    center: marker.position,
                    zoom: 17
                });
                $scope.infoWindow.open($scope.map, marker);
            });
            marker.addListener('rightclick', function(){
                marker.setMap(null);
                allMarkers = allMarkers.filter(function(marker){
                    return marker.getMap();
                });
            });
            marker.addListener('dragstart', function(){
                $scope.infoWindow.close();
            });

            if(allMarkers.length < MAX_MARKERS) allMarkers.push(marker);
            else {
                marker.setMap(null);
                $scope.error = "error";
                $timeout(function () {
                    $scope.error = "";
                }, 2000);
                drawingManager.setDrawingMode(null);
                $scope.$apply();
            }
        });

        drawingManager.addListener('circlecomplete', function (newCircle) {
            $scope.infoWindow.close();
            newCircle.addListener('rightclick', function(){
                newCircle.setMap(null);
            });
            newCircle.addListener('click', function(){
                var center = newCircle.getCenter();
                $scope.infoWindow.setOptions({
                    maxWidth: 200,
                    position: center
                });
                setInfoWindowContent(center);
                $scope.map.setOptions({
                    center: center, zoom: 17
                });
                $scope.infoWindow.open($scope.map, newCircle);
            });
            newCircle.addListener('center_changed', function(){
                $scope.infoWindow.close();
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

    function setInfoWindowContent(latlng){
        var geocoder = new google.maps.Geocoder;
        geocoder.geocode({'location': latlng}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $scope.infoWindow.setContent(results[0].formatted_address);
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

}]);