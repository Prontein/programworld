angular.module('program').controller('types_prog_languagesController', function ($scope, $http,$location,$routeParams ) {
    const contexPath = 'http://localhost:8182/pmworld';

//    var updateTime=function(){content_type_container.style.display="block"}
//        setTimeout(updateTime,500);
//        clearTimeout(updateTime);


    $scope.openJavaProgram = function() {
        var program_language = 'Java';
        $location.path('/types_prog_languages/programs_list/' + program_language);
    }

});
