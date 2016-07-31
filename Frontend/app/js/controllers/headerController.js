
app.controller('headerController', ['$scope', '$location', '$mdDialog', '$mdMedia', 'authService', 'dictionaryService',
    function ($scope, $location, $mdDialog, $mdMedia, authService, dictionaryService) {

        dictionaryService.getAll(uri.postTypes).then(function(data) {
            $scope.postTypes = data;
        });

        $scope.filter = {
            postType: {},
            date:  new Date()
        };

        $scope.authService = authService;
        $scope.$location = $location;
        $scope.autocomplete = {
            searchText: '',
            items: [],
            selectedItem: {},
            itemText: ''
        };
        $scope.searchTextChange = function(){

        };

        $scope.user = {
            email: '',
            password: ''
        };

        $scope.$watch((function (){return authService.isAuthenticated()}), function(newVal){
            $scope.logIn = newVal;
        });

        $scope.showLoginDialog = function (ev) {
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;

            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: '../layout/templates/loginDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: useFullScreen
                });

            $scope.$watch(function () {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function (wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });

        };

        function DialogController($scope, $mdDialog, $auth) {

            $scope.closeDialog = function () {
                $mdDialog.cancel();
            };

            $scope.login = function () {
                authService.login($scope.user.email, $scope.user.password).then(
                    function (response) {
                        authService.setToken(response.data);
                    },
                    function (error) {
                        console.log('Access denied!');
                    });
            };

            $scope.oauth = function(provider) {
                $scope.closeDialog();
                $auth.authenticate(provider).then(
                    function (response) {
                        authService.setToken(response.data);
                        console.log('sosisya');
                    },
                    function (error) {
                        console.log('Access denied!');
                    });
            };
        }
    }]);

