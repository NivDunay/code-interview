package com.eli.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityDto {
    String from;
    String to;

    public AvailabilityDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public AvailabilityDto() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
