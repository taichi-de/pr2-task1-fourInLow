public class Game {
    public int currentTurn = 1;
    public int winPlayer = 0;
    public String playerMove;
    int skipCheck = 0;
    int currentLine;
    int currentColumn;

    public String[][] gameField = {
            { "*", "*", "*", "*", "*", "*", "*" },
            { "*", "*", "*", "*", "*", "*", "*" },
            { "*", "*", "*", "*", "*", "*", "*" },
            { "*", "*", "*", "*", "*", "*", "*" },
            { "*", "*", "*", "*", "*", "*", "*" },
            { "*", "*", "*", "*", "*", "*", "*" },
            { "1", "2", "3", "4", "5", "6", "7" }
    };

    public String[][] getField(){
        return gameField;
    }

    public String makeMove(int playerInput){
        if(playerInput < 8){
            if(currentTurn == 1) playerMove = "X";
            else if(currentTurn == 2) playerMove = "O";
            else playerMove = "?";
            for(int i = 0; i < 7; i++) {
                try {
                    if (gameField[i][playerInput - 1] == "*") { // 5
                        continue;
                    }
                    else if (gameField[i][playerInput - 1] == "X" || gameField[i][playerInput - 1] == "O"){
                        gameField[i - 1][playerInput - 1] = playerMove; skipCheck = 0; break;
                    }
                    else {
                        gameField[i - 1][playerInput - 1] = playerMove;
                        skipCheck = 0;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    skipCheck = 1;
                    return "The column is overfilled!! ["+((currentTurn == 1) ? "X" : "O")+"] can have another turn!";
                }
            }
            return "~~~~~~~~~~~~~~~~~~~~~~~~~";
        } else {
            skipCheck = 1;
            return "Input error. ["+playerMove+"] can have another turn!";
        }
    }

    public int checkGame (int playerID){
        if(playerID == 1) playerMove = "X";
        if(playerID == 2) playerMove = "O";

        // check the Field horizontally
        currentLine = 0;
        currentColumn = 0;
            while (currentColumn < 4) {
                if (gameField[currentLine][currentColumn] == playerMove &&
                    gameField[currentLine][currentColumn + 1] == playerMove &&
                    gameField[currentLine][currentColumn + 2] == playerMove &&
                    gameField[currentLine][currentColumn + 3] == playerMove)
                    winPlayer = playerID;
                currentLine++;
                if (currentLine == 6) {
                    currentLine = 0;
                    currentColumn++;
                }
            }

        // check the Field vertically
            currentLine = 0;
            currentColumn = 0;
            while (currentLine < 3) {
                if (gameField[currentLine][currentColumn] == playerMove &&
                    gameField[currentLine+1][currentColumn] == playerMove &&
                    gameField[currentLine+2][currentColumn] == playerMove &&
                    gameField[currentLine+3][currentColumn] == playerMove)
                    winPlayer = playerID;
                currentColumn++;
                if (currentColumn == 7) {
                    currentColumn = 0;
                    currentLine++;
                }
            }

        // check the Field diagonal (> right)
            currentLine = 0;
            currentColumn = 0;
            while (currentLine < 3) {
                if (gameField[currentLine][currentColumn] == playerMove &&
                    gameField[currentLine+1][currentColumn+1] == playerMove &&
                    gameField[currentLine+2][currentColumn+2] == playerMove &&
                    gameField[currentLine+3][currentColumn+3] == playerMove)
                    winPlayer = playerID;
                currentColumn++;
                if (currentColumn == 4) {
                    currentColumn = 0;
                    currentLine++;
                }
            }

        // check the Field diagonal (< left)
            currentLine = 0;
            currentColumn = 6;
            while (currentLine < 3) {
                if (gameField[currentLine][currentColumn] == playerMove &&
                    gameField[currentLine+1][currentColumn-1] == playerMove &&
                    gameField[currentLine+2][currentColumn-2] == playerMove &&
                    gameField[currentLine+3][currentColumn-3] == playerMove)
                    winPlayer = playerID;
                currentColumn--;
                if (currentColumn == 2) {
                    currentColumn = 6;
                    currentLine++;
                }
            }

        if(playerID == 1 && skipCheck != 1) currentTurn = 2;
        else if(playerID == 2 && skipCheck != 1) currentTurn = 1;

        return winPlayer;
    }

}
