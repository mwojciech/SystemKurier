
angular.module('stApp.users', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/users', {
            templateUrl : 'app/partials/users/users.html',
            controller : 'UserCtrl'
        })
    }])
    .controller('UserCtrl', function(UserSrv, $scope, $rootScope){

        $scope.getUsers = function(){
            var promise = UserSrv.users()
                .success(function(result){
                    $scope.users = result;
                })
                .error(function(){

                })
        }

        $scope.getUsers();

        $scope.addUser = function(){
            var promise = UserSrv.user($scope.user)
                .success(function(){
                    $scope.getUsers();
                })
                .error(function(){

                });
        }

    })
