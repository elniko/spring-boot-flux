package flux.test.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mykolacherviatsov on 03/12/2017.
 */
@Data
@Document(collection = "persons")
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
}
