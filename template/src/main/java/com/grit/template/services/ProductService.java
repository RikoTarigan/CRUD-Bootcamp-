package com.grit.template.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grit.template.models.Product;
import com.grit.template.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productrepo;
	
	public List<Product> getAllProduct(){
		return productrepo.findAll();
	}
	public void delProductByid(String productCode)
	{
		productrepo.deleteById(productCode);
	}	
	public Product getProductById(String id)
	{
		Optional<Product> p= productrepo.findById(id);
		if(p.isPresent())
			return p.get();
		else
			return null;
	}
	public void saveProduct(Product p)
	{
			if(!p.getProductCode().isEmpty())
				if(!p.getProductName().isEmpty())
					if(!p.getProductType().isEmpty())
						productrepo.save(p);
	}
	public List<LinkedHashMap<String, Object>> getDataBySearch(Product product)
	{
		
			String pc = product.getProductCode();
			String pn = product.getProductName();
			Double pp = product.getPrice();
			String pt = product.getProductType();
			if(pc==null)
				pc="";
			if(pn==null)
				pn="";
			if(pp==null)
				pp=0.0;
			if(pt==null)
				pt="";
			List<Object[]> lstDatas= productrepo.getDataByQueary(pc, pt, pp, pn);
		
			List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String,Object>>();
			LinkedHashMap<String, Object> map=null;
			
			for(Object[] obj:lstDatas)
			{
				map = new LinkedHashMap<String, Object>();
				map.put("productCode", obj[0]);
				map.put("price", obj[1]);
				map.put("productName", obj[2]);
				map.put("productType", obj[3]);
				data.add(map);
			}
		return data;
	}
//	public List<Product> getDataBySearch1(Product product)
//	{
//		//return productrepo.findByProductCodeLike(product.getProductCode());
//	}
}
