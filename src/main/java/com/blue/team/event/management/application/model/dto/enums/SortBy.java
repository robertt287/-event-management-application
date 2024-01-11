package com.blue.team.event.management.application.model.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortBy {
    DATE("date"),
    NAME("name");

    String field;

}
