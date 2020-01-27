package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public void initialLoad() {
		List<LegalNature> legalNatures  = new ArrayList<>(); 
		String path = "F:\\Temp\\csv\\legalnature.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			int l = 0;
			int n = l;
			while (line != null) {
				n++;
				if ( l == 0) { // pulando a 1 linha que nao interessa por ser o header.
					l++;
				}
				else {
					line = line.replace("|", "#");
//					System.out.println("[" + line + "]");
					String reg[] = line.split("#");
//					System.out.println(reg.length);
					Integer id	=	Integer.parseInt(reg[0]);
					String name = 	reg[1];
					
					LegalNature obj = new LegalNature(id,name);
					legalNatures.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		repo.saveAll(legalNatures);
	}
}
