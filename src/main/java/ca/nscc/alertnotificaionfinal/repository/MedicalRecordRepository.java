/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.repository;

import ca.nscc.alertnotificaionfinal.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String> {
    MedicalRecord findByPersonId(String personId);
}