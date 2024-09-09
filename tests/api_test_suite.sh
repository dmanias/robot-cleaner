#!/bin/bash

# Valid case
echo "Testing: Valid case"
curl -X POST http://localhost:8080/api/hoover/navigate \
-H "Content-Type: application/json" \
-d '{
    "roomSize": [5, 5],
    "coords": [1, 2],
    "patches": [
        [1, 0],
        [2, 2],
        [2, 3]
    ],
    "instructions": "NNESEESWNWW"
}'
echo -e "\n\n"

# Invalid room size
echo "Testing: Invalid room size"
curl -X POST http://localhost:8080/api/hoover/navigate \
-H "Content-Type: application/json" \
-d '{
    "roomSize": [0, 5],
    "coords": [1, 2],
    "patches": [
        [1, 0]
    ],
    "instructions": "N"
}'
echo -e "\n\n"

# Invalid patch position
echo "Testing: Invalid patch position"
curl -X POST http://localhost:8080/api/hoover/navigate \
-H "Content-Type: application/json" \
-d '{
    "roomSize": [5, 5],
    "coords": [1, 1],
    "patches": [
        [6, 6]
    ],
    "instructions": "N"
}'
echo -e "\n\n"

# No instructions
echo "Testing: No instructions"
curl -X POST http://localhost:8080/api/hoover/navigate \
-H "Content-Type: application/json" \
-d '{
    "roomSize": [5, 5],
    "coords": [2, 2],
    "patches": [
        [1, 1],
        [3, 3]
    ],
    "instructions": ""
}'
echo -e "\n\n"

echo "All tests completed."