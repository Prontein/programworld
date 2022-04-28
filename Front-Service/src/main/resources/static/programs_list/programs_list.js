
angular.module('program').controller('programs_listController', function ($scope, $http,$location,$routeParams) {
    const contextPath = 'http://localhost:5555/core/';


   $scope.openProgram = function(programId) {
        $location.path('/types_prog_languages/programs_list/' + programId);
    }

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

  $scope.getArticles = function () {
        $http({
            url: contextPath + 'api/v1/articles/' + $routeParams.program_language,
            method: 'GET'
        }).then(function (response) {
    /*        $scope.articles = response.data;*/
            console.log($scope.articles);
        });
        }
/*
        $scope.refresh = function() {
            $(".rev_slider").not('.slick-initialized').slick();
        }
*/
    $scope.getArticles();
 /*   $scope.refresh();*/

    $scope.articles =
    [
        {
            "id": 1,
            "fileName": null,
            "fileType": null,
            "author": "Первый",
            "title": "Статья 1",
            "progLanguage": "Java",
            "images": null
        },
        {
            "id": 2,
            "fileName": null,
            "fileType": null,
            "author": "Второй",
            "title": "Статья 2",
            "progLanguage": "Java",
            "images": null
        },
        {
            "id": 4,
            "fileName": null,
            "fileType": null,
            "author": "Автор 4",
            "title": "Статья 4",
            "progLanguage": "Java",
            "images": null
        },
        {
            "id": 5,
            "fileName": null,
            "fileType": null,
            "author": "Автор 5",
            "title": "Статья 5",
            "progLanguage": "Java",
            "images": null
        },
        {
            "id": 6,
            "fileName": null,
            "fileType": null,
            "author": "Автор 6",
            "title": "Статья 6",
            "progLanguage": "Java",
            "images": null
        }
    ]


});
