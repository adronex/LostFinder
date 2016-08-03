
app.controller('headerController', ['$scope', '$location', 'authService', 'dictionaryService', 'loginDialog',
    function ($scope, $location, authService, dictionaryService, loginDialog) {

        $scope.postTypes = {'LOOK': 'Ищу', 'FOUND': 'Нашел'};

        $scope.filter = {
            postType: {},
            date:  new Date()
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

        $scope.user = {
            email: '',
            password: ''
        };

        $scope.$watch((function (){return authService.isAuthenticated()}), function(newVal){ //todo: remake
            $scope.logIn = newVal;
        });

        $scope.$watch((function(){ return $location.path() }), function(newVal){
            $scope.showFilter = (newVal === "/" || newVal === "/map");
        });

        $scope.logout = function(){
            authService.logout();
            $location.path('/');
        };

    }]);

