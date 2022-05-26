
angular.module('program').controller('programs_listController', function ($scope, $http,$location,$routeParams,$localStorage) {
    const contextPath = 'http://localhost:5555/core/';


   $scope.openProgram = function(programId,author,title) {
        $localStorage.author = author;
        $localStorage.title = title;
        $location.path('/types_prog_languages/programs_list/' + $routeParams.program_language + '/' + programId);
    }

  $scope.getArticles = function () {
        $http({
            url: contextPath + 'api/v1/articles',
            method: 'GET',
            params: {
                program_language: $routeParams.program_language
                }
        }).then(function (response) {
            $scope.articles = response.data;
            console.log($scope.articles);
            $(function() {
                $('.rev_slider').slick({
                dots:true,
                //  adaptiveHeight: true,
                waitForAnimate: false,
                rows:2,
                slidesPerRow:2,
                variableWidth: true
                })
            });
        });
        }

    $scope.returnBtnClick = function () {
        history.back();
    }

    $scope.getArticles();
});
