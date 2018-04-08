package flux.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import flux.test.repository.Person;
import flux.test.repository.ReactivePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

/**
 * Created by mykolacherviatsov on 03/12/2017.
 */
@SpringBootApplication
@EnableMongoAuditing
@EnableReactiveMongoRepositories
@EnableMongoRepositories
@ComponentScan(value = {"flux.test.controller", "flux.test.repository"})
public class Main  {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner commandLineRunner(ObjectMapper mapper, ReactivePersonRepository personRepository) {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                ClassLoader classLoader = getClass().getClassLoader();
                JsonNode jsonNode = mapper.readTree(classLoader.getResourceAsStream("users.json"));
                Person[] people = mapper.treeToValue(jsonNode, Person[].class);
                personRepository.saveAll(Arrays.asList(people)).blockLast();
            }
        };
    }
}
