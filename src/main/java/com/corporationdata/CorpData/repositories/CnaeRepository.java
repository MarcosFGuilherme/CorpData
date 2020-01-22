package com.corporationdata.CorpData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.State;


@Repository
public interface CnaeRepository extends JpaRepository<Cnae, Integer> {
	
	@Transactional(readOnly = true)
	public List<Cnae> findAllByOrderByName();
}
