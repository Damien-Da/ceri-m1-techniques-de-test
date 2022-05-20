package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketPokemonFactoryTest {
    RocketPokemonFactory rocketPokemonFactory;

    @Before
    public void setUp() throws Exception {
        rocketPokemonFactory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemon() {
        Pokemon bulbasaur = rocketPokemonFactory.createPokemon(1, 613, 64, 4000, 4);

        assertEquals(1, bulbasaur.getIndex());
        assertEquals("Bulbasaur", bulbasaur.getName());
        assertEquals(126, bulbasaur.getAttack());
        assertEquals(126, bulbasaur.getDefense());
        assertEquals(90, bulbasaur.getStamina());
        assertEquals(613, bulbasaur.getCp());
        assertEquals(64, bulbasaur.getHp());
        assertEquals(4000, bulbasaur.getDust());
        assertEquals(4, bulbasaur.getCandy());
        assertEquals(1, bulbasaur.getIv());
    }
}