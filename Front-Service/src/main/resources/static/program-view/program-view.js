
angular.module('program').controller('program-viewController', function ($scope, $http,$location,$routeParams) {
    const contextPath = 'http://localhost:5555/core/';
    $scope.readArticle = function () {
        $http.get(contextPath + 'api/v1/articles/' + $routeParams.programId)
            .then(function successCallback (response) {
                $scope.article = response.data;

                console.log(response);
            }, function failureCallback (response) {
                alert(response.data.messages);
                $location.path('/');
            });
    }
    $scope.readArticle();
});
