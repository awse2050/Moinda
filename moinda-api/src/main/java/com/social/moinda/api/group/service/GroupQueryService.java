package com.social.moinda.api.group.service;

import com.social.moinda.core.domains.group.dto.GroupDto;
import com.social.moinda.core.domains.group.entity.GroupQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {

    private final GroupQueryRepository groupQueryRepository;

    public List<GroupDto> searchGroups() {
        List<GroupDto> dtoList = groupQueryRepository.findGroupAll();
        return dtoList;
    }

    public List<GroupDto> searchGroups(String search) {
        List<GroupDto> dtoList = groupQueryRepository.findAllByNameContains(search);
        return dtoList;
    }
}
