package com.smartf.comu.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FillLogDto {

    private String username;

    private String carNumber;

    private LocalDateTime datetime;

    private Long pumpId;

    private String product;

    private Long amount;
}
