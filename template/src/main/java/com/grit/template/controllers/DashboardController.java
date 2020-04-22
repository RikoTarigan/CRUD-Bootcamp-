package com.grit.template.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grit.template.models.Product;
import com.grit.template.models.User;
import com.grit.template.models.UserRole;
import com.grit.template.repositories.UserRoleRepository;
import com.grit.template.services.ProductService;
import com.grit.template.services.UserAccesService;
import com.grit.template.services.UserRoleService;
import com.grit.template.services.UserService;

import groovy.lang.BenchmarkInterceptor;

@Controller
public class DashboardController {
	

	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userservice;
	@Autowired
	private UserRoleService userroleservice;
	@Autowired
	private UserAccesService useraccesservice;
	@Autowired
	private ProductService productservice;

	/* myVar */
	private String flag="";
	private String style="";
	public String userActive="";
	/* END MyVar */
	
	
	@GetMapping("login")
	public String login(Model model)
	{
		model.addAttribute("user",new User());
		model.addAttribute("flag",flag);		
		return "login";
	}
	
	@RequestMapping(value = "/dologin",method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("user") User user ) {
		try {
			userActive="";
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
	        = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword());
			
			//System.out.println(usernamePasswordAuthenticationToken);
			
			Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			userActive=user.getUserName().toString();
			return "redirect:dashboard";
		}
		catch (Exception e) {
			flag="Username/Password not match!!";
			return "redirect:login";
		}
	}
	
	
	@GetMapping("/dashboard")
	public String getAll(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		flag="";
		model.addAttribute("userName",userActive);
		model.addAttribute("menu",useraccesservice.getMenuAcces(userActive));
		return "dashboard/dashboard";
	}
	@GetMapping("/Product")
	public String view(Model model)
	{
		model.addAttribute("title","Product");
		model.addAttribute("flag", flag);
		model.addAttribute("notif",style);
		model.addAttribute("userName",userActive);
		model.addAttribute("menu",useraccesservice.getMenuAcces(userActive));
		List<Product> products = productservice.getAllProduct();
		model.addAttribute("products",products);
		flag="";
		style="";
		return "dashboard/viewall";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("byid") String id)
	{
		try {
			productservice.delProductByid(id);
			flag="Product Berhasil Dihapus!!";
			style = "callout callout-warning";
		}
		catch (Exception e) {
			flag="Terjadi Kesalahan pada saat penghapusan!!";
			style = "callout callout-danger";
		}
		return "redirect:Product";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") String id,Model model)
	{
		model.addAttribute("userName",userActive);
		model.addAttribute("menu",useraccesservice.getMenuAcces(userActive));
		
		if(id.isEmpty())
		{
			model.addAttribute("flag",true);
			model.addAttribute("product",new Product());
			return "dashboard/editform";
		}
		else
		{
			model.addAttribute("flag",false);
			Product product = productservice.getProductById(id);
			model.addAttribute("product",product);
			return "dashboard/editform";
		}
	}
	@PostMapping("/editproduct")
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		productservice.saveProduct(product);
		flag="Product Berhasil Disimpan!!";
		style = "callout callout-info";
		return "redirect:Product";
	}	
		
	@GetMapping("/searchform")
	public String search()
	{
		return "redirect:searchform1"; 
	}
	
	@GetMapping("/searchform1")
	public String searchform(Model model, @ModelAttribute("search") Product product)
	{
		//model.addAttribute("products",productservice.getAllProduct());
		List<LinkedHashMap<String, Object>> products = productservice.getDataBySearch(product);

		model.addAttribute("menu",useraccesservice.getMenuAcces(userActive));
		model.addAttribute("products",products);
		model.addAttribute("search",new Product());
		return "dashboard/searchform";
	}
}
