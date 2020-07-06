package tictactoe;

import java.util.Map;

public class Field {
    private String [] cells = new String[9];
    private States state;

    private enum States {NOT_FINISHED, DRAW, X_WINS, O_WINS, IMPOS};
    Map<States, String> dict = Map.of(
            States.NOT_FINISHED, "Game not finished",
            States.DRAW, "Draw",
            States.X_WINS, "X wins",
            States.O_WINS, "O wins",
            States.IMPOS, "Impossible");


    public Field()
    {
        this.state = States.NOT_FINISHED;
        this.setDefaultField();
    }

    private void setDefaultField() {
        int i = 0;
        while (i < cells.length)
        {
            cells[i++] = " ";
        }
    }

    public String getCell(int y, int x)
    {
        int number = (y - 1) * 3 + (x - 1);
        return cells[number];
    }



    public void setCell(int y, int x, String c)
    {

        int number = (y - 1) * 3 + (x - 1);
        cells[number] = c;
        this.setState();
    }

    private void setState()
    {
        if (Math.abs(getCharCount("X") - getCharCount("O")) > 1) {
            this.state = States.IMPOS;
        }
        else if (isWinner("X"))
        {
            if (isWinner("O"))
                this.state = States.IMPOS;
            else
                this.state = States.X_WINS;
        }
        else if (isWinner("O"))
            this.state = States.O_WINS;
        else if (getCharCount(" ") == 0)
            this.state = States.DRAW;
    }

    public void setField(String[] chars)
    {
        cells = chars.clone();
        setState();
    }

    public boolean isRunningState()
    {
        return (state.equals(States.NOT_FINISHED));
    }

    private int getCharCount(String c)
    {
        int counter = 0;
        int i = 0;
        while (i < this.cells.length)
        {
            if (this.cells[i].equals(c))
                counter++;
            i++;
        }
        return counter;
    }

    private boolean isWinner(String c)
    {
        if (getCharCount(c) < 3)
            return false;
        if (c.equals(cells[4])) {
            if (cells[0].equals(cells[4]) && cells[4].equals(cells[8]))
                return true;
            if (cells[2].equals(cells[4]) && cells[4].equals(cells[6]))
                return true;
            if (cells[1].equals(cells[4]) && cells[4].equals(cells[7]))
                return true;
            if (cells[3].equals(cells[4]) && cells[4].equals(cells[5]))
                return true;
        }
        if (c.equals(cells[0])) {
            if (cells[0].equals(cells[1]) && cells[1].equals(cells[2]))
                return true;
            if (cells[0].equals(cells[3]) && cells[3].equals(cells[6]))
                return true;
        }
        if (c.equals(cells[8])) {
            if (cells[8].equals(cells[5]) && cells[5].equals(cells[2]))
                return true;
            if (cells[8].equals(cells[7]) && cells[7].equals(cells[6]))
                return true;
        }
        return false;
    }

    public void printField()
    {
        String dash = "-";
        String line = dash.repeat(9);
        System.out.println(line);
        System.out.printf("| %s %s %s |\n",
                this.getCell(1,1), this.getCell(1,2), this.getCell(1,3));

        System.out.printf("| %s %s %s |\n",
                this.getCell(2,1), this.getCell(2,2), this.getCell(2,3));
        System.out.printf("| %s %s %s |\n",
                this.getCell(3,1), this.getCell(3,2), this.getCell(3,3));

        System.out.println(line);
    }

    public void printState()
    {
        System.out.println(this.dict.get(this.state));
    }
}
