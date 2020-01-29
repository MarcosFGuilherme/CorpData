package com.corporationdata.CorpData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corporationdata.CorpData.domain.Filter;


@Repository
public interface FilterRepository extends JpaRepository<Filter, Integer> {
	
}


