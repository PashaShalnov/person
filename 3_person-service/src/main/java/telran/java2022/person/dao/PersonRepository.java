package telran.java2022.person.dao;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.model.Person;
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	@Query(value = "SELECT * FROM persons WHERE city = :city", nativeQuery = true)
	Stream<Person> findPersonsByCity(@Param("city") String str);
	
	@Query(value = "SELECT * FROM persons WHERE name = :name", nativeQuery = true)
	Stream<Person> findPersonsByName(@Param("name") String str);
	
	@Query(value = "SELECT * FROM persons WHERE birth_Date BETWEEN :max AND :min", nativeQuery = true)
	Stream<Person> findPersonsBetweenAges(@Param("min") LocalDate min, @Param("max") LocalDate max);
	
	@Query(value = "SELECT city, COUNT(name) AS population FROM persons GROUP BY city", nativeQuery = true)
	Stream<CityPopulationDto> getCitiesPopulation();
}
