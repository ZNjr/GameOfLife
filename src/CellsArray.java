import java.awt.*;
import java.util.HashMap;

class CellsArray {
    private HashMap<String, int[]> actualRules;
    private Cell[][] cells;
    private int resizeRate;
    private int maxSize;

    CellsArray(int rows, int cols, HashMap<String, int[]> actualRules) {
        this.cells = new Cell[rows][cols];
        this.actualRules = actualRules;
        this.resizeRate = 0;
        this.maxSize = rows;
        clearBoard();
    }


    Cell[][] getDate() {
        return cells;
    }

    void setActualRules(HashMap<String, int[]> actualRules) {
        this.actualRules = actualRules;
    }

    void addNewCells(int[][] startCells, Point startPoint) {
        for (int y = 0; y < startCells.length; y++)
            for (int x = 0; x < startCells[y].length; x++) {
                cells[y + startPoint.y][x + startPoint.x] = new Cell(startCells[y][x]);
            }
    }

    void clearBoard() {
        for (int y = 0; y < cells.length; y++)
            for (int x = 0; x < cells[y].length; x++) cells[y][x] = new Cell();
    }

    void setNextState() {
        for (int i = 1; i < cells.length - 1; i++)
            for (int j = 1; j < cells[i].length - 1; j++) {
                int nOLifeNeighbours = lifeNeighbours(new Point(i, j));
                if ((meetTheRules(Cell.State.Dead, nOLifeNeighbours)) ||
                        (cells[i][j].is == Cell.State.Life && meetTheRules(Cell.State.Life, nOLifeNeighbours)))
                    cells[i][j].willBe = Cell.State.Life;
                else cells[i][j].willBe = Cell.State.Dead;
            }
    }

    Point nOCells() {
        return new Point(cells.length - 6 * resizeRate, cells[0].length - 6 * resizeRate);
    }


    private boolean meetTheRules(Cell.State state, int nOLifeNeighbours) {
        if (state == Cell.State.Dead) {
            for (int i : actualRules.get("B"))
                if (i == nOLifeNeighbours) return true;
        } else {
            for (int i : actualRules.get("S"))
                if (i == nOLifeNeighbours) return true;
        }
        return false;
    }

    private int lifeNeighbours(Point point) {
        int nOLife = 0;
        for (int i = point.x - 1; i <= point.x + 1; i++) {
            for (int j = point.y - 1; j <= point.y + 1; j++) {
                if (cells[i][j].is == Cell.State.Life && (i != point.x || j != point.y)) nOLife++;
            }
        }
        return nOLife;
    }

    void updateCellsState() {
        for (int i = 1; i < cells.length - 1; i++)
            for (int j = 1; j < cells[i].length - 1; j++) {
                cells[i][j].is = cells[i][j].willBe;
            }
        setNextState();
    }

    Rectangle getSize() {
        return new Rectangle(3 * resizeRate, 3 * resizeRate, cells.length - (3 * resizeRate), cells[0].length - (3 * resizeRate));
    }

    void resize(int resizeType, Point center) {
        if (center.x > 0 && center.x < cells.length && center.y > 0 && center.y < cells[0].length) {
            if (resizeType == 1) {
                if (cells.length + 6 > maxSize) {
                    Cell[][] copy = cells;
                    cells = new Cell[cells.length + 6][cells[0].length + 6];
                    clearBoard();
                    maxSize = cells.length;
                    for (int i = 0; i < copy.length; i++) {
                        System.arraycopy(copy[i], 0, cells[i + 3], 3, copy.length);
                    }
                }
                if (resizeRate > 0) resizeRate--;
            } else if (resizeType == -1) {
                resizeRate++;
            }
        }
    }
}
