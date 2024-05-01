import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void returnExceptionWhenArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void returnCorrectMessageWhenArgumentIsNull() {
        assertEquals("Horses cannot be null.", assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)).getMessage());;
    }

    @Test
    public void returnExceptionWhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void returnCorrectMessageWhenListIsEmpty() {
        assertEquals("Horses cannot be empty.", assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())).getMessage());
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse: " + i, i + 1, i + 5));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (Horse horse : horses) {
            horse.move();
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse: " + i, i + 1, i + 5));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(winner, hippodrome.getWinner());
    }
}