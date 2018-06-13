package com.shopping.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shopping.dao.ProductDao;
import com.shopping.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	@Autowired
	ProductDao productDao;
	private static final Logger logger = Logger.getLogger(ProductRestController.class);
	//Retrive all Product
	
	@RequestMapping(value= "", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProduct(){
		if(logger.isDebugEnabled()){
			logger.debug("Product list is executed!");
		}
		List<Product> products = productDao.list();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
	}
	//Retrive single product
	
	@RequestMapping(value= "{productName}", method= RequestMethod.GET)
	public ResponseEntity <Product> getProduct(@PathVariable("productName") String productName){
		Product product = productDao.getProductsByName(productName);
		if(product.equals(null)){
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product> (product,HttpStatus.OK);
	}
	//Add Product
	
	 @RequestMapping( value="/admin/addProduct/",method= RequestMethod.POST)
	    public ResponseEntity<Void> createProduct(@RequestBody Product product/*,    UriComponentsBuilder ucBuilder*/) {
	        /*System.out.println("Creating Product " + product.getProductName());
	  
	        if (productDao.isProductExit(product)) {
	            System.out.println("A Product with name " + product.getProductName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }*/
	  
	       productDao.add(product);
	       if(logger.isDebugEnabled()){
				logger.debug("AddProduct is executed!");
			}
	  /*
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(product.getProductId().toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);*/
	       return new ResponseEntity<Void	>(HttpStatus.CREATED);
	    }
	 //Delete product
	 @RequestMapping(value = "/admin/delete/{productId}", method = RequestMethod.POST)
	 @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") int productId) {
	        System.out.println("Fetching & Deleting Product with id " + productId);
	  
	        Product product = productDao.get(productId);
	        if (product == null) {
	            System.out.println("Unable to delete. Product with id " + productId + " not found");
	            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	        }
	  
	        productDao.delete(productId);
	        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	    }
	  
	  //Update product
	 @RequestMapping(value="/admin/update/{productId}", method = RequestMethod.PUT)
	 public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId){
		 Product product = productDao.get(productId);
		 productDao.update(product);
		 return new ResponseEntity<Product>(HttpStatus.OK);
	 }
 }
