angular.module('program').controller('program-viewController', function ($localStorage,$scope,$rootScope, $http,$location,$routeParams,$sce,$route) {
    const contextPath = 'http://localhost:5555/core/';


    if ($localStorage.siteMemoryUser) {
        $scope.username = $localStorage.siteMemoryUser.username;
    }

    $scope.article_comment = {username: $scope.username, article_Id: $routeParams.programId};

    $scope.open = function () {
        $http({
            url: contextPath + 'api/v1/articles/' + $routeParams.program_language,
            method: 'GET',
            params: {
                programId: $routeParams.programId
                }
        })
            /*.get(contextPath + 'api/v1/articles/' + $routeParams.program_language +'/' + $routeParams.programId)*/
            .then(function successCallback (response) {

                $scope.content1 = $sce.trustAsHtml(response.data);
                $scope.$watch('content1', function() {
                    hljs.initHighlighting();
                });
        }, function failureCallback (response) {
                 alert(response.data.messages);
                 $location.path('/');
             });
    }

    $scope.createComment = function () {
       /* $scope.article_comment = {username: $scope.username, article_Id: $routeParams.programId};
*/
        $http.post(contextPath + 'api/v1/comments', $scope.article_comment)
            .then(function successCallback (response) {
                console.log($scope.article_comment);
                $scope.article_comment = null;
                alert("Ваш комментарий успешно добавлен");
               /* $scope.open();*/
                $route.reload();
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.loadComments = function () {
        $http({
            url: contextPath + 'api/v1/comments/',
            method: 'GET',
            params: {
                article_Id: $routeParams.programId
                }
        })
            .then(function successCallback (response) {
                $scope.article_comments = response.data;
                showComments($scope.article_comments);

            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    function showComments(comments){
        let commentField = document.getElementById('comment-field');
        let out ='';

        if ($localStorage.siteMemoryUser != null && $localStorage.siteMemoryUser.roles.indexOf( 'ROLE_ADMIN' ) != -1 ) {
            comments.forEach(function(item){
                out += `<p class="text-right small" style="margin: 15px 0 0;">${item.username}</p>`;
                out += `<label name="result" style="padding: 10px;border-radius: 10px;background-color: white;max-width:1000px; word-wrap:break-word;">${item.content}</label>`;
                out += `<button class="btnDelete" type="button" value="${item.id}" onclick="">Удалить</button>`;
            })
        } else {
            comments.forEach(function(item){
                out += `<p class="text-right small" style="margin: 15px 0 0;">${item.username}</p>`;
                out += `<label name="result" style="padding: 10px;border-radius: 10px;background-color: white;max-width:1000px; word-wrap:break-word;">${item.content}</label>`;
            })
        }

        commentField.innerHTML = out;
        activateBtn();
    }

    function activateBtn(){
        var btns = document.querySelectorAll('.btnDelete');
        btns.forEach(function(btn) {
          // Вешаем событие клик
          btn.addEventListener('click', function(e) {
  /*          console.log('Button clicked' + e.target.classList);*/
            deleteComment(e.target.value);
          })
        })

    }

     function deleteComment(comment_id) {
        $http({
            url: contextPath + 'api/v1/comments',
            method: 'DELETE',
            params: {
                comment_Id: comment_id
            }
        }).then(function (response) {
            console.log(response);
            $route.reload();
        });
    }

  $scope.getRating = function () {
        $http.get(contextPath + 'api/v1/ratings/' + $routeParams.programId)
            .then(function successCallback (response) {
                $scope.rating_info = response.data;
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.setScore = function () {
    if ($scope.rate_value === undefined) {
        alert("Вы не указали оценку!");
        return;
    }

    let ratingDTO = {userScore: $scope.rate_value.select, username: $scope.username, article_Id: $routeParams.programId};
    $http.post(contextPath + 'api/v1/ratings', ratingDTO)
            .then(function successCallback (response) {
                $scope.rate_value.select = null;
                alert("Спасибо за вашу оценку!");
               /* $scope.open();*/
                $route.reload();
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.open();
    $scope.loadComments();
    $scope.getRating();
});
