package com.eli.exercise.repo.impl;

import com.eli.exercise.dto.ProviderDto;
import com.eli.exercise.repo.IProvidersRepo;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProvidersRepo implements IProvidersRepo {
    private static final String PATH = "./providers/providers.json";

    List<ProviderDto> providedDtosAsList = new ArrayList<>();


    @PostConstruct
    public void setup() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(PATH));
        ProviderDto[] providerDtos = gson.fromJson(br, ProviderDto[].class);
        providedDtosAsList = Arrays.asList(providerDtos);
    }

    public List<ProviderDto> getProviders() {
        return providedDtosAsList;
    }
}
