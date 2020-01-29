package com.corporationdata.CorpData.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corporationdata.CorpData.domain.Corporation;
import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.dto.CorporationDTO;
import com.corporationdata.CorpData.services.FilterService;


@RestController
@RequestMapping(value = "/filters")
public class FilterResource {
	
	@Autowired
	private FilterService service;
	
	@RequestMapping(value = "{email}", method = RequestMethod.GET)
	public ResponseEntity<Filter> find(@PathVariable String email) {
		
		Filter obj = service.find(email);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Filter>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "email") String orderBy, 
			@RequestParam(value="direction", defaultValue = "DESC") String direction) {
		Page<Filter> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
