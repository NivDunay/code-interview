package com.eli.exercise.repo;

import com.eli.exercise.dto.ProviderDto;

import java.util.List;

public interface IProvidersRepo {
    List<ProviderDto> getProviders();
}
