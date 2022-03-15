package com.next.location.domain.repository;

import java.util.List;

import com.next.location.domain.entity.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor {

    List<City> findByName(String name);

    List<City> findByNameStartsWith(String name);

    List<City> findByNameEndingWith(String name);

    List<City> findByNameContaining(String name);

    List<City> findByNameLike(String name);

    @Query("select city from City city where lower(city.name) like lower(:name)")
    List<City> findByNameLikeLowercase(@Param("name") String name);

    @Query("select city from City city where lower(city.name) like lower(:name)")
    List<City> findByNameLikeLowercaseSorted(@Param("name") String name, Sort sort);

    @Query("select city from City city where lower(city.name) like lower(:name)")
    Page<City> findByNameLikeLowercasePageable(@Param("name") String name, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from tb_city as city where city.name = :name")
    List<City> findByNameNativeQuery(@Param("name") String name);

    @Query(nativeQuery = true, value = "select city.name from tb_city as city where city.id = :id")
    List<String> findByIdNativeQuery(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "select city.name as name, city.population as population from tb_city as city where city.id = :id")
    List<CityProjection> findByIdProjectionNativeQuery(@Param("id") Integer id);

    List<City> findByPopulation(Integer population);

    List<City> findByPopulationLessThan(Integer population);

    List<City> findByPopulationGreaterThan(Integer population);

    List<City> findByPopulationLessThanEqual(Integer population);

    List<City> findByPopulationGreaterThanEqual(Integer population);
    
}
