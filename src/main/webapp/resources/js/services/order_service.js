'use strict';
angular.module('myApp').factory('OrderService',['$http','$q', function($http, $q){
	var REST_SERVICE_URI = 'http://localhost:8080/shopng/order/';
	
	var factory = {
			fetchAllProduct : fetchAllProduct,
			addProduct : addProduct,
			updateProduct : updateProduct,
			deleteProduct : deleteProduct
	};
	return factory;
	
	function fetchAllProduct(){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI)
		      .then(
		    	function(response){
		    		deferred.resolve(response.data);
		    	},
		    	function(errResponse){
		    	    console.error('Error while fetching Products');
		    	    deferred.reject(errResponse);
		    	}
		      );
		return deferred.promise;
	}
	function addProduct(product){
		var deferred = $q.defer();
		$http.post('http://localhost:8080/shopng/addProduct/', product)
		    .then(
		       function(response){
		    	   deferred.resolve(response.data);
		       },
		       function(errResponse){
		    	   console.log('Error while adding product');
		    	   deferred.reject(errResponse);
		       }
		       
		    );
		return deferred.promise;
	}
	function updateProduct(product,id){
		var deferred = $q.defer();
		$http.put(REST_SERVICE_URI+"/update"+id, product)
		    .then(
		       function(response){
		    	   deferred.resolve(response.data);
		       },
		       function(errResponse){
		    	   console.error('Error while updating product');
		    	   deferred.reject(errResponse);
		       }
		    );
		return deferred.promise;
	}
	function deleteProduct(id){
		var deferred = $q.defer();
		 $http.post(REST_SERVICE_URI+"/delete/"+id)
		    .then(
		       function(response){
		    	   deferred.resolve(response.data);
		       },
		       function(errResponse){
		    	   console.error('Error while deleting product');
		    	   deferred.reject(errResponse);
		       }
		    );
		return deferred.promise;
	}
}])
