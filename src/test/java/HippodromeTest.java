import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void testNullHippodromeList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void testEmptyHippodromeList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> expectedList = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            expectedList.add(new Horse("Horse#" + (i + 1), (i + 1) * 5, (i + 1) * 15 ));
        }

        Hippodrome hippodrome = new Hippodrome(expectedList);
        List<Horse> actualList = hippodrome.getHorses();

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testMoveHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for(Horse horse: mockHorses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {

        Horse horse1 = new Horse("Pegas", 30, 300);
        Horse horse2 = new Horse("Plotva", 50, 450);
        Horse horse3 = new Horse("Thunder", 40, 370);
        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse2, hippodrome.getWinner());
    }
}
