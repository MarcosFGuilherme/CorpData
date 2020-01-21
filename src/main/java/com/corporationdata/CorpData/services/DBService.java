package com.corporationdata.CorpData.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	public void instatiateTestDatabase() throws ParseException {
		/*
		 * Inserindo os dados de [States]
		 */
		State s1 = new State(null, "RS");
		State s2 = new State(null, "SP");
		/*
		 * Inserindo os dados de [City]
		 */
		City c1 = new City(null, "Santa Maria", s1);
		City c2 = new City(null, "Piracicaba", s2);
		/*
		 * Inserindo a associacao de [Estado] com [Cidade].
		 */
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2));
		/*
		 * Gravando no banco os dados das classes.
		 */
		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2));
		/*
		 * Inserindo os dados de [CNAE]
		 */
		Cnae cn1 = new Cnae(2599399, "Fabricação de outros produtos de metal não especificados anteriormente");
		Cnae cn2 = new Cnae(2610800, "Fabricação de componentes eletrônicos");
		Cnae cn3 = new Cnae(3832700, "Recuperação de materiais plásticos");
		Cnae cn4 = new Cnae(4520007,
				"Serviços de instalação, manutenção e reparação de acessórios para veículos automotores");
		Cnae cn5 = new Cnae(4530703, "Comércio a varejo de peças e acessórios novos para veículos automotores");
		Cnae cn6 = new Cnae(4617600,
				"Representantes comerciais e agentes do comércio de produtos alimentícios, bebidas e fumo");
		Cnae cn7 = new Cnae(4637107, "Comércio atacadista de chocolates, confeitos, balas, bombons e semelhantes");
		Cnae cn8 = new Cnae(4686902, "Comércio atacadista de embalagens");
		Cnae cn9 = new Cnae(4712100,
				"Comércio varejista de mercadorias em geral, com predominância de produtos alimentícios - minimercados, mercearias e armazéns");
		Cnae cn10 = new Cnae(4731800, "Comércio varejista de combustíveis para veículos automotores");
		Cnae cn11 = new Cnae(4771701, "Comércio varejista de produtos farmacêuticos, sem manipulação de fórmulas");
		Cnae cn12 = new Cnae(4773300, "Comércio varejista de artigos médicos e ortopédicos");
		Cnae cn13 = new Cnae(4781400, "Comércio varejista de artigos do vestuário e acessórios");
		Cnae cn14 = new Cnae(4930203, "Transporte rodoviário de produtos perigosos");
		Cnae cn15 = new Cnae(5612100, "Serviços ambulantes de alimentação");
		Cnae cn16 = new Cnae(7112000, "Serviços de engenharia");
		Cnae cn17 = new Cnae(8640205,
				"Serviços de diagnóstico por imagem com uso de radiação ionizante, exceto tomografia");
		/*
		 * Gravando no banco os dados das classes.
		 */
		cnaeRepository.saveAll(Arrays.asList(cn1, cn2, cn3, cn4, cn5, cn6, cn7, cn8, cn9, cn10, cn11, cn12, cn13, cn14,
				cn15, cn16, cn17));
		/*
		 * Inserindo os dados de [Legal Nature]
		 */
		LegalNature ln1 = new LegalNature(2062, "Sociedade Empresária Limitada");
		LegalNature ln2 = new LegalNature(2135, "Empresário Individual");
		LegalNature ln3 = new LegalNature(2240, "Sociedade Simples Limitada");
		LegalNature ln4 = new LegalNature(2305,
				"Empresa Individual de Responsabilidade Limitada (de Natureza Empresária");
		/*
		 * Gravando no banco os dados das classes.
		 */
		legalNatureRepository.saveAll(Arrays.asList(ln1, ln2, ln3, ln4));
		/*
		 * Inserindo os dados de [Corporation]
		 */
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
