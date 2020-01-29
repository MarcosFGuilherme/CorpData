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
import com.corporationdata.CorpData.domain.dto.CorporationDTO;
import com.corporationdata.CorpData.services.CorporationService;


@RestController
@RequestMapping(value = "/corporations")
public class CorporationResource {
	
	@Autowired
	private CorporationService service;
	
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
	public ResponseEntity<Page<CorporationDTO>> findPage(
			@RequestParam(value="city", defaultValue = "0") Integer cityId, 
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "document") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
//		String nomeDecoded = URL.decodeParam(nome);
//		List<Integer> ids = URL.decodeIntList(categorias);
		service.exportList(cityId);
		Page<Corporation> list = service.search(cityId,  page, linesPerPage, orderBy, direction);
		Page<CorporationDTO> listDto = list.map(obj -> new CorporationDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
