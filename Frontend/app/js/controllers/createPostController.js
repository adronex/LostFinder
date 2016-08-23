'use strict';

app.controller('createPostController', ['$scope', 'dictionaryService', '$location', 'Upload', '$timeout',
    function ($scope, dictionaryService, $location, Upload, $timeout) {

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
            locationArea: {},
            photos: []
        };

        $scope.postModel = {
            hashTags : [],
            rulesConfirm : false,
            disableAddContact : false,
            disableDeleteContact : false,
            areaMarker: false,
            deletePhotoButton: true,
            separatorKeys : [keys.ENTER, keys.SPACE, keys.COMMA, keys.PERIOD],
            phoneRegExp : '^[(]?[0-9]{2}[)]?[0-9]{3}([-]?[0-9]{2}){2}$',
            errorPhotos: [],
            errorMsg: ''
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

        $scope.deletePhoto = function(index){
            $scope.newPost.photos.splice(index, 1);
        };

        $scope.uploadFiles = function(photos) {
            photos.forEach(function(file) {
                file.upload = Upload.upload({
                    url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                    data: {file: file}
                });

                file.upload.then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.postModel.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                        evt.loaded / evt.total));
                });
            });
        }

    }]);