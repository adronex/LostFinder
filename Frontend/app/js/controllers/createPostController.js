'use strict';

app.controller('createPostController', ['$scope', '$mdConstant', 'dictionaryService', 'mapService',
    function ($scope, $mdConstant, dictionaryService, mapService) {

        var uri = {
            postTypes: '/api/dictionaries/postTypes',
            posts: '/api/posts'
        };

        var keys = {
            SPACE: 32, // ' '
            COMMA: 188, // ','
            PERIOD: 190 // '.'
        };
        $scope.separatorKeys = [$mdConstant.KEY_CODE.ENTER, keys.SPACE, keys.COMMA, keys.PERIOD];
        $scope.phoneRegExp = new RegExp('^[(]?[0-9]{2}[)]?[0-9]{3}([-]?[0-9]{2}){2}$');

        $scope.newPost = {
            hashTags: [],
            contacts: [{
                value: ''
            }],
            locations: [],
            locationArea: {}
        };
        $scope.hashTags = [];

        $scope.rulesConfirm = false;
        $scope.disableContact = false;
        $scope.deleteContactButton = false;

        dictionaryService.getAll(uri.postTypes).success(function (data) {
            $scope.postTypes = data;
        });

        $scope.$watchCollection('hashTags', function (newVal) {
            $scope.newPost.hashTags = [];
            newVal.forEach(function (val) {
                $scope.newPost.hashTags.push({
                    name: val
                });
            });
        });

        $scope.addContact = function () {
            $scope.newPost.contacts.push({
                value: ''
            });
        };

        $scope.deleteContact = function (index) {
            $scope.newPost.contacts.splice(index, 1);
        };

        $scope.$watchCollection('newPost.contacts', function (newVal) {
            var maxContacts = 3;
            $scope.disableContact = (function () {
                return newVal.length == maxContacts;
            })();
            $scope.deleteContactButton = (function () {
                return newVal.length > 1;
            })();
        });


        $scope.$on('areaUpdate', function(event, newVal){
            $scope.newPost.locationArea = newVal;
            mapService.getAddress({lat: newVal.lat, lng: newVal.lng})
                .then(function (val) {
                    $scope.newPost.locationArea.address = val;
                });
        });

        $scope.$on('locationsUpdate', function(event, newVal){
            $scope.newPost.locations = newVal;
            newVal.forEach(function (item, index) {
                mapService.getAddress({lat: item.lat, lng: item.lng}).then(function (val) {
                    $scope.newPost.locations[index].address = val;
                });
            });
        });

        $scope.savePost = function () {
            dictionaryService.putItem(uri.posts, $scope.newPost).success(function (data) {
                //redirect to created post page
            });
        }

    }]);