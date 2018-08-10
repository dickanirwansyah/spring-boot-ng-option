angular.module('HipsterApp').controller('HipsterCtrl', HipsterCtrl);
angular.module('HipsterApp').controller('jurusanCtrl', jurusanCtrl);

HipsterCtrl.inject = ['$scope'];
jurusanCtrl.inject = ['$scope', 'Jurusan'];

function HipsterCtrl($scope){

    $scope.name = '';

};

function jurusanCtrl($scope, Jurusan){

    $scope.jurusans = Jurusan.query();
    $scope.message = 'Hallo Jurusan';

    $scope.activeList = [{
        "active" : true,
        "active_name" : "true"
    },
    {
        "active" : false,
        "active_name" : "false"
    }];

    //save jurusan
    $scope.jurusan = {};
    $scope.saveJurusan = function(){
        //check
        if($scope.jurusan.idjurusan !== undefined){
            Jurusan.update($scope.jurusan, function(){
                $scope.jurusans = Jurusan.query();
                $scope.jurusan = {};
            });
        }else{
            Jurusan.save($scope.jurusan, function(){
                $scope.jurusans = Jurusan.query();
                $scope.jurusan = {};
            });
        }
    }
    //update
    $scope.updateInit = function(jurusan){
        console.log("idjurusan : "+jurusan.idjurusan);
        $scope.jurusan = jurusan;
    }
};
