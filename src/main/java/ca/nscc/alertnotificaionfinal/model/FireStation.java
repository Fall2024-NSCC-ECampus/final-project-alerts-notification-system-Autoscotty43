/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;  // utilizing Lombok

@Setter
@Getter
@Entity
@Table(name = "fire_station") // Specifies the table name in the database
public class FireStation {
    @Id
    private String address;
    private int stationNumber;

}