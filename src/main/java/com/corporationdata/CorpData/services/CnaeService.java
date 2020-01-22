package com.corporationdata.CorpData.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.repositories.CnaeRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class CnaeService {

	@Autowired
	private CnaeRepository repo;

	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}

	public Cnae find(Integer id) {
		Optional<Cnae> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + State.class.getName()));
	}
	
}
