package com.github.supercodingspring.web.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ContentDto {
    List<AirLineTicketDto> content = new ArrayList<>();
}
