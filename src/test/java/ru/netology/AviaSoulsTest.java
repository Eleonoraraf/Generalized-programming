package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();
    Ticket ticket1 = new Ticket("Moscow", "Tula", 5_000, 8, 10);
    Ticket ticket2 = new Ticket("Moscow", "Tula", 6_000, 8, 10);
    Ticket ticket5 = new Ticket("Moscow", "Tula", 4_000, 5, 12);
    Ticket ticket9 = new Ticket("Astrakhan", "Volgograd", 3_000, 12, 13);
    Ticket ticket3 = new Ticket("Саратов", "Элиста", 9_000, 14, 16);

    @BeforeEach
    public void setup() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket9);
    }

    @Test
    public void test() {
        String[] expected = {"Moscow", "Moscow", "Саратов"};
        String[] actual = {ticket1.getFrom(), ticket2.getFrom(), ticket3.getFrom()};
        Assertions.assertArrayEquals(expected, actual);

        String[] expected2 = {"Tula", "Tula", "Элиста"};
        String[] actual2 = {ticket1.getTo(), ticket2.getTo(), ticket3.getTo()};
        Assertions.assertArrayEquals(expected2, actual2);

        int[] expected3 = {5_000, 6_000, 9_000};
        int[] actual3 = {ticket1.getPrice(), ticket2.getPrice(), ticket3.getPrice()};
        Assertions.assertArrayEquals(expected3, actual3);

        int[] expected4 = {8, 8, 14};
        int[] actual4 = {ticket1.getTimeFrom(), ticket2.getTimeFrom(), ticket3.getTimeFrom()};
        Assertions.assertArrayEquals(expected4, actual4);

        int[] expected5 = {10, 10, 16};
        int[] actual5 = {ticket1.getTimeTo(), ticket2.getTimeTo(), ticket3.getTimeTo()};
        Assertions.assertArrayEquals(expected5, actual5);
    }

    @Test
    public void compareIfMore() {
        int expected = 1;
        int actual = ticket1.compareTo(ticket9);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareIfLess() {
        int expected = -1;
        int actual = ticket9.compareTo(ticket5);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sortByPrice() {
        Ticket[] expected = {ticket1, ticket2, ticket5};
        Ticket[] actual = aviaSouls.search("Moscow", "Tula");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void find() {
        Ticket[] expected = {ticket9};
        Ticket[] actual = aviaSouls.search("Astrakhan", "Volgograd");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void sortByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket1, ticket2, ticket5};
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "Tula", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
