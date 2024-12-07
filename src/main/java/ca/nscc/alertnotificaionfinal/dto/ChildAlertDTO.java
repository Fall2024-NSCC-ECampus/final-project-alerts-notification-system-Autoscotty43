/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.dto;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildAlertDTO {
    private String childName;
    private int age;
    private List<String> householdMembers;
}