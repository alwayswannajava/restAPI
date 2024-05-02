package com.spring.task2.service;

import com.spring.task2.domain.Group;
import com.spring.task2.dto.GroupSaveDto;

import java.util.List;


public interface GroupService {
    List<Group> findAllGroups();

    Group createGroup(GroupSaveDto groupSaveDto);

    Group updateGroupById(long groupId, GroupSaveDto groupSaveDto);

    void deleteGroupById(long groupId);

    boolean checkDuplicates(long groupId, String groupName);

    boolean isGroupExistByGroupId(long groupId);

    boolean isGroupExistByGroupName(String groupName);
}
