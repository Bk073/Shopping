<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
  </head>
  <body ng-app="myApp">
      <div  ng-controller="ProductController as ctrl">
          <div>
              <div ><span>Add Product</span></div>
              <div>
                  <form ng-submit="ctrl.submit()" name="myForm">
                     <!--  <input type="hidden" ng-model="ctrl.product.productId" /> -->
                      <div>
                          <div>
                              <label>Product Name</label>
                              <div>
                                  <input type="text" ng-model="ctrl.product.productName"  placeholder="Enter product name" required ng-minlength="3"/>
                               
                              </div>
                          </div>
                      </div>
                         
                       
                      <div>
                          <div>
                              <label>Description</label>
                              <div>
                                  <input type="text" ng-model="ctrl.product.description"   placeholder="Enter description. "/>
                              </div>
                          </div>
                      </div>
 
                      <div>
                          <div>
                              <label>Product Price</label>
                              <div>
                                  <input type="number" ng-model="ctrl.product.productPrice"  placeholder="Enter the price" required/>
                                  <div>
                                      
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div>
                          <div>
                              <label>Product Url</label>
                              <div>
                                  <input type="text" ng-model="ctrl.product.imageUrl"  placeholder="Enter the url" required/>
                                  <div>
                                      
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div>
                          <div>
                              <label>Product Url</label>
                              <div>
                                  <input type="text" ng-model="ctrl.product.category"  placeholder="Enter the category" required/>
                                  <div>
                                      
                                  </div>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Add" class="btn btn-primary btn-sm" >
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                     <%--  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />  --%>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Products </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Description</th>
                              <th>Price</th>
                              <th>category</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.products">
                              <td><span ng-bind="u.productId"></span></td>
                              <td><span ng-bind="u.productName"></span></td>
                              <td><span ng-bind="u.description"></span></td>
                              <td><span ng-bind="u.productPrice"></span></td>
                              <td><span ng-bind="u.category"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.productId)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.productId)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                      <%-- <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/resources/js/app.js' />"></script>
      <script src="<c:url value='/resources/js/services/product_service.js' />"></script>
      <script src="<c:url value='/resources/js/controller/product_controller.js' />"></script>
  </body>
</html>