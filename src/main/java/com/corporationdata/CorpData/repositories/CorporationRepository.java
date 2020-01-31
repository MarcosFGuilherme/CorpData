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
	
	@Transactional(readOnly = true)
	@Query(
			"SELECT DISTINCT obj FROM Corporation obj LEFT JOIN obj.phones p "
			+ "WHERE "
			+ "		(obj.city.id IN :cities OR :cities IS NULL) "
			+ "AND  (obj.city.state.id IN :states OR :states IS NULL) "
			+ "AND  (obj.fiscalCnae.id IN :cnaes OR :cnaes IS NULL) "
			+ "AND 	(obj.email <> :thereIsEmail OR :thereIsEmail IS NULL) "
			+ "AND 	(obj.address <> :thereIsAddress OR :thereIsAddress IS NULL) "
			+ "AND	(p IS NOT NULL OR :thereIsTelephone IS NULL ) "
			+ "ORDER BY obj.document")
	public Page<Corporation> findList(
			@Param("cities") List<Integer> cities,  
			@Param("states") List<Integer> states,
			@Param("cnaes")  List<Integer> cnaes,
			@Param("thereIsEmail")  String thereIsEmail,
			@Param("thereIsAddress")  String thereIsAddress,
			@Param("thereIsTelephone")  String thereIsTelephone,
			Pageable pageRequest);
	
}
