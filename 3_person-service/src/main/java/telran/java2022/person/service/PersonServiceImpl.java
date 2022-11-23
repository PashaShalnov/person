package telran.java2022.person.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.person.dao.PersonRepository;
import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.dto.PersonNotFoundException;
import telran.java2022.person.model.Address;
import telran.java2022.person.model.Person;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	
	@Override
	public boolean addPeson(PersonDto personDto) {
		if (personDto == null) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonAdress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(new Address(addressDto.getCity(), addressDto.getStreet(), addressDto.getBuilding()));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}
	
	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findPersonsByCity(city)
		 				.map(p -> modelMapper.map(p, PersonDto.class))
		 				.toList();	 //есть ли недостатки по сравнению с .collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByName(name)
 				.map(p -> modelMapper.map(p, PersonDto.class))
 				.toList();
	}

	@Override
	public Iterable<PersonDto> findPersonsBetweenAges(Integer minAge, Integer maxAge) {
		LocalDate min = LocalDate.now().minusYears(minAge);
		LocalDate max = LocalDate.now().minusYears(maxAge);
		return personRepository.findPersonsBetweenAges(min, max)
				.map(p -> modelMapper.map(p, PersonDto.class))
 				.toList();
	}

	@Override
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		return personRepository.getCitiesPopulation()
				.map(cp -> modelMapper.map(cp, CityPopulationDto.class))
 				.toList();
	}

}
