package com.corporationdata.CorpData.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Plan;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
	
	@Transactional(readOnly = true)
	public Optional<Plan> findTop1ByTotalCompaniesGreaterThanEqualOrderById(Integer totalCompanies);
	
}


