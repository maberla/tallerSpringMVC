package org.springframework.samples.petclinic.owner;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/bills")
public class BillController {
	
	@Autowired
	private BillServices bS;
	
	//listar todas las facturas - get (list)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Bill> list(
		//@RequestParam(value = "page", defaultValue = "1" ) int page,
		//@RequestParam(value = "size", defaultValue = "20" ) int size
			){
			return bS.findAll();
	}
	
	//leer por id de bill - get (id)
	@RequestMapping(value = "/{idNumber}", method = RequestMethod.GET)
	@ResponseBody
	public Bill read(@PathVariable(value = "idNumber") Integer idNumber) {
		Bill b = bS.findById(idNumber);
		return b;
	}
	
	//crear bill - post
//	@RequestMapping(value="/bills", method = RequestMethod.POST)
//	@ResponseBody
//	public Bill create(@RequestBody @Valid Bill b) {
//		return bS.postById(b);
//	}
	
	//control errores
	public ResponseEntity<Bill>saveBill(@RequestBody Bill b){
		if(b!=null)
			if(b.getId()!=null)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			else
				return ResponseEntity.status(HttpStatus.OK).body(this.bS.postById(b));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	//actualizar bill - put
	@RequestMapping(value = "/{idNumber}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@PathVariable(value = "idNumber") Integer idNumber
						) {
		bS.putById(idNumber);
	}

	//delete bill - delete
	@RequestMapping(value = "/{idNumber}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable(value = "idNumber") Integer idNumber) {
		bS.deleteById(idNumber);
	}
}
