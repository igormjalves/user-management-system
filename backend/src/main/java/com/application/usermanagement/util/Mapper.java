package com.application.usermanagement.util;

import org.modelmapper.ModelMapper;

public class Mapper {
    private final ModelMapper mapper = new ModelMapper();

    public <T> T map(Object source, Class<T> destinationClass) { return mapper.map(source, destinationClass); }
}
