package com.corporationdata.CorpData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.LegalNature;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
	@Transactional(readOnly = true)
	public List<City> findAllByOrderByName();
	
	@Transactional(readOnly = true)
	public List<City> findByName(String name);

}
