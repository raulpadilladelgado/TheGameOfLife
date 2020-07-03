import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TheGameOfLifeShould {
    @Test
    void a_dead_cell_with_three_neighbors_comes_back_to_life() {
        World world = new World();
        world.addDeadCell();
        world.addAliveCell();
        world.addAliveCell();
        world.addAliveCell();
        world.nextGeneration();
        assertThat(world.cells.get(0).isAlive).isTrue();
    }
    @Test
    void a_alive_cell_with_two_or_three_neighbours_stay_in_life(){

    }
    @Test
    void a_alive_cell_without_two_or_three_neighbours_is_dead(){

    }

}

class Cell {
    public Boolean isAlive = true;

    public Cell() {
        if (Math.random() * 10 > 4) {
            isAlive = true;
        } else {
            isAlive = false;
        }
    }

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }
}

class World {
    public Map<Integer, Cell> cells = new HashMap<>();

    public void addAliveCell() {
        cells.put(cells.size(), new Cell(true));
    }

    public void addDeadCell() {
        cells.put(cells.size(), new Cell(false));
    }

    public void nextGeneration() {
        reviveCells();
    }

    public World() {

    }

    public World(int size) {
        for (int i = 0; i < size; i++) {
            if (Math.random() * 10 > 4) {
                addAliveCell();
            } else {
                addDeadCell();
            }
        }
    }

    private void reviveCells() {
        for (Integer cellKey : cells.keySet()) {
            if(!cells.get(cellKey).isAlive){
                int amountOfNeighbours = getAmountOfNeighbours(cellKey);
                if (amountOfNeighbours == 3) {
                    cells.get(cellKey).isAlive = true;
                }
            }
        }
    }

    private int getAmountOfNeighbours(Integer cellKey) {
        int amountOfNeighbours = 0;
        for (Iterator<Integer> iterator = cells.keySet().iterator(); iterator.hasNext(); ) {
            Integer key = iterator.next();
            boolean isANeighbour = !cells.get(cellKey).equals(cells.get(key));
            boolean neighbourIsAlive = cells.get(key).isAlive;
            if (isANeighbour && neighbourIsAlive) {
                amountOfNeighbours++;
            }
        }
        return amountOfNeighbours;
    }
}