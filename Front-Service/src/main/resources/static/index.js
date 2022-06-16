(function (){
    angular
        .module('program', ['ngRoute', 'ngStorage','ngSanitize'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/types_prog_languages', {
                templateUrl: 'types_prog_languages/types_prog_languages.html',
                controller: 'types_prog_languagesController'
            })
             .when('/types_prog_languages/programs_list/:program_language', {
                templateUrl: 'programs_list/programs_list.html',
                controller: 'programs_listController'
            })
             .when('/types_prog_languages/programs_list/:program_language/:programId', {
                templateUrl: 'program-view/program-view.html',
                controller: 'program-viewController'
            })
            .when('/moderator_panel', {
                templateUrl: 'moderator_panel/moderator_panel.html',
                controller: 'moderator_panelController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            }).
            when('/about_project', {
                templateUrl: 'about_project/about_project.html',
                controller: 'about_projectController'
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

angular.module('program').controller('indexController', function ($rootScope, $scope, $http, $localStorage,$location,$route) {
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
                    let jwt = response.data.token;
                    let base64Url = jwt.split('.')[1];
                    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                    }).join(''));
                    let roles = JSON.parse(jsonPayload).roles;

                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.siteMemoryUser = {username: $scope.user.username, token: response.data.token, roles: roles};
                        $route.reload();
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
            if ($scope.user != null && $scope.user.username) {
                $scope.user.username = null;
            }
            if ($scope.user != null && $scope.user.password) {
                $scope.user.password = null;
            }
            $route.reload();
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

    $scope.openProfile = function() {
        $location.path('/profile');
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

    $scope.isAdminRole = function () {
        if ($localStorage.siteMemoryUser != null && $localStorage.siteMemoryUser.roles.indexOf( 'ROLE_ADMIN' ) != -1 ) {
            return true;
        } else {
            return false;
        }
    }
});
