package com.corporationdata.CorpData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Corporation;


@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {
	
	@Transactional(readOnly = true)
	public Corporation findByDocument(String document);
}
