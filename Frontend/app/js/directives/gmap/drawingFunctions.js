
app.directive('drawingFunction', [ 'mapService', function(mapService){

    return {

        restrict: 'A',
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
                    map: ctrl.gmap,
                    radius: 10
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
                marker.addListener('dragend', function (event) {
                    mapService.getAddress(event.latLng).then(function (val) {
                        ctrl.infoWindow.setContent(val);
                    });
                    mapService.setLocations(allMarkers);
                });
            });

            drawingManager.addListener('circlecomplete', function (newCircle) {
                ctrl.infoWindow.close();
                drawingManager.setDrawingMode(null);

                var temp = circle;
                circle = newCircle;
                mapService.setArea(newCircle);
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
                    mapService.getAddress(newCircle.center).then(function (val) {
                        ctrl.infoWindow.setContent(val);
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
                mapService.clearMap();
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
            ctrl.setMapControls(deleteMarkersButton, google.maps.ControlPosition.TOP_RIGHT)
        }
    }
}]);