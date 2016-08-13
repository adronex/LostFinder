'use strict';

app.directive('searchBox', [ function(){

    return {

        restrict: "A",
        require: '^googleMap',
        link: function ($scope, element, attr, ctrl) {

            var input = document.createElement('input');
            input.type = 'text';
            input.id = 'searchBox';
            input.placeholder = 'Введите адрес';
            var searchBox = new google.maps.places.SearchBox(input);

            searchBox.addListener('places_changed', function () {
                searchBox.bindTo('bounds', ctrl.gmap);
                var places = searchBox.getPlaces();

                if (places.length == 0) return;
                if (places[0].geometry.viewport) {
                    ctrl.gmap.fitBounds(places[0].geometry.viewport);
                    ctrl.gmap.setZoom(15);
                } else {
                    ctrl.gmap.setCenter(places[0].geometry.location);
                    ctrl.gmap.setZoom(17);
                }
            });
            ctrl.setMapControls(input, google.maps.ControlPosition.TOP_CENTER);
        }
    }
}]);