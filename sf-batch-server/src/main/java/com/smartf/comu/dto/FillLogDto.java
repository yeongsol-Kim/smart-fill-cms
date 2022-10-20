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

    private Long branchId;

    private Long pumpId;

    private Long reservoirId;


    private String product;

    private Long amount;
}
