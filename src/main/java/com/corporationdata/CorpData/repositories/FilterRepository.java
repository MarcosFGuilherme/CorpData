package com.corporationdata.CorpData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Filter;


@Repository
public interface FilterRepository extends JpaRepository<Filter, Integer> {
	
	@Transactional(readOnly = true)
	public List<Filter> findAllByOrderByEmail();
	
	@Transactional(readOnly = true)
	public Filter findByEmail(String email);

}


