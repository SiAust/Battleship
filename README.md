## Battleship
A command line interface which simulates a game of Battleship.
Written in Java.

### Rules
Use the grid to place your Battleship using the coordinates, 
two points between which it will be positioned.
The ships must not overlap, and must not be placed beside
each other.

Aircraft Carrier - 5 cells  
Battleship - 4 cells  
Submarine - 3 cells   
Cruiser - 3 cells   
Submarine - 2 cells

Both players take turns to place their ships.
Once all the ships have been placed on the battlefield
the game starts! Enter a point to fire a shot, the result
will be reflected on the battlefield. Players take turns
to shoot, with the game showing the appropriate field for
the current player and an obscured field of their 
opponent above that.

The game continues until one player sinks all the enemy ships.

### To run

Clone this repository, in your favourite terminal `cd battleship`,
and execute `java -jar out/artifacts/battleship_jar/battleship.jar
`
