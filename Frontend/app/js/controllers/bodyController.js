app.controller('bodyController', ['$rootScope', '$scope', '$location', 'authService', 'dictionaryService', 'loginDialog',
    function ($rootScope, $scope, $location, authService, dictionaryService, loginDialog) {

        $scope.postTypes = {
            'LOOK': 'ищу',
            'FOUND': 'нашел'
        };

        $scope.filter = {
            postType: '',
            date: new Date()
        };

        $scope.logIn = false;
        $scope.authLoading = false;

        $scope.authService = authService;
        $scope.$location = $location;
        $scope.loginDialog = loginDialog;

        $scope.autocomplete = {
            searchText: '',
            items: [],
            selectedItem: {},
            itemText: ''
        };

        $scope.user = {
            email: '',
            password: ''
        };

        $scope.$watch((function () {
            return authService.isAuthenticated()
        }), function (newVal) {
            $scope.logIn = newVal;
            $scope.authLoading = false;
        });

        $rootScope.$on('authLoading', function(){
            $scope.authLoading = true;
        });

        $scope.$on('$routeChangeSuccess', function(event, next){
           var path = next.$$route.originalPath;
           $scope.showFilter = (path === "/" || path === "/map");
        });

        $scope.logout = function () {
            authService.logout();
            $location.path('/');
        };

    }]);

