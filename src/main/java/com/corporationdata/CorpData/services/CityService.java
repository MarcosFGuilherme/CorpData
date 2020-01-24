package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.repositories.CityRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class CityService {

	private static final Logger LOG =  LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityRepository repo;
	
	@Autowired
	private StateService stateService;
	

	public List<City> findAll() {
		return repo.findAllByOrderByName();
	}

	public City find(Integer id) {
		Optional<City> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + City.class.getName()));
	}
	
	public City findByNameAndUf(String name, String uf) {
		List<City> obj = repo.findByName(name);
		City city = null;
		for (City c : obj) {
			if ( c.getstate().getUf().equals(uf)) {
				city = c;
			}
		}
		return city;
	}
	
	public void initialLoad() {
		List<City> cities  = new ArrayList<>(); 
		String path = "F:\\Temp\\csv\\city.csv";
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
					line = line + ";";
					for (int i=1; i<3;i++) {
						line = line.replace(";;", ";null;");
					}
					String reg[] = line.split(";");
					Integer id = Integer.parseInt(reg[0]);
					String name = reg[1];
					Double latidude = Double.parseDouble(reg[2]);
					Double longitude = Double.parseDouble(reg[3]);
					Integer capital = Integer.parseInt(reg[4]);
					Integer stateId = Integer.parseInt(reg[5]);
					
					State state = stateService.find(stateId);
					
					City obj = new City(id,name,latidude,longitude,capital,state);
					cities.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		repo.saveAll(cities);
	}
}
