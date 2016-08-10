'use strict';

app.controller('createPostController', ['$scope', 'dictionaryService', 'mapService', '$location',
    function ($scope, dictionaryService, mapService, $location) {

        var keys = {
            ENTER: 13,
            SPACE: 32,
            COMMA: 188, // ','
            PERIOD: 190 // '.'
        };

        $scope.newPost = {
            hashTags: [],
            contacts: [{
                value: ''
            }],
            locations: [],
            locationArea: {}
        };

        $scope.postModel = {
            hashTags : [],
            rulesConfirm : false,
            disableAddContact : false,
            disableDeleteContact : false,
            areaMarker: false,
            separatorKeys : [keys.ENTER, keys.SPACE, keys.COMMA, keys.PERIOD],
            phoneRegExp : '^[(]?[0-9]{2}[)]?[0-9]{3}([-]?[0-9]{2}){2}$'
        };

        $scope.addContact = function () {
            $scope.newPost.contacts.push({
                value: ''
            });
        };

        $scope.deleteContact = function (index) {
            $scope.newPost.contacts.splice(index, 1);
        };

        $scope.savePost = function () {
            dictionaryService.putItem(uri.posts, $scope.newPost).then(function () {
                $location.path('/');
            });
        };

        $scope.$watchCollection('postModel.hashTags', function (newVal) {
            $scope.newPost.hashTags = [];
            newVal.forEach(function (val) {
                $scope.newPost.hashTags.push({
                    name: val
                });
            });
        });

        $scope.$watchCollection('newPost.contacts', function (newVal) {
            var maxContacts = 3;
            $scope.postModel.disableAddContact = (newVal.length == maxContacts);
            $scope.postModel.disableDeleteContact = (newVal.length > 1);
        });

        $scope.$watch('newPost.locationArea', function(){
            $scope.postModel.areaMarker = (Object.keys($scope.newPost.locationArea).length == 0);
        });

        $scope.$on('areaUpdate', function (event, newVal) {
            $scope.newPost.locationArea = newVal;
            mapService.getAddress({lat: newVal.lat, lng: newVal.lng})
                .then(function (val) {
                    $scope.newPost.locationArea.address = val;
                });
            $scope.$apply();
        });

        $scope.$on('locationsUpdate', function (event, newVal) {
            $scope.newPost.locations = newVal;
            newVal.forEach(function (item, index) {
                mapService.getAddress({lat: item.lat, lng: item.lng}).then(function (val) {
                    $scope.newPost.locations[index].address = val;
                });
            });
            $scope.$apply();
        });

        $scope.$on('clearMap', function(){
            $scope.newPost.locationArea = {};
            $scope.newPost.locations = [];
            $scope.$apply();
        });

    }]);