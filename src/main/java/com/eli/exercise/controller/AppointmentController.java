package com.eli.exercise.controller;

import com.eli.exercise.dto.AppointmentRequestBody;
import com.eli.exercise.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eli.exercise.Constants.DATE;
import static com.eli.exercise.Constants.MIN_SCORE;
import static com.eli.exercise.Constants.SPECIALTY;

@RestController
@RequestMapping(value = "/appointments",produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
        MediaType.ALL_VALUE,
        })
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getProviders(@RequestParam() String specialty,
                                     @RequestParam() String date,
                                     @RequestParam() String minScore) {
        Map<String, String> providedFilters = new HashMap<>();

        if(!(specialty.length() > 0)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error"
            );
        }
        Optional.ofNullable(specialty).ifPresent(spec-> providedFilters.put(SPECIALTY, spec));
        Optional.ofNullable(date).ifPresent(dt-> providedFilters.put(DATE, dt));
        Optional.ofNullable(minScore).ifPresent(ms-> providedFilters.put(MIN_SCORE, ms));

        try {
            return appointmentService.getProvidersByFilters(providedFilters);
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error"
            );
        }


    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void checkAppointment(@RequestBody AppointmentRequestBody body) {
        boolean isAvailable = appointmentService.checkAvailability(body);

        if(!isAvailable) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Not available"
            );
        }
    }
}
