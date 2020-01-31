package com.corporationdata.CorpData.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Plan;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
	
//	@Transactional(readOnly = true)
//	@Query("SELECT obj FROM Plan obj WHERE obj.totalCompanies <= :quantity")
//	public Optional<Plan> findTop1ByQuantityOrderByQuantityDesc(@Param("quantity")  Integer quantity);
	
	@Transactional(readOnly = true)
	public Optional<Plan> findTop1ByTotalCompaniesGreaterThanOrderById(Integer totalCompanies);
	
}


