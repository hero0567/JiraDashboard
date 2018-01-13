'use strict';

/**
 * HomeController
 * @constructor
 */

var app = angular.module('app', []);

app.controller("HomeController", function($scope, $http, $location, $window) {
	
	$scope.license = "{startIndex=0, maxResults=50, total=117, issues=[BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/477912, key=SUSERHEL-1974}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/444937, key=SUSERHEL-1898}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/436733, key=SUSERHEL-1876}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/435393, key=SUSERHEL-1847}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/435387, key=SUSERHEL-1846}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/422735, key=SUSERHEL-1807}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/413561, key=SUSERHEL-1776}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/406973, key=SUSERHEL-1741}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/382327, key=SUSERHEL-1637}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/313419, key=SUSERHEL-1510}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/308460, key=SUSERHEL-1509}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/307961, key=SUSERHEL-1508}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/306658, key=SUSERHEL-1477}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/306167, key=SUSERHEL-1457}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/292521, key=SUSERHEL-1368}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/281134, key=SUSERHEL-1349}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/281132, key=SUSERHEL-1348}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/274624, key=SUSERHEL-1306}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/238866, key=SUSERHEL-1160}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/238726, key=SUSERHEL-1159}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/235401, key=SUSERHEL-1131}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/235231, key=SUSERHEL-1124}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/207508, key=SUSERHEL-976}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/207504, key=SUSERHEL-975}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/207497, key=SUSERHEL-974}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/204389, key=SUSERHEL-959}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/204371, key=SUSERHEL-958}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/204366, key=SUSERHEL-955}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/204361, key=SUSERHEL-954}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/199252, key=SUSERHEL-937}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/199226, key=SUSERHEL-934}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/199096, key=SUSERHEL-931}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/198192, key=SUSERHEL-831}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/198062, key=SUSERHEL-830}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/198034, key=SUSERHEL-829}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/197948, key=SUSERHEL-810}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/196756, key=SUSERHEL-809}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/196740, key=SUSERHEL-807}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/196724, key=SUSERHEL-805}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/196711, key=SUSERHEL-804}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/196688, key=SUSERHEL-801}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/188960, key=SUSERHEL-774}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/180186, key=SUSERHEL-666}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171871, key=SUSERHEL-580}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171870, key=SUSERHEL-579}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171867, key=SUSERHEL-577}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171866, key=SUSERHEL-576}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171865, key=SUSERHEL-575}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171864, key=SUSERHEL-574}, BasicIssue{self=https://jira.community.veritas.com/rest/api/latest/issue/171863, key=SUSERHEL-573}]}";
    $scope.components = [{name : "wanopt", checked : false},
                      {name : "storage", checked : false},
                      {name : "sdcs", checked : false},
                      {name : "factoryreset", checked : false},
                      {name : "checkpoint", checked : false},
                      {name : "autosupport", checked : false}];
    
    
    $scope.chooseComponent  = function(component) {
    	component.checked = !component.checked;
    };    
    
    $scope.generate = function() {
    	$http({
	        method  : 'GET',
	        url     : 'https://jira.community.veritas.com/rest/api/2/search?jql=assignee=Shuai.Ma&startAt=2&maxResults=2',
	        headers : { 'Content-Type': 'application/json', 
	        			"Origin": "http://localhost:8080"}  // set the headers so angular passing info as form data (not request payload)
	    })
        .success(function(data) {
        	
        }).error(function() {
        	
        });
    };
    
    $scope.jsong = function(){
        $http.jsonp("http://public-api.wordpress.com/rest/v1/sites/wtmpeachtest.wordpress.com/posts?callback=JSON_CALLBACK")
            .success(
                function(data, status, header, config){
                    console.log(data);
                }
            )
            .error(
                function(data){
                    alert(data);
                }
            );
}
    
    $scope.generate();
});