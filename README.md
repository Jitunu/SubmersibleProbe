# Overview

This is a Spring Boot REST API application that simulates the movement of a submersible probe on a grid. The probe can be initialized at a specific position, execute a sequence of commands(F - Forward, B - Backward, L - Left, R - Right), and provide a summary of its current state and visited positions.

# Technologies Used

- Java 17
- Spring Boot
- Maven
- Lombok
- SLF4J for logging

# Features

1. **Initialize the probe:** Set the probe's starting position, direction, ocean dimension and obstacles.
2. **Execute Commands:** Move the probe forward (`F`), backward (`B`), turn left (`L`), turn right (`R`).
3. **Get Probe Summary:** Retrieve the probe's current position, direction and list of visited positions.

# API Endpoints

## 1. Initialize Probe

**Endpoint:** `POST /initialize`

**Description:** Initializes the probe at a given position with specified grid dimensions and obstacles.

**Request Body:**
```JSON
{
  "x": 0,
  "y": 0,
  "direction": "NORTH",
  "gridWidth": 10,
  "gridHeight": 10,
  "obstacles": [
    {"x": 2, "y": 2},
    {"x": 3, "y": 3}
  ]
}
```
- `x`, `y`: Starting position of the probe.

- `direction`: Initial direction (`NORTH`, `EAST`, `SOUTH`, `WEST`).

- `gridWidth`, `gridHeight`: Dimensions of the grid.

- `obstacles`: List of positions where the probe cannot move.

**Response:** `200 OK` - "Probe initialized successfully."

## 2. Execute Commands

**Endpoint:** `POST /commands?commands=FFLBR`

**Description:** Moves the probe based on input commands (`F` - Forward, `B` - Backward, `L` - Left, `R` - Right).
**Response:** `200 OK` - "Commands executed successfully."

## 3. Get Probe Summary

**Endpoint:** `GET /summary`

**Description:** Retrieves the probe states current position and visited positions of the probe.

**Response Example:**
```
Probe is at Position{x=5, y=5} facing EAST. Visited positions: [Position{x=5, y=3}, Position{x=5, y=4}, Position{x=5, y=5}]
```
## How to Run

1. Clone the repository:
```bash
  git clone https://github.com/your-username/submersible-probe.git
```

2. Navigate to the project directory:
```bash
  cd SubmersibleProbe
```
3. Build the project
```bash
  mvn clean install
```
4. Run the application:
```bash
  mvn spring-boot:run
```

## Note:
**Default Initiation of probe:** 
```
x = 0, y = 0, direction = Direction.NORTH, gridWidth = 10, gridHeight = 10, obstacles = new ArrayList<>() 
```