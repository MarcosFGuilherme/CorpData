package com.corporationdata.CorpData.services;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DBService {

	@Autowired
	private CorporationService corporationService;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private FilterService filterService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CnaeService cnaeService;
	
	@Autowired
	private LegalNatureService legalNatureService;
	
	@Autowired
	private CityService	cityService;

	public void instatiateTestDatabase() throws ParseException {
		
		
		/*
		 * Inserindo os dados de [States]
		 */
		stateService.initialLoad();
		/*
		 * Inserindo os dados de [City]
		 */
		cityService.initialLoad();
		/*
		 * Inserindo os dados de [CNAE]
		 */
		cnaeService.initialLoad();
		/*
		 * Inserindo os dados de [Legal Nature]
		 */
		legalNatureService.initialLoad();
		/*
		 * Inserindo os dados de [Corporation]
		 */
		corporationService.initialLoad();
		/*
		 * Inserindo os dados de [Plans]
		 */
		planService.initialLoad();
		/*
		 * Inserindo os dados de [Filter]
		 */
//		filterService.initialLoad();
	}
}
