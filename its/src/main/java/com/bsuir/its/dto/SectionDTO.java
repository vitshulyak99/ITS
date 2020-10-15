package com.bsuir.its.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    String id;
    String name;
    List<DirectionDTO> directions;

}
