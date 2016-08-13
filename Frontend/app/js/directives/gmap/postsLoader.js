'use strict';

app.directive('postsLoader', [ '$compile', function($compile){

    return {

        restrict: 'A',
        require: '^googleMap',
        link: function($scope, element, attr, ctrl){

            $scope.$on('postsLoaded', function(event, posts){
                if (posts.length) {
                    posts.forEach(function (post) {
                        var coords = post.locationArea.address ? {lat: post.locationArea.lat, lng: post.locationArea.lng}
                            : {lat: post.locations[0].lat, lng: post.locations[0].lng};
                        var marker = new google.maps.Marker({
                            map: ctrl.gmap,
                            position: coords
                        });
                        marker.addListener('click', function () {
                            $scope.displayedPost = post; // todo: change if possible
                            var postTile = '<post-tile flex></post-tile>';
                            var compiled = $compile(postTile)($scope);
                            $scope.$apply();
                            ctrl.infoWindow.setOptions({
                                content: compiled[0]
                            });
                            ctrl.setMapCenter(marker.position);
                            ctrl.infoWindow.open(ctrl.gmap, marker);
                        });
                    });
                }
            });
        }
    }
}]);