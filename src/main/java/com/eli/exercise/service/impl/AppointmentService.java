package com.eli.exercise.service.impl;

import com.eli.exercise.dto.AppointmentRequestBody;
import com.eli.exercise.dto.ProviderDto;
import com.eli.exercise.repo.IProvidersRepo;
import com.eli.exercise.repo.impl.ProvidersRepo;
import com.eli.exercise.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eli.exercise.Constants.DATE;
import static com.eli.exercise.Constants.MIN_SCORE;
import static com.eli.exercise.Constants.SPECIALTY;

@Service
public class AppointmentService implements IAppointmentService {
    private final IProvidersRepo providersRepo;

    @Autowired
    public AppointmentService(ProvidersRepo providersRepo) {
        this.providersRepo = providersRepo;
    }

    @Override
    public List<String> getProvidersByFilters(Map<String, String> filters) {

        return providersRepo
                .getProviders()
                .stream()
                .filter(providerDto -> checkFilter(filters, providerDto))
                .sorted((f1, f2) -> Float.compare(Float.parseFloat(f2.getScore()), Float.parseFloat(f1.getScore())))

                .map(ProviderDto::getName)

                .collect(Collectors.toList());

    }

    @Override
    public boolean checkAvailability(AppointmentRequestBody body) {
        return providersRepo.getProviders().stream().anyMatch(provider-> provider.getName().equals(body.getName()) && checkDateAvailability(provider, body.getDate()));

    }

    private boolean checkFilter(Map<String, String> filters, ProviderDto providerDto) {
        String minScore = filters.get(MIN_SCORE);
        String speciality = filters.get(SPECIALTY);
        String availability = filters.get(DATE);
        Set<String> specAsSet = providerDto
                .getSpecialties()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return
        (Float.parseFloat(providerDto.getScore()) >= Float.parseFloat(minScore) ) &&
                specAsSet.contains(speciality.toLowerCase()) &&
                checkDateAvailability(providerDto, availability);
    }

    private boolean checkDateAvailability(ProviderDto providerDto, String availability) {
        return providerDto.getAvailableDates()
                .stream()
                .anyMatch(item-> Long.parseLong(item.getFrom()) <=
                        Long.parseLong(availability) && Long.parseLong(item.getTo()) >= Long.parseLong(availability));
    }
}
