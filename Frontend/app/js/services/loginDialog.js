app.service('loginDialog', ['$mdDialog', function ($mdDialog) {

    function DialogController($scope, $auth, authService) {
        $scope.closeDialog = function () {
            $mdDialog.cancel();
        };
        $scope.oauth = function(provider) {
            $scope.closeDialog();
            $auth.authenticate(provider).then(
                function (response) {
                    authService.setToken(response.data);
                    console.log('sosisya');
                },
                function (error) {
                    console.log('Access denied! ' + error);
                });
        };
    }

    return {
        show: function (ev) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: '../layout/templates/loginDialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                fullscreen: false
            });
        }
    }
}]);