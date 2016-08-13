'use strict';

app.directive('markersLoader', [ function(){

    return {

        restrict: 'A',
        require: '^googleMap',
        link: function($scope, element, attr, ctrl){

            $scope.$on('postLoaded', function(event, post){

                if (post.locations.length) {
                    post.locations.forEach(function (mk) {
                        var marker = new google.maps.Marker({
                            map: ctrl.gmap,
                            position: {lat: mk.lat, lng: mk.lng}
                        });
                        marker.addListener('click', function () {
                            ctrl.infoWindow.setOptions({
                                content: mk.address
                            });
                            ctrl.setMapCenter(marker.position);
                            ctrl.infoWindow.open(ctrl.gmap, marker);
                        });
                    });
                }

                if (Object.keys(post.locationArea).length) {
                    var circle = new google.maps.Circle({
                        map: ctrl.gmap,
                        radius: post.locationArea.radius,
                        center: {lat: post.locationArea.lat, lng: post.locationArea.lng}
                    });
                    circle.addListener('click', function () {
                        ctrl.setMapCenter(circle.getCenter());
                    });
                }

                ctrl.setMapCenter(circle.getCenter());
            });
        }
    }
}]);