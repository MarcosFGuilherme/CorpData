package com.corporationdata.CorpData.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Corporation;


@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {
	
	@Transactional(readOnly = true)
	Corporation findByDocument(String document);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Corporation obj WHERE obj.city.id = :cityId ORDER BY obj.document")
	Page<Corporation> findList(@Param("cityId") Integer city, Pageable pageRequest);
	
}
