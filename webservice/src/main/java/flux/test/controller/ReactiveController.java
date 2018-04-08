package flux.test.controller;

import flux.test.repository.Person;
import flux.test.repository.ReactivePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class ReactiveController {
    @Autowired
    ReactivePersonRepository personRepository;

    @GetMapping(value = "/personReactiveStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Person> getPersonsStream() {
        return personRepository.findAll().delayElements(Duration.ofMillis(500));
    }

    @GetMapping(value = "/personReactive")
    Flux<Person> getPersons() {
        return personRepository.findAll().delayElements(Duration.ofMillis(500));
    }
}
