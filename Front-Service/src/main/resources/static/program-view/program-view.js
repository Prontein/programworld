
angular.module('program').controller('program-viewController', function ($scope, $http,$location,$routeParams,$sce) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.open = function () {
        $http.get(contextPath + 'api/v1/articles/' + $routeParams.programId)
            .then(function successCallback (response) {
                $scope.content1 = $sce.trustAsHtml(response.data);
                $scope.$watch('content1', function() {
                    hljs.initHighlighting();
                });
        }, function failureCallback (response) {
                 alert(response.data.messages);
                 $location.path('/');
             });
    }

    $scope.open();
});
