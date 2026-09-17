package com.david.learning_management_system.mapper;

import com.david.learning_management_system.dto.request.GroupCreateDto;
import com.david.learning_management_system.dto.request.GroupUpdateDto;
import com.david.learning_management_system.dto.response.GroupResponseDto;
import com.david.learning_management_system.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupCreateDto groupCreateDto);
    GroupResponseDto toResponse(Group group);
    void updateFromDto(@MappingTarget Group group, GroupUpdateDto groupUpdateDto);
}
