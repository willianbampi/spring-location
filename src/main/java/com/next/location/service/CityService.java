package com.next.location.service;

import java.util.ArrayList;
import java.util.List;

import com.next.location.domain.entity.City;
import com.next.location.domain.repository.CityRepository;
import com.next.location.domain.repository.CitySpecification;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void listCitiesByIdProjectionNativeQuery() {
        cityRepository.findByIdProjectionNativeQuery(5).stream()
                        .map(cityProjection -> new City(null, cityProjection.getName(), cityProjection.getPopulation()))
                        .forEach(city -> {
                            System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
                        });
    }


    public void listCitiesByIdNativeQuery() {
        cityRepository.findByIdNativeQuery(4).stream().forEach(System.out::println);
    }

    public void listCitiesByNameNativeQuery() {
        cityRepository.findByNameNativeQuery("Porto Belo").stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
    }

    public void listCitiesByConjunction() {
        Specification<City> specification = Specification.where(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByNameLikeLowercaseSpecification() {
        Specification<City> specification = CitySpecification.nameLikeLowercase("LoNDrina");
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByPopulationBetweenSpecification() {
        Specification<City> specification = CitySpecification.populationBetween(35, 90);
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByGenericPropertiesSpecification() {
        Specification<City> specification = CitySpecification.genericProperties("name", "Piracicaba").or(CitySpecification.genericProperties("id", 2));
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByNameAndPopulationSpecification() {
        Specification<City> specification = CitySpecification.nameEqual("Piracicaba").or(CitySpecification.populationGreaterThan(20));
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByPopulationSpecification() {
        Specification<City> specification = CitySpecification.populationGreaterThan(20);
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void listCitiesByNameSpecification() {
        Specification<City> specification = CitySpecification.nameEqual("Piracicaba");
        cityRepository.findAll(specification).stream().forEach(System.out::println);
    }

    public void dynamicFilterByExampleMatcher(City param) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<City> example = Example.of(param, matcher);
        cityRepository.findAll(example).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
    }

    public void defaultDynamicFilterByExample(City param) {
        Example<City> example = Example.of(param);
        cityRepository.findAll(example).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
    }

    public void listCitiesByPopulationGreaterThanEqual(Integer population) {
		cityRepository.findByPopulationGreaterThanEqual(population).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByPopulationLessThanEqual(Integer population) {
		cityRepository.findByPopulationLessThanEqual(population).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByPopulationGreaterThan(Integer population) {
		cityRepository.findByPopulationGreaterThan(population).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByPopulationLessThan(Integer population) {
		cityRepository.findByPopulationLessThan(population).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

    public void listCitiesByNameLikeLowercasePageable(String name) {
		String param = "%" + name + "%";
        Pageable pageable = PageRequest.of(0, 2);
		cityRepository.findByNameLikeLowercasePageable(param, pageable).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

    public void listCitiesByNameLikeLowercaseSorted(String name) {
		String param = "%" + name + "%";
		cityRepository.findByNameLikeLowercaseSorted(param, Sort.by("population")).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByNameLikeLowercase(String name) {
		String param = "%" + name + "%";
		cityRepository.findByNameLikeLowercase(param).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesLike(String name) {
		String param = "%" + name + "%";
		cityRepository.findByNameLike(param).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesContaining(String name) {
		cityRepository.findByNameContaining(name).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesEndingWith(String name) {
		cityRepository.findByNameEndingWith(name).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesStartsWith(String name) {
		cityRepository.findByNameStartsWith(name).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByPopulation(Integer population) {
		cityRepository.findByPopulation(population).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listCitiesByName(String name) {
		cityRepository.findByName(name).stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	public void listAllCities() {
		cityRepository.findAll().stream().forEach(city -> {
			System.out.println(city.getId() + " " + city.getName() + " " + city.getPopulation());
		});
	}

	@Transactional
	public void save() {
		City city1 = new City(1, "São Paulo", 100);
		City city2 = new City(2, "Porto Alefre", 50);
		City city3 = new City(3, "Florianópolis", 65);
		City city4 = new City(4, "Curitiba", 35);
		City city5 = new City(5, "Rio de Janeiro", 80);
		City city6 = new City(6, "Porto Belo", 10);
		City city7 = new City(7, "Londrina", 15);
		City city8 = new City(8, "Piracicaba", 25);
		City city9 = new City(9, "Dois Irmãos", 78);
		City city10 = new City(10, "Passo Fundo", 38);

		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		cities.add(city4);
		cities.add(city5);
		cities.add(city6);
		cities.add(city7);
		cities.add(city8);
		cities.add(city9);
		cities.add(city10);

		cityRepository.saveAll(cities);
	}
    
}
