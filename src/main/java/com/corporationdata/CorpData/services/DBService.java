package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.Corporation;
import com.corporationdata.CorpData.domain.LegalNature;
import com.corporationdata.CorpData.domain.State;
import com.corporationdata.CorpData.domain.enums.Mei;
import com.corporationdata.CorpData.domain.enums.Simple;
import com.corporationdata.CorpData.domain.enums.Size;
import com.corporationdata.CorpData.domain.enums.Status;
import com.corporationdata.CorpData.repositories.CityRepository;
import com.corporationdata.CorpData.repositories.CnaeRepository;
import com.corporationdata.CorpData.repositories.CorporationRepository;
import com.corporationdata.CorpData.repositories.LegalNatureRepository;
import com.corporationdata.CorpData.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CnaeRepository cnaeRepository;

	@Autowired
	private LegalNatureRepository legalNatureRepository;

	@Autowired
	private CorporationRepository corporationRepository;
	
	@Autowired
	private StateService stateService;

	public void instatiateTestDatabase() throws ParseException {
		
		String path = "";
		
		/*
		 * Inserindo os dados de [States]
		 */
		List<State> states  = new ArrayList<>(); 
		path = "F:\\Temp\\csv\\state.csv";
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
					Integer id	=	Integer.parseInt(reg[0]);
					String uf	= 	reg[1];
					String name = 	reg[2];
					
					
					State obj = new State(id,uf,name);
					states.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		/*
		 * Gravando no banco os dados das classes.
		 */
		stateRepository.saveAll(states);
		/*
		 * Inserindo os dados de [City]
		 */
		List<City> cities  = new ArrayList<>(); 
		path = "F:\\Temp\\csv\\city.csv";
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
		/*
		 * Gravando no banco os dados das classes.
		 */
		cityRepository.saveAll(cities);
		/*
		 * Inserindo os dados de [CNAE]
		 */
		List<Cnae> cnaes  = new ArrayList<>(); 
		path = "F:\\Temp\\csv\\cnae.csv";
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
		/*
		 * Gravando no banco os dados das classes.
		 */
		cnaeRepository.saveAll(cnaes);
		/*
		 * Inserindo os dados de [Legal Nature]
		 */
		List<LegalNature> legalNatures  = new ArrayList<>(); 
		path = "F:\\Temp\\csv\\legalnature.csv";
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
		/*
		 * Gravando no banco os dados das classes.
		 */
		legalNatureRepository.saveAll(legalNatures);
		/*
		 * Inserindo os dados de [Corporation]
		 */
		List<Corporation> corp  = new ArrayList<>(); 
		path = "F:\\Temp\\csv\\corporation.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			int l = 0;
			while (line != null) {
				if ( l == 0) { // pulando a 1 linha que nao interessa por ser o header.
					l++;
				}
				else {
					line = line + ";";
					for (int i=1; i<3;i++) {
						line = line.replace(";;", ";null;");
					}
					System.out.println("Linha:" + line);
					
					
					String reg[] = line.split(";");
					System.out.println("Tamnho do registro:" + reg.length);
					
					String document	=	reg[0];
					String companyName = reg[1];
					String fantasyName = reg[2];
					Status status = null;
					Date statusDate = null;
					Date activeStartDate = null;
					Cnae fiscalCnae = null;
					LegalNature legalNature = null;
					Size sizeCompany = null;
					Double shareCapital = null;
					Simple	simpleOption = null;
					Mei	meiOption = null;
					String typeStreet = reg[14];
					String address = reg[15];
					String neighborhood = reg[16];
					String	zipCode = reg[17];
					City city = null;
					String number = reg[20];
					String complement = reg[21];
					String email = reg[26];
					Corporation obj = new Corporation(document, companyName, fantasyName, status, statusDate, activeStartDate, fiscalCnae, legalNature, sizeCompany, shareCapital, simpleOption, meiOption, typeStreet, address, neighborhood, zipCode, city, number, complement, email);
					corp.add(obj);
				}
				line = br.readLine() ;
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		/*
		 * Gravando no banco os dados das classes.
		 */
		corporationRepository.saveAll(corp);
	}
}
