package com.david.learning_management_system.repository;

import com.david.learning_management_system.Exception.GroupNotFoundException;
import com.david.learning_management_system.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    default Group findGroupByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found"));
    }

    default void deleteGroupByIdOrThrow(Long id) {
        Group group = findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + id));
        delete(group);
    }
}