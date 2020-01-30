package com.corporationdata.CorpData.resources;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corporationdata.CorpData.domain.Corporation;
import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.dto.CorporationDTO;
import com.corporationdata.CorpData.domain.dto.EmailDTO;
import com.corporationdata.CorpData.services.CorporationService;
import com.corporationdata.CorpData.services.FilterService;


@RestController
@RequestMapping(value = "/corporations")
public class CorporationResource {
	
	@Autowired
	private CorporationService service;
	
	@Autowired
	private FilterService filterService;
	
	@RequestMapping(value = "{document}", method = RequestMethod.GET)
	public ResponseEntity<Corporation> find(@PathVariable String document) {
		
		Corporation obj = service.find(document);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Corporation>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "document") String orderBy, 
			@RequestParam(value="direction", defaultValue = "DESC") String direction) {
		Page<Corporation> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Page<CorporationDTO>> findFilter(
			@Valid @RequestBody EmailDTO objDTO,
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "document") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {

		Filter filter = filterService.find(objDTO.getEmail());
		Page<Corporation> list = service.search(filter,  page, linesPerPage, orderBy, direction);
		Page<CorporationDTO> listDto = list.map(obj -> new CorporationDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/exportlist", method = RequestMethod.GET)
	public ResponseEntity<Void> exportList(
			@Valid @RequestBody EmailDTO objDTO,
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "25") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "document") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		Filter filter = filterService.find(objDTO.getEmail());
		service.exportList(filter, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.noContent().build();
	}
}
