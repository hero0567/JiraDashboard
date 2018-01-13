'use strict';

/**
 * LicenseController
 * @constructor
 */

var app = angular.module('app', []);

app.controller("LicenseController",
		function($scope, $http, $location, $window) {

			$scope.license = "";
			$scope.components = [ {
				name : "wanopt",
				value : "wo",
				checked : false
			}, {
				name : "storage",
				value : "st",
				checked : false
			}, {
				name : "sdcs",
				value : "sd",
				checked : false
			}, {
				name : "factoryreset",
				value : "fr",
				checked : false
			}, {
				name : "checkpoint",
				value : "ck",
				checked : false
			}, {
				name : "autosupport",
				value : "as",
				checked : false
			} ];

			$scope.chooseComponent = function(component) {
				component.checked = !component.checked;
			};

			$scope.generate = function() {
				$scope.license = "";
				angular.forEach($scope.components, function(c) {
					if (c.checked) {
						console.log(c.name);
						if ($scope.license == "") {
							$scope.license = $scope.license + c.value;
						} else {
							$scope.license = $scope.license + "-" + c.value;
						}
					}else{
						var rnum1 = Math.floor(Math.random()*10);
						var rnum2 = Math.floor(Math.random()*10);
						if ($scope.license == "") {
							$scope.license = $scope.license + rnum1 + rnum2;
						} else {
							$scope.license = $scope.license + "-" + rnum1 + rnum2;
						}
 					}
				});

				$http.get('license/generate?components=' + $scope.license, {
					cache : true
				}).success(function(data) {
					var lic = data.substr(0,5);
					for (var i=1; 5*i < data.length; i++){
						var str = data.substr(i*5, 5);
						lic = lic + "-" + str; 
					}
					$scope.license = lic;
				}).error(function(data) {

				});
			};
		});
