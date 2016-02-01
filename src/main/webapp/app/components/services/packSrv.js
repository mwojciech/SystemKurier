
angular.module('stApp.packSrv', [])
    .service('PackSrv', function($http){
        return {
            packs : function(){
                return $http.get('../rest/packs/all');
            },
            pack : function(pack){
                return $http.post('../rest/packs/pack', pack);
            }
        }
    });