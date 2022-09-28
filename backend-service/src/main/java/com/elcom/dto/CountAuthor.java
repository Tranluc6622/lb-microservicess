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
public class CountAuthor {

    private String author;

    private Integer numBook;
}