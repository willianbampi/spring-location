package com.next.location;

import com.next.location.domain.entity.City;
import com.next.location.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LocationApplication implements CommandLineRunner {

	@Autowired
	private CityService cityService;

	@Override
	public void run(String... args) throws Exception {
		cityService.listAllCities();
		
		cityService.listCitiesByName("Porto Alegre");
		cityService.listCitiesStartsWith("S");
		cityService.listCitiesEndingWith("o");
		cityService.listCitiesContaining("sso");
		cityService.listCitiesLike("ois");
		cityService.listCitiesByNameLikeLowercase("Na");
		cityService.listCitiesByNameLikeLowercaseSorted("a");
		cityService.listCitiesByNameLikeLowercasePageable("a");

		cityService.listCitiesByPopulation(35);
		cityService.listCitiesByPopulationLessThan(50);
		cityService.listCitiesByPopulationGreaterThan(60);
		cityService.listCitiesByPopulationLessThanEqual(50);
		cityService.listCitiesByPopulationGreaterThanEqual(60);

		City cityExample1 = new City(null, "Curitiba", null);
		City cityExample2 = new City(null, null, 80);
		City cityExample3 = new City(8, null, null);
		City cityExample4 = new City(6, "Porto Belo", null);
		City cityExample5 = new City(10, "Passo Fundo", 38);
		cityService.defaultDynamicFilterByExample(cityExample1);
		cityService.defaultDynamicFilterByExample(cityExample2);
		cityService.defaultDynamicFilterByExample(cityExample3);
		cityService.defaultDynamicFilterByExample(cityExample4);
		cityService.defaultDynamicFilterByExample(cityExample5);

		City cityExampleMatcher = new City(10, "paSSo Fundo", 38);
		cityService.dynamicFilterByExampleMatcher(cityExampleMatcher);

		cityService.listCitiesByNameSpecification();
		cityService.listCitiesByPopulationSpecification();
		cityService.listCitiesByNameAndPopulationSpecification();
		cityService.listCitiesByGenericPropertiesSpecification();
		cityService.listCitiesByNameLikeLowercaseSpecification();
		cityService.listCitiesByPopulationBetweenSpecification();
		cityService.listCitiesByConjunction();

		cityService.listCitiesByNameNativeQuery();
		cityService.listCitiesByIdNativeQuery();
		cityService.listCitiesByIdProjectionNativeQuery();
	}

	public static void main(String[] args) {
		SpringApplication.run(LocationApplication.class, args);
	}

}
