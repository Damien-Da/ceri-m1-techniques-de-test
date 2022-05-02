package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        int idPokemon = (int) args[0];
                        int cpPokemon = (int) args[1];
                        int hpPokemon = (int) args[2];
                        int dustPokemon = (int) args[3];
                        int candyPokemon = (int) args[4];
                        if (idPokemon == 0 && cpPokemon == 613 && hpPokemon == 64 && dustPokemon == 4000 && candyPokemon == 4) {
                            return bulbizarre;
                        }
                        else {
                            return aquali;
                        }
                    }
                }
        );
    }

    @Test
    public void createPokemon() {
        // Test sur Bulbizarre
        Pokemon bulbizarreTest = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        assertEquals(0, bulbizarreTest.getIndex());
        assertEquals("Bulbizarre", bulbizarreTest.getName());
        assertEquals(126, bulbizarreTest.getAttack());
        assertEquals(126, bulbizarreTest.getDefense());
        assertEquals(90, bulbizarreTest.getStamina());
        assertEquals(613, bulbizarreTest.getCp());
        assertEquals(64, bulbizarreTest.getHp());
        assertEquals(4000, bulbizarreTest.getDust());
        assertEquals(4, bulbizarreTest.getCandy());
        Assertions.assertEquals(56, bulbizarreTest.getIv());

        // Test sur Aquali (tout autre pokemon)
        Pokemon aqualiTest = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);

        assertEquals(133, aqualiTest.getIndex());
        assertEquals("Aquali", aqualiTest.getName());
        assertEquals(186, aqualiTest.getAttack());
        assertEquals(168, aqualiTest.getDefense());
        assertEquals(260, aqualiTest.getStamina());
        assertEquals(2729, aqualiTest.getCp());
        assertEquals(202, aqualiTest.getHp());
        assertEquals(5000, aqualiTest.getDust());
        assertEquals(4, aqualiTest.getCandy());
        Assertions.assertEquals(100, aqualiTest.getIv());

    }
}