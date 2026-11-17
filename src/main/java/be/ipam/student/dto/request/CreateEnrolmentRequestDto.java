package be.ipam.student.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateEnrolmentRequestDto {
    @NotNull
    UUID studentPublicId;
    @NotNull
    UUID coursePublicId;
}
