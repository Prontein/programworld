
angular.module('program').controller('moderator_panelController', function ($scope, $http,$location,$routeParams,$sce) {
    const contextPath = 'http://localhost:5555/core/';
   $scope.content1='';
   $scope.selectArticle='';


  /*  $scope.readArticle = function () {
        $http.get(contextPath + 'api/v1/articles/' + $routeParams.articleId)
            .then(function successCallback (response) {
                $scope.article = response.data;
                $scope.content = $sce.trustAsHtml($scope.article.content);
                let reader = new FileReader();
                reader.readAsText();
                reader.onload = function() {
                }


                console.log(response);
            }, function failureCallback (response) {
                alert(response.data.messages);
                $location.path('/');
            });
    }*/

 /*   $scope.readArticle();*/
    $scope.previewArticle = function () {
    //document.querySelector('#bt2').addEventListener('click', function(){
        let file = document.getElementById('file').files[0];

        let reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function(evt) {
            $scope.$apply(function() {
                let res = reader.result;
                console.log(res);
                $scope.content1 = $sce.trustAsHtml(res);

            });
        hljs.initHighlighting();
        };
    }

    $scope.open = function () {
    //document.querySelector('#bt2').addEventListener('click', function(){
        $http.get(contextPath + 'api/v1/articles/' + $routeParams.programId)
            .then(function successCallback (response) {
                $scope.content1 = $sce.trustAsHtml(response.data);
                $scope.$watch('content1', function() {
                    hljs.initHighlighting();
                });
        });
    }

     $scope.saveArticle = function () {
        let file = document.getElementById('file').files[0];
        var fd = new FormData();
        fd.append('file', file);
        fd.append('new_article',new Blob([JSON.stringify($scope.new_article)], {type: "application/json"}));
        $http.post(contextPath + 'api/v1/articles/upload/db', fd, {
/*            transformRequest: angular.identity,*/
            headers: {'Content-Type': undefined}
            })
            .then(function successCallback (response) {
                console.log(response.data);
            }, function errorCallback(response) {
               console.log(response.data.messages);
               alert(response.data.messages);
               });
    }

    $scope.saveImage = function () {
        let file = document.getElementById('ImageMedias').files[0];
        var fd = new FormData();
        fd.append('ImageMedias', file);
    /*    $http.post(contextPath + 'api/v1/articles/upload/local', fd, {*/
        $http.post(contextPath + 'api/v1/articles/upload/local/' + $scope.new_article.progLanguage + $scope.new_article.id, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
            })
            .then(function successCallback (response) {
                console.log(response);
            }, function errorCallback(response) {
               console.log(response.data.messages);
               alert(response.data.messages);
               });
    }

        $("#ImageMedias").change(function () {
            if (typeof (FileReader) != "undefined") {
                var dvPreview = $("#divImageMediaPreview");
                dvPreview.html("");
                $($(this)[0].files).each(function () {
                    var file = $(this);
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var img = $("<img />");
                            img.attr("style", "width: 150px; height:100px; padding: 10px");
                            img.attr("src", e.target.result);
                            dvPreview.append(img);
                        }
                        reader.readAsDataURL(file[0]);
                });
            } else {
                alert("This browser does not support HTML5 FileReader.");
            }
        });

    $scope.showArticles = function () {
        $http({
            url: contextPath + 'api/v1/articles',
            method: 'GET',
        }).then(function (response) {
            console.log(response);
            $scope.articlesPage = response.data;
        });
        }

    $scope.viewArticle = function (article) {
        $scope.selectArticle = article;
    }

    $scope.openArticleForEdit = function (articleId) {
        let file = document.getElementById('fileEdit').files[0];
        var fd = new FormData();
        fd.append('fileEdit', file);
        $http.post(contextPath + 'api/v1/articles/edit/' + articleId, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
            })
            .then(function successCallback (response) {
            console.log(response.data);
            }, function errorCallback(response) {
            console.log(response.data.messages);
            alert(response.data.messages);
        });
    }

});