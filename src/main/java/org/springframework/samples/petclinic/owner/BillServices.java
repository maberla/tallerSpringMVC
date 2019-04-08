package org.springframework.samples.petclinic.owner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServices {
	
	@Autowired
	private BillRepository billRepository;
	
	//bills list
	public List<Bill> findAll() {
		return billRepository.findAll();
	}
	
	//bills id
	public Bill findById(Integer id) {
		return billRepository.findOne(id);
	}
	
	//bills post (crear)
	public Bill postById(Bill entity) {
		return billRepository.save(entity);
	}

	//bills delete (borrar)
	public void deleteById(Integer id) {
		
		Bill b = billRepository.findOne(id);
		
		if(b == null) {
			System.out.println("No existe");
		}else {
		    billRepository.delete(b);
		}	
	}
	
	//bills put (actualizar)
	public void putById(Integer id) {
		
		Bill b = billRepository.findOne(id);
		
		if(b == null) {
			System.out.println("No existe");
		}else {
		    billRepository.save(b);
		}
	}
	
	//bills que tengan relleno el campo visit
	public List<Bill> findBillsVisit() {
		return billRepository.getBillByVisitNotNull();
	}
}
