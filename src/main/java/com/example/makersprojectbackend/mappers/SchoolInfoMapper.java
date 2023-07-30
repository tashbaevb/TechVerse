package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.SchoolInfoDto;
import com.example.makersprojectbackend.entity.SchoolInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SchoolInfoMapper {
    private final ModelMapper mapper;

    public SchoolInfoDto convertToDto(SchoolInfo schoolInfo) {
        return mapper.map(schoolInfo, SchoolInfoDto.class);
    }


    public List<SchoolInfoDto> convertToDtoList(List<SchoolInfo> schoolInfo) {
        List<SchoolInfoDto> schoolInfoDtoList = new ArrayList<>();
        for (SchoolInfo s : schoolInfo) {
            schoolInfoDtoList.add(mapper.map(s, SchoolInfoDto.class));
        }
        return schoolInfoDtoList;
    }

    public SchoolInfo convertToEntity(SchoolInfoDto schoolInfoDto) {
        return mapper.map(schoolInfoDto, SchoolInfo.class);
    }

    public SchoolInfoDto convertToDTO(SchoolInfo schoolInfo) {
        return mapper.map(schoolInfo, SchoolInfoDto.class);
    }
}
