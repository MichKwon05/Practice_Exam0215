package com.mich.crudPractice.models.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_history", nullable = false)
    private String nameHistory;

    @Column(name = "status")
    private Boolean status;

}
