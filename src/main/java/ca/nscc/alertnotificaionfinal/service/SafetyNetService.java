/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.service;

import ca.nscc.alertnotificaionfinal.dto.ChildAlertDTO;
import ca.nscc.alertnotificaionfinal.dto.FireAlertDTO;
import ca.nscc.alertnotificaionfinal.dto.FireStationPersonDTO;
import ca.nscc.alertnotificaionfinal.dto.FloodAlertDTO;
import ca.nscc.alertnotificaionfinal.exception.ResourceNotFoundException;
import ca.nscc.alertnotificaionfinal.model.FireStation;
import ca.nscc.alertnotificaionfinal.model.MedicalRecord;
import ca.nscc.alertnotificaionfinal.model.Person;
import ca.nscc.alertnotificaionfinal.repository.FireStationRepository;
import ca.nscc.alertnotificaionfinal.repository.MedicalRecordRepository;
import ca.nscc.alertnotificaionfinal.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SafetyNetService {

    private static final Logger logger = LoggerFactory.getLogger(SafetyNetService.class);

    private final PersonRepository personRepo;
    private final FireStationRepository fireStationRepo;
    private final MedicalRecordRepository medicalRecordRepo;

    public SafetyNetService(PersonRepository personRepo, FireStationRepository fireStationRepo, MedicalRecordRepository medicalRecordRepo) {
        this.personRepo = personRepo;
        this.fireStationRepo = fireStationRepo;
        this.medicalRecordRepo = medicalRecordRepo;
    }

    /**
     * Get a list of persons covered by a fire station mapped to FireStationPersonDTO.
     */
    public List<FireStationPersonDTO> getPersonsByStation(int stationNumber) {
        logger.info("Fetching persons for fire station number: {}", stationNumber);
        List<FireStation> fireStations = fireStationRepo.findByStationNumber(stationNumber);

        if (fireStations.isEmpty()) {
            String message = "No fire station found for station number: " + stationNumber;
            logger.warn(message);
            throw new ResourceNotFoundException(message);
        }

        List<String> addresses = fireStations.stream()
                .map(FireStation::getAddress)
                .toList();

        List<FireStationPersonDTO> result = personRepo.findAll().stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .map(person -> {
                    FireStationPersonDTO dto = new FireStationPersonDTO();
                    dto.setName(person.getFirstName() + " " + person.getLastName());
                    dto.setPhone(person.getPhone());
                    dto.setAddress(person.getAddress());
                    return dto;
                })
                .collect(Collectors.toList());

        logger.info("Found {} persons for fire station number: {}", result.size(), stationNumber);
        return result;
    }

    /**
     * Get a list of children at a given address mapped to ChildAlertDTO.
     */
    public List<ChildAlertDTO> getChildrenByAddress(String address) {
        logger.info("Fetching children for address: {}", address);
        List<Person> persons = personRepo.findByAddress(address);

        if (persons.isEmpty()) {
            String message = "No persons found for address: " + address;
            logger.warn(message);
            throw new ResourceNotFoundException(message);
        }

        List<ChildAlertDTO> result = persons.stream()
                .filter(person -> {
                    MedicalRecord record = medicalRecordRepo.findById(person.getId()).orElse(null);
                    return record != null && record.getAge() <= 18;
                })
                .map(child -> {
                    MedicalRecord record = medicalRecordRepo.findById(child.getId()).orElse(null);
                    List<String> householdMembers = persons.stream()
                            .filter(p -> !p.getId().equals(child.getId()))
                            .map(p -> p.getFirstName() + " " + p.getLastName())
                            .collect(Collectors.toList());

                    ChildAlertDTO dto = new ChildAlertDTO();
                    dto.setChildName(child.getFirstName() + " " + child.getLastName());
                    dto.setAge(record != null ? record.getAge() : 0); // Safeguard for missing age
                    dto.setHouseholdMembers(householdMembers);
                    return dto;
                })
                .collect(Collectors.toList());

        logger.info("Found {} children for address: {}", result.size(), address);
        return result;
    }

    /**
     * Get a list of phone numbers associated with persons covered by a fire station.
     */
    public List<String> getPhoneNumbersByStation(int stationNumber) {
        logger.info("Fetching phone numbers for fire station number: {}", stationNumber);
        List<FireStation> fireStations = fireStationRepo.findByStationNumber(stationNumber);

        if (fireStations.isEmpty()) {
            String message = "No fire station found for station number: " + stationNumber;
            logger.warn(message);
            throw new ResourceNotFoundException(message);
        }

        List<String> addresses = fireStations.stream()
                .map(FireStation::getAddress)
                .toList();

        List<String> phoneNumbers = addresses.stream()
                .flatMap(address -> personRepo.findByAddress(address).stream())
                .map(Person::getPhone)
                .distinct()
                .collect(Collectors.toList());

        logger.info("Found {} phone numbers for fire station number: {}", phoneNumbers.size(), stationNumber);
        return phoneNumbers;
    }

    /**
     * Get Fire details for a specific address
     */
    public FireAlertDTO getFireDetails(String address) {
        // Dummy logic to be replaced by actual service logic
        logger.info("Fetching fire details for address: {}", address);
        return new FireAlertDTO();
    }

    /**
     * Get Flood details for multiple stations
     */
    public List<FloodAlertDTO> getFloodDetails(List<Integer> stations) {
        // Dummy logic to be replaced by actual service logic
        logger.info("Fetching flood details for stations: {}", stations);
        return List.of(new FloodAlertDTO());
    }

    /**
     * Get Community emails for a specific city
     */
    public List<String> getCommunityEmails(String city) {
        // Dummy logic to be replaced by actual service logic
        logger.info("Fetching community emails for city: {}", city);
        return List.of("example@example.com");
    }
}