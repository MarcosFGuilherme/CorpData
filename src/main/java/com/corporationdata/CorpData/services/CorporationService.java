package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.Corporation;
import com.corporationdata.CorpData.domain.LegalNature;
import com.corporationdata.CorpData.domain.enums.Mei;
import com.corporationdata.CorpData.domain.enums.Simple;
import com.corporationdata.CorpData.domain.enums.Size;
import com.corporationdata.CorpData.domain.enums.Status;
import com.corporationdata.CorpData.repositories.CorporationRepository;
import com.corporationdata.CorpData.services.exception.ObjectNotFoundException;

@Service
public class CorporationService {
	
	@Autowired
	CorporationRepository repo;
	
	@Autowired
	private CityService	cityService;
	
	@Autowired
	private CnaeService cnaeService;
	
	@Autowired
	private LegalNatureService legalNatureService;
	
	public void initialLoad() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Corporation> corp  = new ArrayList<>(); 
		String path = "F:\\Temp\\csv\\corporation.csv";
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
		
		repo.saveAll(corp);
	}
	
	public Corporation find(String document) {
		Corporation obj = repo.findByDocument(document);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Document: " + document + ", Tipo: " + Corporation.class.getName());
		}
		return obj;
	}
	
	public Page<Corporation> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
}
