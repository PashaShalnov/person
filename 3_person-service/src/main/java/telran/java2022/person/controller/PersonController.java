package telran.java2022.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController  {
	
	final PersonService personService;
	
	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPeson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}
	
	@GetMapping("/ages/{minAge}/{maxAge}")
	public Iterable<PersonDto> findPersonsByAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
		return personService.findPersonsBetweenAges(minAge, maxAge);
	}
	
	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updatePersonName(id, name);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}
	
	@GetMapping("/population/city")
	public  Iterable<CityPopulationDto> getCityPopulation() {
		return personService.getCitiesPopulation();
	}
	
	@PutMapping("/{id}/address")
	public PersonDto updateAdress(@PathVariable Integer id, @RequestBody AddressDto AdressDto  ) {
		return personService.updatePersonAdress(id, AdressDto);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}
}
