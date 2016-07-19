app.controller('createPostController', ['$scope', '$mdConstant', function ($scope, $mdConstant) {

    $scope.newPost = {
        hashTags: [],
        contacts: [{
            value: ''
        }]
    };

    $scope.postTypes = ['нашел', 'ищу'];

    $scope.rulesConfirm = false;

    var keys = {
        SPACE: 32, // ' '
        COMMA: 188, // ','
        PERIOD: 190 // '.'
    };
    $scope.separatorKeys = [$mdConstant.KEY_CODE.ENTER, keys.SPACE, keys.COMMA, keys.PERIOD];
    $scope.phoneRegExp = new RegExp('^[(]?[0-9]{2}[)]?[0-9]{3}([-]?[0-9]{2}){2}$');

    var maxContacts = 3;
    $scope.disableContact = false;
    $scope.addContact = function () {
        $scope.newPost.contacts.push({
            value: ''
        });
        if ($scope.newPost.contacts.length == maxContacts) $scope.disableContact = true;
    };
}]);