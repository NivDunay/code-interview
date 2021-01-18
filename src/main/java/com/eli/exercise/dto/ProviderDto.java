package com.eli.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDto {
    String name;
    List<String> specialties;
    List<AvailabilityDto> availableDates;
    String score;


    public ProviderDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<String> specialties) {
        this.specialties = specialties;
    }

    public List<AvailabilityDto> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<AvailabilityDto> availableDates) {
        this.availableDates = availableDates;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
