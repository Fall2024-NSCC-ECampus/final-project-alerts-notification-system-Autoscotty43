/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "person") // Specifies the table name in the database
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private String email;
}