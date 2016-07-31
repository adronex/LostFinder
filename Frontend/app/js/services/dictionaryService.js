'use strict';

services.factory('dictionaryService', ['$http', function ($http) {

    return {
        getAll: function (uri) {
            return $http.get(serverUrl + uri).then(function(response) {
                return response.data;
            })
        },

        putItem: function (uri, itemForPut) {
            return $http.put(serverUrl + uri, itemForPut)
                .success(function (data, status) {
                    return data;
                })
        },

        deleteItem: function (uri, itemForDelete) {
            return $http.delete(serverUrl + uri + itemForDelete.id, itemForDelete)
                .success(function (data, status) {
                    return data;
                })
        }
    };
}]);
