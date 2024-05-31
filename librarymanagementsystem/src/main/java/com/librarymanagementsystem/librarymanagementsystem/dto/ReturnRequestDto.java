package com.librarymanagementsystem.librarymanagementsystem.dto;

import java.time.LocalDate;

public class ReturnRequestDto {
    private LocalDate submitDate;

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }
}
