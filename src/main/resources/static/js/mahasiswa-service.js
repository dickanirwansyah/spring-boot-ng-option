angular.module('HipsterApp').factory('Mahasiswa', Mahasiswa);

Mahasiswa.$inject = ['$resource'];

function Mahasiswa($resource){

    var resourceUrl = 'api/mahasiswa/:id';

    return $resource(resourceUrl, {}, {
        'update' : {
            method : 'PUT'
        }
    });
};