import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    Horse horse = new Horse("Igor", 12, 30);

    @Test
    public void nullHorses() {

        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0));

        assertEquals("Name cannot be null.", assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 0, 0)).getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\s", "\n", "\t"})
    public void nullParameterizedHorse(String empties) {

        assertThrows(IllegalArgumentException.class, () -> new Horse(empties, 0, 0));

        assertEquals("Name cannot be blank.", assertThrows(IllegalArgumentException.class,
                () -> new Horse(empties, 0, 0)).getMessage());
    }

    @Test
    public void noNegativeDoubleValue() {

        assertThrows(IllegalArgumentException.class, () -> new Horse("Grisha", -1, 0));

        assertEquals("Speed cannot be negative.", assertThrows(IllegalArgumentException.class,
                () -> new Horse("Martin", -1, 0)).getMessage());

        assertThrows(IllegalArgumentException.class, () -> new Horse("Valera", 15, -1));

        assertEquals("Distance cannot be negative.", assertThrows(IllegalArgumentException.class,
                () -> new Horse("Dimas", 12, -1)).getMessage());
    }

    @Test
    public void nameTest() {
        assertEquals("Igor", horse.getName());
    }

    @Test
    public void speedTest() {
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void distanceTest() {
        assertEquals(30, horse.getDistance());

        assertEquals(0, new Horse("Vanya", 20).getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.6, 0.5})
    public void moveTest(double args) {
        double first = 0.2, second = 0.9, distance = horse.getDistance();

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            //stubbing
            mockedStatic.when(() -> Horse.getRandomDouble(first, second)).thenReturn(args);
            //verifying if getRandomDouble was called
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(first, second));

            double calculatedValue = distance + horse.getSpeed() * Horse.getRandomDouble(first, second);
            assertEquals(calculatedValue, horse.getDistance());
        }
    }

}