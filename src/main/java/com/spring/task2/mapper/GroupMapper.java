package com.spring.task2.mapper;

import com.spring.task2.domain.Group;
import com.spring.task2.dto.GroupSaveDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GroupMapper {

    public static Group mapGroupSaveDtoToGroup(GroupSaveDto groupSaveDto) {
        log.info("Mapping groupSaveDto to Group");
        Group group = new Group();
        group.setGroupId(groupSaveDto.getGroupId());
        group.setGroupName(groupSaveDto.getGroupName());
        return group;
    }
}
