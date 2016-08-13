'use strict';

app.directive('drawingFunction', [function () {

    return {

        restrict: 'A',
        scope: {
            post: '='
        },
        require: '^googleMap',
        link: function ($scope, element, attr, ctrl) {

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
                    map: ctrl.gmap
                },
                markerOptions: {
                    map: ctrl.gmap,
                    animation: google.maps.Animation.DROP,
                    draggable: true
                }
            });
            drawingManager.setMap(ctrl.gmap);

            drawingManager.addListener('markercomplete', function (marker) {
                ctrl.infoWindow.close();
                drawingManager.setDrawingMode(null);

                if (allMarkers.length == MAX_MARKERS) {
                    marker.setMap(null);
                    return;
                }

                allMarkers.push(marker);
                ctrl.getAddress(marker.position.lat(), marker.position.lng()).then(function (address) {
                    $scope.post.locations.push({
                        lat: marker.position.lat(),
                        lng: marker.position.lng(),
                        address: address
                    });
                });

                marker.addListener('click', function (event) {
                    ctrl.getAddress(event.latLng.lat(), event.latLng.lng()).then(function (val) {
                        ctrl.infoWindow.setContent(val);
                    });
                    ctrl.gmap.setOptions({
                        center: event.latLng,
                        zoom: 17
                    });
                    ctrl.infoWindow.open(ctrl.gmap, marker);
                });
                marker.addListener('dragstart', function () {
                    ctrl.infoWindow.close();
                    ctrl.infoWindow.setContent('Определение...');
                });
                marker.addListener('dragend', function () {
                    var index = allMarkers.indexOf(marker);
                    ctrl.getAddress(marker.position.lat(), marker.position.lng()).then(function (address) {
                        $scope.post.locations[index] = {
                            lat: marker.position.lat(),
                            lng: marker.position.lng(),
                            address: address
                        };
                    });
                });
            });

            drawingManager.addListener('circlecomplete', function (newCircle) {
                ctrl.infoWindow.close();
                drawingManager.setDrawingMode(null);

                var temp = circle;
                circle = newCircle;
                ctrl.getAddress(newCircle.center.lat(), newCircle.center.lng()).then(function(address){
                    $scope.post.locationArea = {
                        lat: newCircle.center.lat(),
                        lng: newCircle.center.lng(),
                        radius: newCircle.radius,
                        address: address
                    };
                });

                if (Object.keys(temp).length) temp.setMap(null);

                newCircle.addListener('click', function () {
                    var center = newCircle.getCenter();
                    ctrl.gmap.setOptions({
                        center: center,
                        zoom: 17
                    });
                });
                newCircle.addListener('center_changed', function () {
                    ctrl.infoWindow.close();
                    ctrl.getAddress(newCircle.center.lat(), newCircle.center.lng()).then(function(address){
                        $scope.post.locationArea = {
                            lat: newCircle.center.lat(),
                            lng: newCircle.center.lng(),
                            address: address
                        };
                    });
                });
                newCircle.addListener('radius_changed', function () {
                    $scope.post.locationArea.radius = newCircle.radius;
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
                    $scope.post.locations = [];
                }
                if (Object.keys(circle).length) {
                    circle.setMap(null);
                    circle = {};
                    $scope.post.locationArea = {};
                }
            });
            ctrl.setMapControls(deleteMarkersButton, google.maps.ControlPosition.TOP_RIGHT)
        }
    }
}]);