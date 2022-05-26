angular.module('program').controller('types_prog_languagesController', function ($scope, $http,$location,$routeParams ) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.getAllArticlesForUser = function () {
        $http({
            url: contextPath + 'api/v1/articles',
            method: 'GET',
            params: {
                view_all_for_user: true
            }
        }).then(function (response) {
            let articles = response.data;

            $scope.Java_articles = articles.filter(function(article){
                return (article.progLanguage == "Java");
            }).length;
        });
    }

    $scope.openJavaProgram = function() {
        let program_language = 'Java';
        $location.path('/types_prog_languages/programs_list/' + program_language);
    }

    $scope.getAllArticlesForUser();
});
