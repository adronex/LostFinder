
app.controller('headerController', ['$scope', '$location', '$mdDialog', '$mdMedia', 'authService',
    function ($scope, $location, $mdDialog, $mdMedia, authService) {

        $scope.$location = $location;
        $scope.searchText = '';
        $scope.ctrl = {};

        $scope.user = {
            email: '',
            password: ''
        };

        $scope.showLoginDialog = function (ev) {
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;

            $mdDialog.show({
                    controller: DialogController,
                    templateUrl: '../layout/loginDialog.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: useFullScreen
                })
                .then(function (answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function () {
                    $scope.status = 'You cancelled the dialog.';
                });
            $scope.$watch(function () {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function (wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });

        };

        function DialogController($scope, $mdDialog, $auth) {
            $scope.hide = function () {
                $mdDialog.hide();
            };
            $scope.cancel = function () {
                $mdDialog.cancel();
            };
            $scope.answer = function (answer) {
                $mdDialog.hide(answer);
            };
            $scope.login = function () {
                authService.login($scope.user.email, $scope.user.password).then
                (
                    function (response) {
                        authService.setToken(response.data);
                    },
                    function (error) {
                        alert('Access denied!');
                    });
            };
            $scope.oauth = function(provider) {
                $auth.authenticate(provider).then(
                    function (response) {
                        authService.setToken(response.data);
                        alert('sosisya');
                    },
                    function (error) {
                        alert('Access denied!');
                    });
            };
        }
    }]);

