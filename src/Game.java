public class Game {
    public int currentTurn = 1;
    public int winPlayer = 0;
    public String playerSymbol;
    int skipCheck = 0; // Error? -> 0 or 1
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

            if(currentTurn == 1) playerSymbol = "X";
            else playerSymbol = "O";

            for(int i = 0; i < 7; i++){
                try{
                    if(gameField[i][playerInput - 1] == "*") {// ist der Platz noch frei?
                        continue;
                    }else if(gameField[i][playerInput - 1] == "X" || gameField[i][playerInput - 1] == "O"){// ist da schon ein Symbol? -> eine Zeile hoeher
                        gameField[i - 1][playerInput - 1] = playerSymbol;
                        skipCheck = 0;
                        break;
                    }else{
                        gameField[i - 1][playerInput - 1] = playerSymbol;
                        skipCheck = 0;
                    }
                }catch(ArrayIndexOutOfBoundsException e) {//eine Spalte ist voll?
                    skipCheck = 1;
                    return "The column is overfilled!! [" + ((currentTurn == 1) ? "X" : "O") + "] can have another turn!";
                }
            }
            return "~~~~~~~~~~~~~~~~~~~~~~~~~";
        }else{
            skipCheck = 1;
            return "Input error. [" + playerSymbol + "] can have another turn!";
        }
    }

    public int checkGame (int playerID){
        if(playerID == 1) playerSymbol = "X";
        else playerSymbol = "O";

        // check the Field horizontally
        currentLine = 0;
        currentColumn = 0;
        while (currentColumn < 4) {
            if (gameField[currentLine][currentColumn] == playerSymbol &&
                gameField[currentLine][currentColumn + 1] == playerSymbol &&
                gameField[currentLine][currentColumn + 2] == playerSymbol &&
                gameField[currentLine][currentColumn + 3] == playerSymbol)
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
            if (gameField[currentLine][currentColumn] == playerSymbol &&
                gameField[currentLine+1][currentColumn] == playerSymbol &&
                gameField[currentLine+2][currentColumn] == playerSymbol &&
                gameField[currentLine+3][currentColumn] == playerSymbol)
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
            if (gameField[currentLine][currentColumn] == playerSymbol &&
                gameField[currentLine+1][currentColumn+1] == playerSymbol &&
                gameField[currentLine+2][currentColumn+2] == playerSymbol &&
                gameField[currentLine+3][currentColumn+3] == playerSymbol)
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
            if (gameField[currentLine][currentColumn] == playerSymbol &&
                gameField[currentLine+1][currentColumn-1] == playerSymbol &&
                gameField[currentLine+2][currentColumn-2] == playerSymbol &&
                gameField[currentLine+3][currentColumn-3] == playerSymbol)
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
