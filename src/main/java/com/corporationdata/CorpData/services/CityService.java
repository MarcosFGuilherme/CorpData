package com.corporationdata.CorpData.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.repositories.CityRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class CityService {

	private static final Logger LOG =  LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityRepository repo;

	public List<City> findAll() {
		return repo.findAllByOrderByName();
	}

	public City find(Integer id) {
		Optional<City> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + City.class.getName()));
	}
	
	public City findByNameAndUf(String name, String uf) {
		List<City> obj = repo.findByName(name);
		City city = null;
		for (City c : obj) {
			if ( c.getstate().getUf().equals(uf)) {
				city = c;
			}
		}
		return city;
	}
}
