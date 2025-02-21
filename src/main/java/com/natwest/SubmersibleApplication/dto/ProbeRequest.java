package com.natwest.SubmersibleApplication.dto;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProbeRequest {
    private int x;
    private int y;
    private Direction direction;
    private int gridWidth;
    private int gridHeight;
    private Set<Position> obstacles;

}