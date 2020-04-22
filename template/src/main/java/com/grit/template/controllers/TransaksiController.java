package com.grit.template.controllers;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grit.template.models.Customer;
import com.grit.template.models.Product;
import com.grit.template.models.Transaksi;
import com.grit.template.models.User;
import com.grit.template.services.ProductService;
import com.grit.template.services.TransaksiService;
import com.grit.template.services.UserAccesService;
import com.grit.template.services.UserService;

@Controller
public class TransaksiController {
	
	@Autowired
	private TransaksiService transaksiservice;
	@Autowired
	private UserAccesService useraccesservice;
	@Autowired
	private DashboardController dc;
	@Autowired
	private UserService userservice;
	@Autowired
	private ProductService produkservice;
	/* Notifikasi */
	private String flag="";
	private String style="";
	/* END NOTIFIKASI */
	
	@GetMapping("/Transaction")
	public String transaksi(Model model)
	{
		model.addAttribute("userName",dc.userActive);
		model.addAttribute("flag",flag);
		model.addAttribute("notif",style);
		model.addAttribute("title","Transaksi");
		model.addAttribute("menu",useraccesservice.getMenuAcces(dc.userActive));
		List<Transaksi> transaction = transaksiservice.getDataTransaksi();
		model.addAttribute("transaction",transaction);
		flag="";
		style="";
		return "dashboard/transaksi/transaksi";
	}
	
	
	@GetMapping("/transaksi-edit")
	public String edittransaksi(@RequestParam("id") String id,Model model)
	{
		model.addAttribute("transaksi",transaksiservice.findTransaksi(id));
		return "dashboard/transaksi/edittransaksi";
	}
	@PostMapping("/transaksi-save")
	public String savetransaksi(@ModelAttribute("transaksi") Transaksi transaksi,Model model)
	{
		try
		{
			style = "callout callout-info";
			flag="Transaksi Berhasil Disimpan";
			transaksiservice.saveTransaksi(transaksi);
		}
		catch (Exception e) {
			style = "callout callout-danger";
			flag="Transaksi Tidak berhasil Disimpan!! " + e.getMessage();
		}
		
		return "redirect:Transaction";
	}
	
	@GetMapping("/transaksi-delete")
	public String delete(@RequestParam("id") String id,Model model)
	{
		transaksiservice.delTransaksi(id);
		style = "callout callout-warning";
		flag="Transaksi "+id.toString()+" Berhasil di hapus!!";
		return "redirect:Transaction";
	}
	
	/* TAMBAH */
	@GetMapping("/tambah-tr")
	public String tambahtr(Model model,@ModelAttribute("transaksi") Transaksi transaksi)
	{
		String trCode="";
		List<Transaksi> tr =transaksiservice.getDataTransaksi();
		String x="";
		if(tr.size()!=0)
		{
			 x= tr.get(tr.size()-1).getTrCode();
			String TrCode = x.substring(4, x.length());
			int counter = Integer.parseInt(TrCode);
			trCode += "TR00" + (counter+1);
		}
		if(tr.size()==0)
			trCode+="TR001";
		
		model.addAttribute("userName",dc.userActive);
		model.addAttribute("menu",useraccesservice.getMenuAcces(dc.userActive));
		model.addAttribute("Customer",transaksiservice.getAllCustomer("CUSTOMER"));
		model.addAttribute("product",produkservice.getAllProduct());
		//System.out.println(transaksiservice.getAllCustomer("CUSTOMER").get(0));
		model.addAttribute("trCode",trCode);
		model.addAttribute("user",new User());
		return "dashboard/transaksi/tambah";
	}
	
	
	
	@RequestMapping(value="/getdata")
	public ResponseEntity<List<Product>> getAll()
	{
		List<Product> response = produkservice.getAllProduct();
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
