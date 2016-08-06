'use strict';

services.factory('dictionaryService', ['$http', function ($http) {

    return {
        getById: function (uri, id) {
            return $http.get(serverUrl + uri + "/" + id).then(function(response) {
                return response.data;
            })
        },

        getAll: function (uri, filter) {
            return $http.post(serverUrl + uri, filter).then(function(response) {
                return response.data;
            })
        },

        putItem: function (uri, itemForPut) {
            return $http.put(serverUrl + uri, itemForPut)
                .then(function (data) {
                    return data;
                })
        }
    };
}]);
