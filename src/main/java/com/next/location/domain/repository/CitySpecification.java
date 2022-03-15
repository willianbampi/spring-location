package com.next.location.domain.repository;

import com.next.location.domain.entity.City;

import org.springframework.data.jpa.domain.Specification;

public class CitySpecification {

    public static Specification<City> nameEqual(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<City> nameLikeLowercase(String name) {
        String param = "%" + name + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), param.toLowerCase());
    }
    
    public static Specification<City> populationGreaterThan(Integer population) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("population"), population);
    }

    public static Specification<City> populationBetween(Integer minPopulation, Integer maxPopulation) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("population"), minPopulation, maxPopulation);
    }

    public static Specification<City> genericProperties(String property, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(property), value);
    }

}
