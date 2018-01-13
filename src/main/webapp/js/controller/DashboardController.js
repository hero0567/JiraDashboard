'use strict';

/**
 * HomeController
 * @constructor
 */

var app = angular.module('app', []);

app.controller("DashboardController", function($scope, $http) {
	
	$scope.issue_link_prefix="https://jira.community.veritas.com/browse/";

	$scope.projects = {};
	$scope.currentproject = {};
	$scope.platform = true;
	$scope.autosupport = true;
			
	$scope.alltrains = {"platform":[{name : "Ghostbusters", value : "Ghostbusters"},
		                           {name : "HALO", value : "HALO"},
		                           {name : "Goku", value : "Goku"},
		                           {name : "DOTA Allstars", value : "DOTA Allstars"}],
                        "autosupport":[{name : "​​​Aladdin ", value : "​​​Aladdin "},
		                           {name : "Matrix", value : "Matrix"}]          
						};
		
	$scope.teaminfo = {	"dota":[{ title: "PO", name : "Roger", email: "roger.zhou@veritas.com"},
	                            { title : "PM", name : "Paul", email: "Paul.Sustman@veritas.com"},
	                            { title : "SM", name : "Shuai", email : "shuai.ma@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AS-Team-DOTA", email : "AS-Team-DOTA@veritas.com"}],
                        "halo":[{ title: "PO", name : "Roger", email: "roger.zhou@veritas.com"},
	                            { title : "PM", name : "Ashish", email: "Ashish.Patwardhan@veritas.com"},
	                            { title : "SM", name : "Rocky", email : "rocky.ren@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AS-Team-Halo", email : "AS-Team-Halo@veritas.com"}],
                        "goku":[{ title: "PO", name : "Evan", email: "Evan.Zeng@veritas.com"},
	                            { title : "PM", name : "Paul", email: "rocky.ren@veritas.com"},
	                            { title : "SM", name : "John", email : "John.He@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AS-Team-Goku", email : "AS-Team-Goku@veritas.com"}],
                        "ghostbusters":[{ title: "PO", name : "Evan", email: "Evan.Zeng@veritas.com"},
	                            { title : "PM", name : "Rohit", email: "Rohit.Rakshe@veritas.com"},
	                            { title : "SM", name : "Pooja", email : "Pooja.Bhan@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AS-Team-Ghostbusters", email : "DL-VTAS-AS-Team-Ghostbusters@veritas.com"}],
                        "​​​aladdin":[{ title: "PO", name : "LC", email: "Lei.Cui@veritas.com"},
	                            { title : "PM", name : "Clare", email: "Clare.Deng@veritas.com"},
	                            { title : "SM", name : "Clare", email : "Clare.Deng@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AutoSupport-Aladdin", email : "DL-VTAS-AS-Team-AutoSupport-Aladdin@veritas.com"}],
                        "matrix":[{ title: "PO", name : "LC", email: "Lei.Cui@veritas.com"},
	                            { title : "PM", name : "Clare", email: "Clare.Deng@veritas.com"},
	                            { title : "SM", name : "Clare", email : "Clare.Deng@veritas.com"},
	                            { title : "Team", name : "DL-VTAS-AutoSupport-Matrix", email : "DL-VTAS-AS-Team-AutoSupport-Matrix@veritas.com"}]
	                   };
	
	$scope.owners = [{name:"Shuai.Ma"},{name:"Levy.Xia"}];
	$scope.pis = ["PI1","PI2","PI3","PI4","PI5", "PI6", "PI7", "PI8", "PI9"];
	$scope.trains = ["Velocity Train", "Platform Train", "NBA Train", "Bullet Train", "Autosupport Train"];
	$scope.piindex = 4;
	$scope.train = "Platform Train";
	
	$scope.currentowner = $scope.owners[0].name;
	
	$scope.lal = {};
	$scope.dota = {};
	$scope.goku = {};
	$scope.halo = {};
	$scope.ghostbusters = {};
	$scope.matrix = {};

	$scope.search = function(component) {				
		var jql = "project='Appliance Solutions' and component='"+component+"' and fixVersion = PI5&maxResults=500";	
		$http.get('/rest/api/2/search?jql=' + jql ).success(function(results){
			if (component == "DOTA AllStars"){
				$scope.dota = results;
			}else if (component == "Goku"){
				$scope.goku = results;
			}else if (component == "HALO"){
				$scope.halo = results;
			}else if (component == "Ghostbusters"){
				$scope.ghostbusters = results;
			}else if (component == "Aladdin"){
				$scope.lal = results;
			}else if (component == "Matrix"){
				$scope.matrix = results;
			}
			
		});
	}
	
	$scope.fetchProject = function() {					
		$http.get('/rest/api/2/project'  ).success(function(projects){
			$scope.projects = projects;
		});
	}
	
	$scope.statusStyle= function(status){
		if (status == "Closed" || status == "Done"){ // green background
			return {
				'background-color': '#14892c',
				'border-color': '#14892c',
				'color': '#fff'};
		}else if(status =="Open"){ // blue-grey background
			return {
			    'background-color': '#4a6785',
		    	'border-color': '#4a6785',
		    	'color': '#fff'};
		} else if(status == "In Progress"){ // yellow background
			return {
			    'background-color': '#ffd351',
			    'border-color': '#ffd351',
			    'color': '#594300'};
		}
	}
	
	$scope.progressStyle = function(epic) {	
		var progress = $scope.progress(epic);
		var epicStyle = {"width" : progress + "%"};
		return epicStyle;
	};
	
	$scope.progress = function(epic) {	
		var progress = Math.ceil((epic.finished / epic.total) * 100) ;
		return progress;
	};
	
	$scope.changepi = function(incr) {	
		$scope.piindex = $scope.piindex + incr;
	};
	
	$scope.changetrain = function(train) {					
		$scope.train = train;
		if (train == "Autosupport Train"){
			$scope.search("Aladdin");
			$scope.search("Matrix");
			$scope.platform = false;
			$scope.autosupport = true;
		}else{
			$scope.search("DOTA AllStars");
			$scope.search("HALO");
			$scope.search("Goku");
			$scope.search("Ghostbusters");
			$scope.platform = true;
			$scope.autosupport = false;
		}
	};
	
	
	$scope.changetrain("Platform Train");	
	
});