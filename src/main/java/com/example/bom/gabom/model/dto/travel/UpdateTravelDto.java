package com.example.bom.gabom.model.dto.travel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

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
