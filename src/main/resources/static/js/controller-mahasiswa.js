'use strict';

angular.module('HipsterApp').controller('mahasiswaCtrl',
    ['MahasiswaService', '$scope', function(MahasiswaService, $scope){

        var self = this;
        self.mahasiswa = {};
        self.mahasiswas = [];
        self.getAllMahasiswa = getAllMahasiswa;
        self.successMessage = '';
        self.errorMessage = '';


        function getAllMahasiswa(){
            return MahasiswaService.getAllMahasiswa();
        }

    }]);