package com.natwest.SubmersibleApplication.dto;

import com.natwest.SubmersibleApplication.model.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProbeResponse {
    private int x;
    private int y;
    private Direction direction;

}
