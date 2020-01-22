package com.corporationdata.CorpData.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.repositories.StateRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;

	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}

	public State find(Integer id) {
		Optional<State> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + State.class.getName()));
	}
	
}
