package com.spring.task2.controller;

import com.spring.task2.domain.Group;
import com.spring.task2.dto.GroupSaveDto;
import com.spring.task2.service.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@Log4j2
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups() {
        log.info("Controller level. Finding all groups");
        return groupService.findAllGroups();
    }

    @PostMapping
    public ResponseEntity createGroup(@RequestBody @Valid GroupSaveDto groupSaveDto) {
        log.info("Controller level. Creating group");
        groupService.createGroup(groupSaveDto);
        return ResponseEntity.ok("Group added successfully");
    }

    @PutMapping("/{groupId}")
    public ResponseEntity updateGroup(@PathVariable long groupId, @RequestBody @Valid GroupSaveDto groupSaveDto) {
        log.info("Controller level. Updating group");
        groupService.updateGroupById(groupId, groupSaveDto);
        return ResponseEntity.ok("Group updated successfully");
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity deleteGroup(@PathVariable long groupId) {
        log.info("Controller level. Deleting group");
        groupService.deleteGroupById(groupId);
        return ResponseEntity.ok("Group deleted successfully");
    }
}
