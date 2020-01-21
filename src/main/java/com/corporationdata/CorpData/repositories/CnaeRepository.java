package com.corporationdata.CorpData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corporationdata.CorpData.domain.Cnae;


@Repository
public interface CnaeRepository extends JpaRepository<Cnae, Integer> {

}
