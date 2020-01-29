package com.corporationdata.CorpData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corporationdata.CorpData.domain.Plan;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
	
}


