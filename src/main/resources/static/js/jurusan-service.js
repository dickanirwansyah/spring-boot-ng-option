angular.module('HipsterApp').factory('Jurusan', Jurusan);

Jurusan.$inject = ['$resource'];

function Jurusan($resource){

    var resourceUrl = 'api/jurusan/:id';

    return $resource(resourceUrl, {}, {
        'update' : {
            method : 'PUT'
        }
    });
}