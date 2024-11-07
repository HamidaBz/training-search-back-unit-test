package com.memoire.trainingSite.Controller;

import com.memoire.trainingSite.Services.ApplicationService;
import com.memoire.trainingSite.models.Application;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/applications")
public class ApplicationController {
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long applicationId) {
        Optional<Application> application = applicationService.getApplicationById(applicationId);
        return application.map(ap -> new ResponseEntity<>(ap, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application){
        Application savedApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(savedApplication, HttpStatus.OK);
    }

    @PutMapping("/applicantionId")
    public ResponseEntity<Application> updateApplicantion(@PathVariable Long applicantionId,
                                                          @RequestBody Application application){
        Optional<Application> updatedApplication =
                applicationService.updateApplication(applicantionId,application);
        return updatedApplication.map(ap -> new ResponseEntity<>(ap, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/applicantionId")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicantId){
        applicationService.deleteApplication(applicantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Application>> getApplicationsByApplicantUsername(
            @RequestParam(required = false) String applicant_username,
            @RequestParam(required = false) Long position_id){
        List<Application> applications = new ArrayList<>();
        if(applicant_username != null ){
            applications.addAll(applicationService.getApplicationsByApplicantUsername(applicant_username));
        }
        if(position_id != null ){
            applications.addAll(applicationService.getApplicationsByPositionId(position_id));
        }
        if((applicant_username == null)&&(position_id == null)){
            applications.addAll(applicationService.getAllApplications());
        }
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

}
