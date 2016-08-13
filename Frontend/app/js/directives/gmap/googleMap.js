'use strict';

app.directive('googleMap', [function () {

    return {
        restrict: 'E',
        controller: 'mapController'
    }
}]);