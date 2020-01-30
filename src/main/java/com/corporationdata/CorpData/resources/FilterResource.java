package com.corporationdata.CorpData.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.dto.FilterDTO;
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
	
	@RequestMapping(value = "/dto",method = RequestMethod.GET)
	public ResponseEntity<List<FilterDTO>> findAll() {
		List<Filter> list = service.findAll();
		List<FilterDTO> listDto = list.stream().map(obj -> new FilterDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	/* INSERIR */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FilterDTO objDTO){
		Filter obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(obj.getEmail()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
