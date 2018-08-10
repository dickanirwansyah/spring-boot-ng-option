var HipsterApp = angular.module('HipsterApp', ['ngResource', 'ngRoute', 'ngStorage']);

//config constant url
HipsterApp.constant('urls', {
    BASE: 'http://localhost:8080',
    MAHASISWA_SERVICE_API: 'http://localhost:8080/api/mahasiswa'
});
