package com.corporationdata.CorpData.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.Plan;
import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.repositories.FilterRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class FilterService {

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
	
	public Filter find(Integer id) {
		Optional<Filter> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Filter.class.getName()));
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
}
