package Service;

import DAO.PersonDao;
import DTO.PersonDTO;
import Model.Person;

import java.util.ArrayList;

public class PersonService {
    private final PersonDao dao;

    public PersonService() {
        dao = new PersonDao();
    }

    public Person convertToPerson(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setColor(dto.getColor());
        person.setRetired(dto.getRetired());
        return person;
    }

    public PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setColor(person.getColor());
        dto.setRetired(person.getRetired());
        return dto;
    }

    public PersonDTO getPersonByName(String name) {
        Person person = dao.get(name);
        return convertToDTO(person);
    }

    public PersonDTO getPersonByID(String ID) {
        Person person = dao.getByID(ID);
        return convertToDTO(person);
    }

    public void addPerson(PersonDTO dto) {
        Person person = convertToPerson(dto);
        dao.add(person);
    }

    public ArrayList<PersonDTO> getAllPeople() {
        ArrayList<Person> people = dao.getAll();
        ArrayList<PersonDTO> peopleDTOS = new ArrayList<>();
        for (Person person :
                people) {
            peopleDTOS.add(convertToDTO(person));
        }
        return peopleDTOS;
    }

    public ArrayList<String> getAllPeopleName() {
        return dao.getAllName();
    }

    public ArrayList<String> getDoingPeopleIdName() {
        return dao.getDoingIdName();
    }

    public ArrayList<PersonDTO> getRetiredPeople(int check) {
        ArrayList<Person> people = dao.getRetiredPerson(check);
        ArrayList<PersonDTO> peopleDTOS = new ArrayList<>();
        for (Person person :
                people) {
            peopleDTOS.add(convertToDTO(person));
        }
        return peopleDTOS;
    }

    public void updatePerson(PersonDTO dto) {
        dao.update(convertToPerson(dto));
    }

    public void deletePerson(PersonDTO dto) {
        dao.delete(convertToPerson(dto));
    }

}
