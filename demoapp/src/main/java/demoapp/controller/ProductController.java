package demoapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import demoapp.model.Product;
import demoapp.service.ProductService;


@Controller
public class ProductController {

	private ProductService productService;
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);
    
    @Autowired(required=true)
    @Qualifier(value="productService")
    public void setProductService(ProductService ps){
        this.productService = ps;
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", this.productService.listProducts());
        return "ProductAdmin";
    }
     
    //For add and update product both
    @RequestMapping(value= "/products/add", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("product") Product p,BindingResult res,Model model,@RequestParam("file") MultipartFile file) 
    {
    	System.out.println("here 2");
    	if(res.hasErrors()) {
    	
    		model.addAttribute("listProducts", this.productService.listProducts());
            return "ProductAdmin";
        }
	    
        if(p.getId() == 0){
            //new product, add it
            this.productService.addProduct(p);
               if (!file.isEmpty()) {
            	   String name=String.valueOf(p.getId())+".jpg";
    			try {
    				byte[] bytes = file.getBytes();

    				// Creating the directory to store file
    				String rootPath = System.getProperty("catalina.home");
    				File dir = new File(rootPath + File.separator + "images");
    				if (!dir.exists())
    					dir.mkdirs();
                    
    				// Create the file on server
    				File serverFile = new File(dir.getAbsolutePath()
    						+ File.separator + name);
    				BufferedOutputStream stream = new BufferedOutputStream(
    						new FileOutputStream(serverFile));
    				stream.write(bytes);
    				stream.close();

    				logger.info("Server File Location="
    						+ serverFile.getAbsolutePath());

    			   return "redirect:/products";
    			} catch (Exception e) {
    				return "You failed to upload " + name + " => " + e.getMessage();
    			}
    		} else {
    			return "You failed to upload " 
    					+ " because the file was empty.";
    		}
            
        }else{
            //existing product, call update
            this.productService.updateProduct(p);
            return "redirect:/products";
        }
         
      
         
    }
     
    @RequestMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable("id") int id){
         
        this.productService.removeProduct(id);
        return "redirect:/products";
    }
  
    @RequestMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("listProducts", this.productService.listProducts());
        return "ProductAdmin";
    }
}
