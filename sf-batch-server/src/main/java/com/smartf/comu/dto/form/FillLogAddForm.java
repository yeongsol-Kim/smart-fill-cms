package com.smartf.comu.dto.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FillLogAddForm {

    private LocalDateTime datetime;

    private Long reservoirId;

    private Long amount;
}
