package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {

    private static final String DESCRIPTION = "Complete feature list";
    private static final String START = "21-02-26 1100";
    private static final String END = "21-02-26 1500";
    private static final String NAME = "Amy Bee";

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, START, END, NAME));
        assertThrows(NullPointerException.class, () -> new Event(DESCRIPTION, null, END, NAME));
        assertThrows(NullPointerException.class, () -> new Event(DESCRIPTION, START, null, NAME));
        assertThrows(NullPointerException.class, () -> new Event(DESCRIPTION, START, END, null));
    }

    @Test
    public void getters() {
        Event event = new Event(DESCRIPTION, START, END, NAME);

        assertEquals(DESCRIPTION, event.getDescription());
        assertEquals(START, event.getStartTime());
        assertEquals(END, event.getEndTime());
        assertEquals(new Name(NAME), event.getName());
    }

    @Test
    public void equals() {
        Event event = new Event(DESCRIPTION, START, END, NAME);

        // same values -> returns true
        assertTrue(event.equals(new Event(DESCRIPTION, START, END, NAME)));

        // same object -> returns true
        assertTrue(event.equals(event));

        // null -> returns false
        assertFalse(event.equals(null));

        // different type -> returns false
        assertFalse(event.equals(5));

        // different description -> returns false
        assertFalse(event.equals(new Event("Other", START, END, NAME)));

        // different start -> returns false
        assertFalse(event.equals(new Event(DESCRIPTION, "21-02-26 1000", END, NAME)));

        // different end -> returns false
        assertFalse(event.equals(new Event(DESCRIPTION, START, "21-02-26 1600", NAME)));
    }

    @Test
    public void toStringMethod() {
        Event event = new Event(DESCRIPTION, START, END, NAME);
        String expected = String.format("%s: %s from %s to %s.", NAME, DESCRIPTION, START, END, new Name(NAME));
        assertEquals(expected, event.toString());
    }
}
