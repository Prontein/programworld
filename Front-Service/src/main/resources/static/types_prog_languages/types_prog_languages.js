angular.module('program').controller('types_prog_languagesController', function ($scope, $http,$location ) {
    const contexPath = 'http://localhost:8182/pmworld';

    $scope.openJavaProgram = function() {
        $location.path('/types_prog_languages/programs_list');
    }
});
