package com.corporationdata.CorpData.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.LegalNature;
import com.corporationdata.CorpData.repositories.LegalNatureRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class LegalNatureService {

	@Autowired
	private LegalNatureRepository repo;

	public List<LegalNature> findAll() {
		return repo.findAllByOrderByName();
	}

	public LegalNature find(Integer id) {
		Optional<LegalNature> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + LegalNature.class.getName()));
	}
	
}
