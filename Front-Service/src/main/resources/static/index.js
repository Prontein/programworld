(function (){
    angular
        .module('program', ['ngRoute', 'ngStorage','ngSanitize'])
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
             .when('/types_prog_languages/programs_list/:programId', {
                templateUrl: 'program-view/program-view.html',
                controller: 'program-viewController'
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
    $scope.passwordType = "password";
    $scope.passwordTypeReg = "password";
     $scope.tryToAuth = function () {

         if ($scope.user == null) {
             alert('Вы не ввели логин');
             return;
         }
         if ($scope.user.password == null) {
             alert('Вы не ввели пароль');
             return;
         }
            $http.post(authPath + 'api/v1/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.siteMemoryUser = {username: $scope.user.username, token: response.data.token};

                        $scope.user.username = null;
                        $scope.user.password = null;
                         $('#exampleModal').modal('hide');
                    }
                }, function errorCallback(response) {
                console.log(response.data.messages);
                alert(response.data.messages);
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

    $scope.registrationUser = function () {
        if ($scope.new_user == null) {
            alert('Вы не заполнили поля');
            return;
        }
        if ($scope.new_user.password != $scope.new_user.matchingPassword) {
            alert('Пароли не совпадают');
            return;
        }
        $http.post(authPath + 'api/v1/registration', $scope.new_user)
            .then(function successCallback (response) {
                $scope.new_user = null;
                alert("Регистрация прошла успешно");
                $('#registrationModal').modal('hide');
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
        });
    };

    $scope.openProgram = function() {
        $location.path('/types_prog_languages');
    }


    $scope.showPassword = function () {
        if ($scope.passwordType == "password") {
            $scope.passwordType = "text";
            angular.element(document.querySelector("#passHideLogo")).addClass('bi bi-eye').removeClass('bi bi-eye-slash');
        } else {
            $scope.passwordType = "password";
            angular.element(document.querySelector("#passHideLogo")).addClass('bi bi-eye-slash').removeClass('bi bi-eye');
        }

    };

    $scope.showRegPassword = function () {
        if ($scope.passwordTypeReg == "password") {
            $scope.passwordTypeReg = "text";
            angular.element(document.querySelector("#passRegHideLogo")).addClass('bi bi-eye').removeClass('bi bi-eye-slash');
        } else {
            $scope.passwordTypeReg = "password";
            angular.element(document.querySelector("#passRegHideLogo")).addClass('bi bi-eye-slash').removeClass('bi bi-eye');
        }
    };

});
