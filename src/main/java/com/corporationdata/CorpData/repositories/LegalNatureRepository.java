package com.corporationdata.CorpData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corporationdata.CorpData.domain.LegalNature;


@Repository
public interface LegalNatureRepository extends JpaRepository<LegalNature, Integer> {

	@Transactional(readOnly = true)
	public List<LegalNature> findAllByOrderByName();
}
