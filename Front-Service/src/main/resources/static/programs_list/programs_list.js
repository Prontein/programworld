
angular.module('program').controller('programs_listController', function ($scope, $http,$location,$routeParams) {

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

    $scope.programsList = {
        "languageName": "Java",
        "content": [
            {
                "id": 1,
                "author": "Васильев В.Е.",
                "title": "100 вопросов по Java",
                "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 2,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                "views":10,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 3,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                "views":55,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 4,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 5,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 6,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 7,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 8,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 9,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 10,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 11,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 12,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 13,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 14,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 15,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 16,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            },
            {
                "id": 17,
                "author": "Иванов П.Н.",
                "title": "Основы Java",
                 "views":12,
                "amount_scores": 4,
                "comments":5,
                "rating": 67
            }
        ]
    }
});
