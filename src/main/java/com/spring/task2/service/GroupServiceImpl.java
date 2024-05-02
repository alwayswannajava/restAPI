package com.spring.task2.service;

import com.spring.task2.domain.Group;
import com.spring.task2.dto.GroupSaveDto;
import com.spring.task2.exception.GroupExistsException;
import com.spring.task2.exception.GroupNotFoundException;
import com.spring.task2.mapper.GroupMapper;
import com.spring.task2.repository.GroupRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findAllGroups() {
        log.info("Service level. Finding all groups");
        return groupRepository.findAll();
    }

    @Override
    public Group createGroup(GroupSaveDto groupSaveDto) {
        log.info("Service level. Creating group");
        long groupId = groupSaveDto.getGroupId();
        String groupName = groupSaveDto.getGroupName();
        checkDuplicates(groupId, groupName);
        Group group = GroupMapper.mapGroupSaveDtoToGroup(groupSaveDto);
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroupById(long groupId, GroupSaveDto groupSaveDto) {
        log.info("Service level. Updating group by id: {}", groupSaveDto.getGroupId());
        isGroupExistByGroupId(groupId);
        Group group = groupRepository.findById(groupId).get();
        group.setGroupName(groupSaveDto.getGroupName());
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroupById(long groupId) {
        log.info("Service level. Deleting group by id: {}", groupId);
        isGroupExistByGroupId(groupId);
        groupRepository.deleteById(groupId);
    }

    @Override
    public boolean checkDuplicates(long groupId, String groupName) {
        log.info("Service level. Check if group exist by groupName: {}", groupName);
        if (groupRepository.existsByGroupName(groupName)) {
            throw new GroupExistsException("Group with name: " + groupName + " already exists");
        } else if (groupRepository.existsById(groupId)) {
            throw new GroupExistsException("Group with id: " + groupId + " already exists");
        }
        return false;
    }

    @Override
    public boolean isGroupExistByGroupId(long groupId) {
        if (groupRepository.existsById(groupId)) {
            return true;
        } else {
            throw new GroupNotFoundException("Group with id: " + groupId + " not found");
        }
    }

    @Override
    public boolean isGroupExistByGroupName(String groupName) {
        if (groupRepository.existsByGroupName(groupName)) {
            return true;
        } else {
            throw new GroupNotFoundException("Group with name: " + groupName + " not found");
        }
    }

}
