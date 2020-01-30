package com.corporationdata.CorpData.repositories;

import java.util.List;

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
	
//	@Transactional(readOnly = true)
//	@Query("SELECT obj FROM Corporation obj WHERE "
//			+ " 	(obj.city.id in :cities OR :cities is null) "
//			+ " AND (obj.state.id in :states or :states is null)  "
//			+ "(obj.cnae.id in :cnaes or :cnaes is null) "
//			+ " ORDER BY obj.document")
//	public Page<Corporation> findList(
//			@Param("cities") List<Integer> cities, 
//			@Param("states") List<Integer> states,
//			@Param("cnaes") List<Integer> cnaes,
//			Pageable pageRequest);
	
	@Transactional(readOnly = true)
	@Query(
			"SELECT obj FROM Corporation obj WHERE "
			+ "		(obj.city.id IN :cities OR :cities is null) "
			+ "AND  (obj.city.state.id IN :states OR :states is null) "
			+ "AND  (obj.fiscalCnae.id IN :cnaes OR :cnaes is null) "
			+ "ORDER BY obj.document")
	public Page<Corporation> findList(
			@Param("cities") List<Integer> cities,  
			@Param("states") List<Integer> states,
			@Param("cnaes")  List<Integer> cnaes,
			Pageable pageRequest);
	
}
