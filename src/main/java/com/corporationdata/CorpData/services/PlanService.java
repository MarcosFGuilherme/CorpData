package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.Plan;
import com.corporationdata.CorpData.repositories.PlanRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class PlanService {

	@Autowired
	private PlanRepository repo;
	
	public Plan find(Integer id) {
		Optional<Plan> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Plan.class.getName()));
	}
	
	public void initialLoad() {
		List<Plan> plans  = new ArrayList<>(); 
		String path = "F:\\Temp\\csv\\plan.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			int l = 0;
			
			while (line != null) {
				
				if ( l == 0) { // pulando a 1 linha que nao interessa por ser o header.
					l++;
				}
				else {
					String reg[] = line.split("#");
					String name = reg[0];
					Integer totalCompanies = Integer.parseInt(reg[1]);
					Double unitPrice = Double.parseDouble(reg[2]);
					
					Plan obj = new Plan(null,name,totalCompanies,unitPrice);
					plans.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		repo.saveAll(plans);
	}
}
