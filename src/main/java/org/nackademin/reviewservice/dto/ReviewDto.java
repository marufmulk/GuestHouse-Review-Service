package org.nackademin.reviewservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;

    @NotNull(message = "Kund-id saknas")
    private Long customerId;

    @NotNull(message = "Rum-id saknas")
    private Long roomId;

    @Min(value = 1, message = "Betyg måste vara minst 1")
    @Max(value = 5, message = "Betyg får vara max 5")
    @NotNull(message = "Betyg saknas")
    private Integer rating;

    @NotBlank(message = "Kommentar är obligatorisk")
    private String comment;
}
