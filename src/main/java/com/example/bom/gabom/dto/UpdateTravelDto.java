package com.example.bom.gabom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTravelDto {

    private Long travelId;
    private String title;
    private String content;

//    @UpdateTimestamp
//    private LocalDateTime updateAt = LocalDateTime.now();
}
