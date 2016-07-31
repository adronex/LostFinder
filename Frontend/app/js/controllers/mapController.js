'use strict';

app.controller('mapController', ['$scope', '$timeout', '$compile', 'mapService',
    function ($scope, $timeout, $compile, mapService) {

        this.loadPostMarkers = function (markers) {
            if (markers.length) {
                markers.forEach(function (mk) {
                    var marker = new google.maps.Marker({
                        map: $scope.map,
                        position: {lat: mk.lat, lng: mk.lng}
                    });
                    marker.addListener('click', function () {
                        $scope.infoWindow.setOptions({
                            content: mk.address
                        });
                        setMapCenter(marker.position);
                        $scope.infoWindow.open($scope.map, marker);
                    });
                });
            }
        };

        this.loadPostArea = function (area) {
            if (Object.keys(area).length) {
                var circle = new google.maps.Circle({
                    map: $scope.map,
                    radius: area.radius,
                    center: {lat: area.lat, lng: area.lng}
                });
                circle.addListener('click', function () {
                    setMapCenter(circle.getCenter())
                });
            }
            setMapCenter(circle.getCenter());
        };

        this.loadGlobalMapPosts = function (posts) {
            if (posts.length) {
                posts.forEach(function (post) {
                    var coords = post.locationArea.address ? {lat: post.locationArea.lat, lng: post.locationArea.lng}
                                                           : {lat: post.locations[0].lat, lng: post.locations[0].lng};
                    var marker = new google.maps.Marker({
                        map: $scope.map,
                        position: coords
                    });
                    marker.addListener('click', function () {
                        $scope.post = post;
                        var postTile = '<post-tile flex></post-tile>';
                        var compiled = $compile(postTile)($scope);
                        $scope.$apply();
                        $scope.infoWindow.setOptions({
                            content: compiled[0]
                        });
                        setMapCenter(marker.position);
                        $scope.infoWindow.open($scope.map, marker);
                    });
                });
            }
        };

        function setMapCenter(position) {
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
                    $scope.map.setZoom(15);
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
                    alert("Geolocation is not supported by this browser.");
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

            var allMarkers = [];
            var circle = {};
            var MAX_MARKERS = 3;

            var drawingManager = new google.maps.drawing.DrawingManager({
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
                drawingManager.setDrawingMode(null);

                if (allMarkers.length < MAX_MARKERS) {
                    allMarkers.push(marker);
                    mapService.setLocations(allMarkers);
                }
                else {
                    marker.setMap(null);
                    drawingManager.setDrawingMode(null);
                    return;
                }

                marker.addListener('click', function (event) {
                    mapService.getAddress(event.latLng).then(function (val) {
                        $scope.infoWindow.setContent(val);
                    });
                    $scope.map.setOptions({
                        center: event.latLng,
                        zoom: 17
                    });
                    $scope.infoWindow.open($scope.map, marker);
                });
                marker.addListener('dragstart', function () {
                    $scope.infoWindow.close();
                    $scope.infoWindow.setContent('Определение...');
                });
                marker.addListener('dragend', function (event) {
                    mapService.getAddress(event.latLng).then(function (val) {
                        $scope.infoWindow.setContent(val);
                    });
                    mapService.setLocations(allMarkers);
                });
            });

            drawingManager.addListener('circlecomplete', function (newCircle) {
                $scope.infoWindow.close();
                drawingManager.setDrawingMode(null);

                var temp = circle;
                circle = newCircle;
                mapService.setArea(newCircle);
                if (Object.keys(temp).length) temp.setMap(null);

                newCircle.addListener('click', function () {
                    var center = newCircle.getCenter();

                    $scope.map.setOptions({
                        center: center,
                        zoom: 17
                    });
                });
                newCircle.addListener('center_changed', function () {
                    $scope.infoWindow.close();
                    mapService.getAddress(newCircle.center).then(function (val) {
                        $scope.infoWindow.setContent(val);
                    });
                    mapService.setArea(newCircle);
                });
                newCircle.addListener('radius_changed', function () {
                    mapService.setArea(newCircle);
                });
            });

            var deleteMarkersButton = document.createElement('div');
            deleteMarkersButton.id = 'deleteMarkersButton';
            deleteMarkersButton.innerHTML = 'Очистить карту';
            deleteMarkersButton.addEventListener('click', function () {
                if (allMarkers.length) {
                    allMarkers.forEach(function (mk) {
                        mk.setMap(null);
                    });
                    allMarkers = [];
                    mapService.setLocations([]);
                }
                if (Object.keys(circle).length) {
                    circle.setMap(null);
                    circle = '';
                    mapService.setArea({});
                }
            });
            setMapControls(deleteMarkersButton, google.maps.ControlPosition.TOP_RIGHT)
        };

    }]);