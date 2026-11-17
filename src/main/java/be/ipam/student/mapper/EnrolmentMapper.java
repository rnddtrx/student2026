package be.ipam.student.mapper;

import be.ipam.student.dto.EnrolmentDto;
import be.ipam.student.model.Enrolment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnrolmentMapper {
    Enrolment toEntity(EnrolmentDto enrolmentDto);

    EnrolmentDto toDto(Enrolment enrolment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enrolment partialUpdate(EnrolmentDto enrolmentDto, @MappingTarget Enrolment enrolment);
}