

angular.module('stApp.main', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/main',{
            templateUrl : 'app/partials/main/main.html',
            controller : 'MainCtrl'
        })
    }])
    .controller('MainCtrl', function($scope, $location){
            $scope.main = "IT is main view";

    })
