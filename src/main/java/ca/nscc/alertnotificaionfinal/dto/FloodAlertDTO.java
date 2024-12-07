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
public class FloodAlertDTO {
    private String address;
    private List<String> affectedPersons;  // List of affected persons by name
    private String floodSeverity;  // Severity of the flood (e.g., "high", "medium", "low")
}