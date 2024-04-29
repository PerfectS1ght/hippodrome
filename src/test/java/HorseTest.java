import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void testNullHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 32, 124));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\r\n", " \t \n"})
    public void testEmptyHorses(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(input.trim(), 32, 123));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void testNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Plotva", -10, 33));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void testNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegas", 10, -1378));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void testGetHorseName() {
        String expectedName = "Plotva";
        Horse horse = new Horse(expectedName, 10, 20);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }
    @Test
    public void testGetHorseSpeed() {
        double expectedSpeed = 20.4;
        Horse horse = new Horse("Pegas", expectedSpeed, 32);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }
    @Test
    public void testGetHorseDistance() {
        double expectedDistance = 36.6;
        Horse horse = new Horse("Ferrari", 12.5, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
        expectedDistance = 0;
        horse = new Horse("Lamba", 14.3);
        actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void testMoveGetRandom() {
        Horse horse = new Horse("Caballo", 15, 200);
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void testMoveDistanceUpdated() {

        double speed = 10;
        double initialDistance = 100;
        Horse horse = new Horse("Storm", speed, initialDistance);

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            double expectedDistance = initialDistance + speed * 0.5; // По формуле
            assertEquals(expectedDistance, horse.getDistance());
        }
    }







    
}
