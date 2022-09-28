package com.elcom.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Admin
 */
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class CountCategory {

    private String category;
    private Integer numBook;
}