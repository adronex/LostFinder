app.controller('bodyController', ['$rootScope', '$scope', '$location', 'authService', 'dictionaryService', 'loginDialog',
    function ($rootScope, $scope, $location, authService, dictionaryService, loginDialog) {

        // global access from $parent
        $scope.postTypes = {
            'LOOK': 'ищу',
            'FOUND': 'нашел'
        };

        $scope.postFilter = {
            postType: '',
            date: new Date(),
            show: true
        };

        $scope.model = {
            logIn : false,
            authLoading : false
        };

        $scope.authService = authService;
        $scope.$location = $location;
        $scope.loginDialog = loginDialog;

        $scope.autocomplete = {
            searchText: '',
            items: [],
            selectedItem: {},
            itemText: ''
        };

        $scope.$watch((function () {
            return authService.isAuthenticated()
        }), function (newVal) {
            $scope.model.logIn = newVal;
            $scope.model.authLoading = false;
        });

        $rootScope.$on('authLoading', function(){
            $scope.model.authLoading = true;
        });

        $scope.$on('$routeChangeSuccess', function(event, next){
           var path = next.$$route.originalPath;
           $scope.postFilter.show = (path === "/" || path === "/map");
        });

        $scope.logout = function () {
            authService.logout();
            $location.path('/');
        };

    }]);

