angular.module('program').controller('profileController', function ($scope, $http, $localStorage,$location,$route) {
    const contextPath = 'http://localhost:5555/core/';
    const authPath = 'http://localhost:5555/auth/';

    $scope.loadProfile = function () {
        $http({
            url: 'http://localhost:5555/auth/api/v1/users',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    if ($localStorage.siteMemoryUser) {
        $scope.loadProfile();
    } else {
        $location.path('/')
    };

    $scope.updateProfile = function () {
        if ($scope.user_update == null) {
            alert('Некорректный ввод данных');
        return;
    }

    $http.put(authPath + 'api/v1/registration', $scope.user_update)
        .then(function successCallback (response) {
            $scope.user_update = null;
            alert("Ваши данные успешно обновлены");
            $route.reload();
        }, function failureCallback (response) {
            $scope.user_update = null;
            alert(response.data.messages);
        });
    };

    $('#btn1').click(function() {
      $('.btnText').toggle();
      delete $scope.user_update;
    });

});
