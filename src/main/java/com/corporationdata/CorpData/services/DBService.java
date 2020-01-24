package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Autowired
	private CnaeService cnaeService;
	
	@Autowired
	private LegalNatureService legalNatureService;
	
	@Autowired
	private CityService	cityService;

	public void instatiateTestDatabase() throws ParseException {
		
		String path = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		/*
		 * Inserindo os dados de [States]
		 */
		stateService.initialLoad();
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
					String reg[] = line.split(";");
					String document	=	reg[0];
					String companyName = reg[1];
					String fantasyName = reg[2];
					Status status = Status.toEnum(Integer.parseInt(reg[3]));
					Date statusDate = sdf.parse(reg[4]);
					Date activeStartDate = sdf.parse(reg[5]);
					
					Integer cnaeId = Integer.parseInt(reg[6]);
					Cnae fiscalCnae = cnaeService.find(cnaeId);
					
					Integer legalNatureId = Integer.parseInt(reg[8]);
					LegalNature legalNature = legalNatureService.find(legalNatureId);
					
					Size sizeCompany = Size.toEnum( Integer.parseInt(reg[10]));
					
					Double shareCapital =  Double.parseDouble(reg[11]);
					
					Simple	simpleOption = Simple.toEnum( Integer.parseInt(reg[12]));
					
					Mei	meiOption = Mei.toEnum( Integer.parseInt(reg[13]));
					
					String typeStreet = reg[14];
					String address = reg[15];
					String neighborhood = reg[16];
					String	zipCode = reg[17];
					
					String uf = reg[18];
					String nameCity = reg[19];
					City city = cityService.findByNameAndUf(nameCity, uf);
					System.out.println(city);
					
					String number = reg[20];
					String complement = reg[21];
					String email = reg[26];
					Corporation obj = new Corporation(document, companyName, fantasyName, status, statusDate, activeStartDate, fiscalCnae, legalNature, sizeCompany, shareCapital, simpleOption, meiOption, typeStreet, address, neighborhood, zipCode, city, number, complement, email);
					List<String> ph = new ArrayList<>();
					if (!reg[22].equals("0") ) {
						ph.add(reg[22]+ "-" + reg[23]  );
					}
					if (!reg[24].equals("0") ) {
						ph.add(reg[24]+ "-" + reg[25]);
					}
					obj.getPhones().addAll(ph);
					
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
