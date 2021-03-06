package com.corporationdata.CorpData.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.Corporation;
import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.Plan;
import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.domain.dto.FilterDTO;
import com.corporationdata.CorpData.repositories.FilterRepository;
import com.corporationdata.CorpData.services.exception.DataIntegrityException;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class FilterService {

	private static final Logger LOG =  LoggerFactory.getLogger(FilterService.class);
	
	@Autowired
	private FilterRepository repo;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CnaeService cnaeService;
	
	public Filter find(String email) {
		
		Filter obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Email: " + email + ", Tipo: " + Corporation.class.getName());
		}
		return obj;
	}
	
	public Page<Filter> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public List<Filter> findAll() {
		return repo.findAll();
	}
	
	
	public void initialLoad() {
		List<Filter> filters  = new ArrayList<>(); 
		
		String email = "marcosfs.guilherme@gmail.com";
		
		Filter obj = new Filter(email,true,true,true,null);
		
		Plan plan = planService.find(1);
		// 3538709->Piracicaba | 35->SP
		Integer stateId = 35;
		State states = stateService.find(stateId);
		obj.getStates().addAll(Arrays.asList(states));
		
		Integer cityId;
		cityId = 3538709;
		City c1 = cityService.find(cityId);
		cityId = 3526902;
		City c2 = cityService.find(cityId);
		obj.getCities().addAll(Arrays.asList(c1,c2));
		
		
		Integer cnaeId = 4520001;
		Cnae cnaes = cnaeService.find(cnaeId);
		obj.getCnaes().addAll(Arrays.asList(cnaes));
		
		obj.setPlans(plan);
		
		filters.add(obj);
		
		repo.saveAll(filters);
	}
	
	public Filter insert(Filter obj) {
		return repo.save(obj);
	}
	

	public Filter fromDTO(FilterDTO objDTO) {
		String email = objDTO.getEmail();
		Boolean thereIsEmail = objDTO.getThereIsEmail();
		Boolean thereIsAddress = objDTO.getThereIsAddress();
		Boolean thereIsTelephone = objDTO.getThereIsTelephone();
		List<Cnae> cnaes = new ArrayList<>();
		for (Integer id : objDTO.getCnaes()) {
			cnaes.add(cnaeService.find(id));
		}
		List<State> states = new ArrayList<>();
		for (Integer id : objDTO.getStates()) {
			states.add(stateService.find(id));
		}
		List<City> cities = new ArrayList<>();
		for (Integer id : objDTO.getCities()) {
			cities.add(cityService.find(id));
		}
		Plan plan = planService.find(objDTO.getPlan());
		Filter obj = new Filter(email, thereIsEmail, thereIsAddress, thereIsTelephone, plan);
		
		obj.getCnaes().addAll(cnaes);
		obj.getCities().addAll(cities);
		obj.getStates().addAll(states);
		return obj;
	}
	
}
