package com.grit.template.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grit.template.models.Product;
import com.grit.template.models.Transaksi;
import com.grit.template.repositories.TransaksiRepository;

@Service
public class TransaksiService {

	@Autowired
	private TransaksiRepository transaksirepo;
	
	public Optional<Transaksi> findTransaksi(String id)
	{
		return transaksirepo.findById(id);
	}
	public List<Transaksi> getDataTransaksi()
	{
		return transaksirepo.findAll();
	}
	public void getTransaksiById(String id)
	{
		transaksirepo.findById(id);
	}
	public void saveTransaksi(Transaksi transaksi) throws Exception
	{
		if(transaksi.getTrDate()==null)
			transaksi.setTrDate(LocalDate.now());
		if(transaksi.getCustName()==null)
			throw new Exception("Customer Name tidak ditemukan");
		if(transaksi.getCustName().equals(""))
			throw new Exception("Customer Name Tidak ditemukan");
		if(transaksi.getTrCode()==null)
		{
			List<Transaksi> tr =transaksirepo.findAll();
			if(tr.size()==0)
			{
				transaksi.setTrCode("TR001");
			}
			else
			{
				String x= tr.get(tr.size()-1).getTrCode();
				String TrCode = x.substring(4, x.length());
				int counter = Integer.parseInt(TrCode);
				transaksi.setTrCode("TR00" + (counter+1));
			}
		}
		if(transaksi.getTotalAmout()==0)
			throw new Exception("Total Amout 0");
		transaksirepo.save(transaksi);
	}
	public void delTransaksi(String id)
	{
		transaksirepo.deleteById(id);
	}
	
	public List<LinkedHashMap<String, Object>> getAllCustomer(String type)
	{
			List<Object[]> lstDatas= transaksirepo.getAllCustomer(type);
		
			List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String,Object>>();
			LinkedHashMap<String, Object> map=null;
			
			for(Object[] obj:lstDatas)
			{
				map = new LinkedHashMap<String, Object>();
				map.put("userName", obj[0]);
				map.put("userFirstName", obj[1]);
				data.add(map);
			}
		return data;
	}

}
