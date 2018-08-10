'use strict';

angular.module('HipsterApp').factory('MahasiswaService',
    ['$localStorage', '$http', '$q', 'urls',
        function($localStorage, $http, $q, $urls){

            var factory = {
                loadAllMahasiswa: loadAllMahasiswa,
                getAllMahasiswa: getAllMahasiswa,
                getMahasiswaId: getMahasiswaId
            };

            return factory;

            function loadAllMahasiswa() {
                console.log('fetching mahasiswa..');
                var deffered = $q.defer();
                $http.get(urls.MAHASISWA_SERVICE_API)
                    .then(
                        function(response) {
                            console.log('fetched successfully !');
                            $localStorage.mahasiswas = response.data;
                            deffered.resolve(response);
                        },
                        function(errResponse) {
                            console.log('fetched unsuccessfully !');
                            deffered.reject(errResponse);
                        }
                    );
                    return deffered.promise;
            };

            function getAllMahasiswa(){
                return $localStorage.mahasiswas;
            };

            function getMahasiswaId(idmahasiswa){
                console.log('fetching kode mahasiswa : '+idmahasiswa);
                var deffered = $q.defer();
                $http.get(urls.MAHASISWA_SERVICE_API+"/"+idmahasiswa)
                    .then(
                        function(response) {
                            console.log('fetched successfully !');
                            deffered.resolve(response.data);
                        },
                        function(errResponse){
                            console.log('fetched unsuccessfull !');
                            deffered.reject(errResponse);
                        }
                    );
                    return deffered.promise;
            };

        }]);