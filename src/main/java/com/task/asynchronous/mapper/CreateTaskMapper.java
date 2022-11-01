package com.task.asynchronous.mapper;

import com.task.asynchronous.dto.CreateTaskDto;
import com.task.asynchronous.model.Task;
import org.mapstruct.Mapping;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;


@org.mapstruct.Mapper(componentModel = "spring")
public interface CreateTaskMapper extends Converter<CreateTaskDto, Task> {

    @Mapping(target = "input", source = "input")
    @Mapping(target = "pattern", source = "pattern")
    Task convert(CreateTaskDto taskCreateDto);
}
