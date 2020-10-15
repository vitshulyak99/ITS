package com.bsuir.its.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceSection  {
    String userId;
    List<String> sectionIds;
}
