package com.corporationdata.CorpData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corporationdata.CorpData.domain.State;


@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
