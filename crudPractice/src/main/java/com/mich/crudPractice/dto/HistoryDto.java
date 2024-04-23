package com.mich.crudPractice.dto;

import com.mich.crudPractice.models.history.History;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryDto {

    private Long id;
    private String nameHistory;
    private Boolean status;

    public History getHistory(){
        return new History(
                getId(),
                getNameHistory(),
                getStatus()
        );
    }
}
