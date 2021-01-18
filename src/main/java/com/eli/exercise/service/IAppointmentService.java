package com.eli.exercise.service;

import com.eli.exercise.dto.AppointmentRequestBody;

import java.util.List;
import java.util.Map;

public interface IAppointmentService {
    List<String> getProvidersByFilters(Map<String, String> filters);
    boolean checkAvailability(AppointmentRequestBody body);
}
