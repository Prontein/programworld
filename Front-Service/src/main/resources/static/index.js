(function (){
    angular
        .module('program', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider/*,$locationProvider*/) {
/*        $locationProvider.html5Mode(true);*/
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/types_prog_languages', {
                templateUrl: 'types_prog_languages/types_prog_languages.html',
                controller: 'types_prog_languagesController'
            })
             .when('/types_prog_languages/programs_list', {
                templateUrl: 'programs_list/programs_list.html',
                controller: 'programs_listController'
            })

            .otherwise({
                redirectTo: '/'
            });
    }
    function run($rootScope, $http, $localStorage) {
        if ($localStorage.siteMemoryUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.siteMemoryUser.token;
        }
    }
})();

angular.module('program').controller('indexController', function ($rootScope, $scope, $http, $localStorage,$location) {
    const authPath = 'http://localhost:5555/auth/';

     $scope.tryToAuth = function () {
            $http.post(authPath + 'api/v1/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.siteMemoryUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }, function errorCallback(response) {
                });
        };

        $scope.tryToLogout = function () {
            $scope.clearUser();
            if ($scope.user.username) {
                $scope.user.username = null;
            }
            if ($scope.user.password) {
                $scope.user.password = null;
            }
            $location.path('/');
        };

        $scope.clearUser = function () {
            delete $localStorage.siteMemoryUser;
            $http.defaults.headers.common.Authorization = '';
        };

        $rootScope.isUserLoggedIn = function () {
            if ($localStorage.siteMemoryUser) {
                return true;
            } else {
                return false;
            }
        };

    $scope.openProgram = function() {
        $location.path('/types_prog_languages');
    }
});
