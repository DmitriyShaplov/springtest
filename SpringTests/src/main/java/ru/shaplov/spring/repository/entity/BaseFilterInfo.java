package ru.shaplov.spring.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilterInfo {
    private Integer offset;
    private Integer size;
}
