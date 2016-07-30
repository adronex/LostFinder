'use strict';

app.controller('homeController',['$scope', function($scope) {

    $scope.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    $scope.posts = [
                    {
                        title: "потерял книгу",
                        type: "ищу",
                        img: "icons/book_black_48px.svg"
                    },
                    {
                        title: "нашел смартфон",
                        type: "нашел",
                        img: "icons/mobile_black_48px.svg"
                    },
                    {
                        title: "потерял планшет",
                        type: "ищу",
                        img: "icons/tablet_black_48px.svg"
                    },
                    {
                        title: "потерял ноутбук",
                        type: "ищу",
                        img: "icons/computer_black_48px.svg"
                    },
                    {
                        title: "потерял кошелёк",
                        type: "ищу",
                        img: "icons/wallet_black_48px.svg"
                    },
                    {
                        title: "потерял книгу",
                        type: "ищу",
                        img: "icons/book_black_48px.svg"
                    },
                    {
                        title: "нашел смартфон",
                        type: "нашел",
                        img: "icons/mobile_black_48px.svg"
                    },
                    {
                        title: "потерял планшет",
                        type: "ищу",
                        img: "icons/tablet_black_48px.svg"
                    },
                    {
                        title: "потерял ноутбук",
                        type: "ищу",
                        img: "icons/computer_black_48px.svg"
                    },
                    {
                        title: "потерял кошелёк",
                        type: "ищу",
                        img: "icons/wallet_black_48px.svg"
                    }
                    ];

    $scope.types = { all:{ name: "все", color: ""}, lost:{ name: "ищу", color: "#C62828"}, find:{name: "нашел", color: "#1565C0"}};
}]);