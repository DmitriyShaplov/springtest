package ru.shaplov.spring.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemAttrFilterInfo extends BaseFilterInfo {
    private SortOrder sortOrder;
    private SystemAttrSortBy sortBy;

    public enum SystemAttrSortBy {
        id,
        name,
        actionInd
    }
}
