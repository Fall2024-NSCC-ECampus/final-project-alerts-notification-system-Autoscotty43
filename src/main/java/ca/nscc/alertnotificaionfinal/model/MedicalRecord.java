/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "medical_record") // Specifies the table name in the database
public class MedicalRecord {
    @Id
    private String personId;
    private int age;

    @ElementCollection
    private List<String> medications;

    @ElementCollection
    private List<String> allergies;

}