package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.repositories.CnaeRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class CnaeService {

	@Autowired
	private CnaeRepository repo;

	public List<Cnae> findAll() {
		return repo.findAllByOrderByName();
	}

	public Cnae find(Integer id) {
		Optional<Cnae> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cnae.class.getName()));
	}
	
	public void initialLoad() {
		List<Cnae> cnaes  = new ArrayList<>(); 
		String path = "F:\\Temp\\csv\\cnae.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			int l = 0;
			while (line != null) {
				if ( l == 0) { // pulando a 1 linha que nao interessa por ser o header.
					l++;
				}
				else {
					line = line.replace("|", "#");
					String reg[] = line.split("#");
					Integer id	=	Integer.parseInt(reg[0]);
					String name = 	reg[1];
					
					Cnae obj = new Cnae(id,name);
					cnaes.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		repo.saveAll(cnaes);
	}
}
