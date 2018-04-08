package flux.test.controller;

import flux.test.repository.Person;
import flux.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlockingController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person")
    public List<Person> getPersonList() {
        ArrayList<Person> result = new ArrayList<>();
        personRepository.findAll().forEach(result::add);
        return result;
    }

}
