'use strict';

app.controller('mapController', ['$scope', '$element','$timeout', '$compile',
    function ($scope, $element, $timeout, $compile) {

        var self = this;

        this.mapCenter = {lat: 53.87844, lng: 27.46582}; //Belarus

        this.gmap = new google.maps.Map($element[0], {
            center: self.mapCenter,
            zoom: 6,
            streetViewControl: false
        });

        this.infoWindow = new google.maps.InfoWindow();
        this.infoWindow.setOptions({
            maxWidth: 200
        });

        this.loadPostMarkers = function (markers) {
            if (markers.length) {
                markers.forEach(function (mk) {
                    var marker = new google.maps.Marker({
                        map: self.gmap,
                        position: {lat: mk.lat, lng: mk.lng}
                    });
                    marker.addListener('click', function () {
                        self.infoWindow.setOptions({
                            content: mk.address
                        });
                        setMapCenter(marker.position);
                        self.infoWindow.open(self.gmap, marker);
                    });
                });
            }
        };

        this.loadPostArea = function (area) {
            if (Object.keys(area).length) {
                var circle = new google.maps.Circle({
                    map: self.gmap,
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
                        map: self.gmap,
                        position: coords
                    });
                    marker.addListener('click', function () {
                        $scope.post = post;
                        var postTile = '<post-tile flex></post-tile>';
                        var compiled = $compile(postTile)($scope);
                        $scope.$apply();
                        self.infoWindow.setOptions({
                            content: compiled[0]
                        });
                        setMapCenter(marker.position);
                        self.infoWindow.open(self.gmap, marker);
                    });
                });
            }
        };

        function setMapCenter(position) {
            self.infoWindow.close();
            self.gmap.setOptions({
                center: position,
                zoom: 17
            });
        }

        this.setMapControls = function(button, position) {
            $timeout(function () {
                self.gmap.getDiv().appendChild(button);
                self.gmap.controls[position].push(button);
            }, 500);
        };

    }]);