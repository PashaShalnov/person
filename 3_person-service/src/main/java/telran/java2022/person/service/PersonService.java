package telran.java2022.person.service;

import java.util.Map;

import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;

public interface PersonService {
	boolean addPeson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto removePerson(Integer id);

	PersonDto updatePersonName(Integer id, String name);

	PersonDto updatePersonAdress(Integer id, AddressDto addAdressDto);

	Iterable<PersonDto> findPersonsByCity(String city);

	Iterable<PersonDto> findPersonsByName(String name);

	Iterable<PersonDto> findPersonsBetweenAges(Integer minAge, Integer maxAge);
	
	Iterable<CityPopulationDto> getCitiesPopulation();

}
