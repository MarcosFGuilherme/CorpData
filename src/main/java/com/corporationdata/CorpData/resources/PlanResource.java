package com.corporationdata.CorpData.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corporationdata.CorpData.domain.Plan;
import com.corporationdata.CorpData.domain.dto.BudgetDTO;
import com.corporationdata.CorpData.domain.dto.CorporationDTO;
import com.corporationdata.CorpData.services.PlanService;


@RestController
@RequestMapping(value = "/plans")
public class PlanResource {
	
	@Autowired
	private PlanService service;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Plan> find(@PathVariable Integer id) {
		Plan obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Plan>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Plan> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/budget/{quantity}", method = RequestMethod.GET)
	public ResponseEntity<BudgetDTO> findBudget(@PathVariable Integer quantity) {
		Plan obj = service.findBudget(quantity);
		BudgetDTO objDTO = new BudgetDTO(obj,quantity);
		return ResponseEntity.ok().body(objDTO);
	}
//	@RequestMapping(value = "/dto",method = RequestMethod.GET)
//	public ResponseEntity<List<PlanDTO>> findAll() {
//		List<Plan> list = service.findAll();
//		List<PlanDTO> listDto = list.stream().map(obj -> new PlanDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
	
	/* INSERIR */
//	@RequestMapping(value = "/insert",method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody PlanDTO objDTO){
//		Plan obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(obj.getEmail()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
	
}
