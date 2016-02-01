
angular.module('stApp.packs', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/packs', {
            templateUrl : 'app/partials/packs/packs.html',
            controller : 'PackCtrl'
        })
    }])
    .controller('PackCtrl', function(PackSrv, $scope, $rootScope){

        $scope.getPacks = function(){
            var promise = PackSrv.packs()
                .success(function(result){
                    $scope.packs = result;
                })
                .error(function(){

                })
        }

        $scope.getPacks();

        $scope.addPack = function(){
            var promise = PackSrv.pack($scope.pack)
                .success(function(){
                    $scope.getPacks();
                })
                .error(function(){

                });
        }

    })
