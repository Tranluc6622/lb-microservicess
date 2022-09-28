package com.elcom.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class CountFirstLetter {

    private String firstLetter;
    private Integer numBook;

}