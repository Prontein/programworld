angular.module('program').controller('welcomeController', function ($scope, $http,$location ) {
const contextPath = 'http://localhost:5555/core/';

    $scope.getAllArticlesForUser = function () {
        $http({
            url: contextPath + 'api/v1/articles',
            method: 'GET',
            params: {
                view_all_for_user: true
            }
        }).then(function (response) {
           $scope.articlesPage = response.data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
        });
    }

    $scope.getAllArticlesForUser();
});
