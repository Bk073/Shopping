'use strict';
angular.module('myApp').controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
    var self = this;
    self.product={/*productId:'Null',*/productName:'',productPrice:'',description:'', imageUrl:'', category:''};
    self.products=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
    fetchAllProduct()
    function fetchAllProduct(){
        ProductService.fetchAllProduct()
            .then(
            function(d) {
                self.products = d;
            },
            function(errResponse){
                console.error('Error while fetching Product');
            }
        );
    }
 
    function addProduct(product){
        ProductService.addProduct(product)
            .then(
            fetchAllProduct,
            function(errResponse){
            	console.log(product);
                console.error('Error while creating Product');
            }
        );
    }
 
    function  updateProduct(product,id){
        ProductService. updateProduct(product,id)
            .then(
            fetchAllProduct,
            function(errResponse){
                console.error('Error while updating Product');
            }
        );
    }
 
    function deleteProduct(id){
        ProductService.deleteProduct(id)
            .then(
            fetchAllProduct,
            function(errResponse){
                console.error('Error while deleting Product');
            }
        );
    }
 
    function submit() {
        /*if(self.product.id===null){
            console.log('Saving New Prduct', self.product);*/
            addProduct(self.product);
            console.log(self.product);
        /*}else{
            updateProduct(self.product, self.product.id);
            console.log('User updated with id ', self.product.id);
        }*/
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.products.length; i++){
            if(self.product[i].id === id) {
                self.product = angular.copy(self.products[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        /*if(self.product.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }*/
        deleteProduct(id);
    }
 
 
    function reset(){
        self.product={productId:null,productName:'',productPrice:'',description:'', imageUrl:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);