HipsterApp.config(function($routeProvider){

    $routeProvider
        .when('/jurusan', {
            templateUrl: 'jurusan/jurusan.html',
            controller: 'jurusanCtrl'
        })
        .when('/mahasiswa', {
            templateUrl: 'mahasiswa/mahasiswa.html',
            controller: 'mahasiswaCtrl'
        });
});