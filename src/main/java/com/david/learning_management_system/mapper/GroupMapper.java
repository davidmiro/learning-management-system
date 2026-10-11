package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.GroupCreateDto;
import com.david.learning_management_system.dto.request.GroupUpdateDto;
import com.david.learning_management_system.dto.response.GroupResponseDto;
import com.david.learning_management_system.model.Group;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface GroupMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "courseGroups", ignore = true)
    Group toEntity(GroupCreateDto groupCreateDto);

    @BeanMapping(ignoreUnmappedSourceProperties = {"students", "schedules", "courseGroups"})
    GroupResponseDto toResponse(Group group);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "courseGroups", ignore = true)
    void updateFromDto(@MappingTarget Group group, GroupUpdateDto groupUpdateDto);
}