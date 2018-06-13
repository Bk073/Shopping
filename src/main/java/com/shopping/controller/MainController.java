package com.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.ProductDao;
import com.shopping.dao.UserDao;
import com.shopping.entity.Product;
import com.shopping.entity.User;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	UserDao userDao;
	@Autowired
	ProductDao productDao;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String defaultPage() {
		return "homePage";

	}
	@RequestMapping(value = { "/signUp" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "homePage";
    }
	 @RequestMapping(value = { "/signUp" }, method = RequestMethod.POST)
	    public String saveUser( User user, BindingResult result,
	            ModelMap model) {
	 
	        if (result.hasErrors()) {
	            return "homePage";
	        }
	
	        /*if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
	            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
	            result.addError(ssoError);
	            return "registration";
	        }*/
	         
	        userDao.saveUser(user);
	 
	        model.addAttribute("success", "User " + user.getUsername() + " " + " registered successfully");
	        /*model.addAttribute("loggedinuser", getPrincipal());*/
	        //return "success";
	        return "homePage";
	    }
	

}