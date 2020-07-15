package ru.shaplov.spring.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemAttrEntity {
    private Long id;
    private String name;
    private ActionIndicatorEnum actionInd;
}
