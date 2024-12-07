/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal.controller;

import ca.nscc.alertnotificaionfinal.dto.*;
import ca.nscc.alertnotificaionfinal.service.SafetyNetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/safetynet") // SafetyNet REST Controller
public class SafetyNetController {

    private static final Logger logger = LoggerFactory.getLogger(SafetyNetController.class);
    private final SafetyNetService service;

    public SafetyNetController(SafetyNetService service) {
        this.service = service;
    }

    @GetMapping("/firestation")
    public ResponseEntity<List<FireStationPersonDTO>> getFireStationPersons(@RequestParam("stationNumber") int stationNumber) {
        logger.info("Request received for firestation endpoint with stationNumber: {}", stationNumber);
        List<FireStationPersonDTO> persons = service.getPersonsByStation(stationNumber);
        logger.info("Response for firestation endpoint: {} records found", persons.size());
        return persons.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(persons);
    }

    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertDTO>> getChildrenByAddress(@RequestParam("address") String address) {
        logger.info("Request received for childAlert endpoint with address: {}", address);
        List<ChildAlertDTO> children = service.getChildrenByAddress(address);
        logger.info("Response for childAlert endpoint: {} records found", children.size());
        return children.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(children);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneNumbersByStation(@RequestParam("stationNumber") int stationNumber) {
        logger.info("Request received for phoneAlert endpoint with stationNumber: {}", stationNumber);
        List<String> phoneNumbers = service.getPhoneNumbersByStation(stationNumber);
        logger.info("Response for phoneAlert endpoint: {} phone numbers found", phoneNumbers.size());
        return phoneNumbers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(phoneNumbers);
    }

    @GetMapping("/fire")
    public ResponseEntity<FireAlertDTO> getFireDetails(@RequestParam("address") String address) {
        logger.info("Request received for fire endpoint with address: {}", address);
        FireAlertDTO fireDetails = service.getFireDetails(address);
        logger.info("Response for fire endpoint: {}", fireDetails != null ? "Data found" : "No data found");
        return fireDetails == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(fireDetails);
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodAlertDTO>> getFloodDetails(@RequestParam("stations") List<Integer> stations) {
        logger.info("Request received for flood endpoint with stations: {}", stations);
        List<FloodAlertDTO> floodDetails = service.getFloodDetails(stations);
        logger.info("Response for flood endpoint: {} households found", floodDetails.size());
        return floodDetails.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(floodDetails);
    }

    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getCommunityEmails(@RequestParam("city") String city) {
        logger.info("Request received for communityEmail endpoint with city: {}", city);
        List<String> emails = service.getCommunityEmails(city);
        logger.info("Response for communityEmail endpoint: {} emails found", emails.size());
        return emails.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(emails);
    }

    @RestController
    public static class TestController {

        @GetMapping("/test")
        public String testEndpoint() {
            return "Success!";
        }
    }
}