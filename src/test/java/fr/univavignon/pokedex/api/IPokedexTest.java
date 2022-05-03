package fr.univavignon.pokedex.api;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class IPokedexTest {
    IPokedex pokedex;
    List<Pokemon> listPokemon;
    List<Pokemon> listPokemonTrieeByIndex;
    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

    @Before
    public void setUp() throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);
        listPokemon = new ArrayList<Pokemon>();
        listPokemonTrieeByIndex = new ArrayList<Pokemon>();
        when(pokedex.size()).thenAnswer(
                new Answer() {
                    public Integer answer(InvocationOnMock invocation) {
                        return listPokemon.size();
                    }
                });

        when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(
                new Answer() {
                    public Integer answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Pokemon pokemon = (Pokemon) args[0];
                        int idPokemon = pokemon.getIndex();
                        return idPokemon;
                    }
                });

        when(pokedex.getPokemon(anyInt())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) throws PokedexException {
                        Object[] args = invocation.getArguments();
                        int idPokemon = (int) args[0];
                        if (idPokemon < 0 || idPokemon > 150) {
                            throw new PokedexException("Le pokemon d'id " + idPokemon + " n'existe pas");
                        }
                        else if (idPokemon == 0) {
                            return bulbizarre;
                        }
                        else {
                            return aquali;
                        }
                    }
                }
        );

        when(pokedex.getPokemons()).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        return Collections.unmodifiableList(listPokemon);
                    }
                }
        );

        listPokemonTrieeByIndex.add(bulbizarre);
        listPokemonTrieeByIndex.add(aquali);
        when(pokedex.getPokemons(any(PokemonComparators.class))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        PokemonComparators comparator = (PokemonComparators) args[0];
                        if (comparator == PokemonComparators.INDEX) {
                            return Collections.unmodifiableList(listPokemonTrieeByIndex);
                        }
                        else {
                            return Collections.unmodifiableList(listPokemon);
                        }
                    }
                }
        );

    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());
        listPokemon.add(bulbizarre);
        assertEquals(1, pokedex.size());
        listPokemon.add(aquali);
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbizarre));
        assertEquals(133, pokedex.addPokemon(aquali));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Test sur Bulbizarre
        Pokemon bulbizarreTest = pokedex.getPokemon(0);

        assertEquals(0, bulbizarreTest.getIndex());
        assertEquals("Bulbizarre", bulbizarreTest.getName());
        assertEquals(126, bulbizarreTest.getAttack());
        assertEquals(126, bulbizarreTest.getDefense());
        assertEquals(90, bulbizarreTest.getStamina());
        assertEquals(613, bulbizarreTest.getCp());
        assertEquals(64, bulbizarreTest.getHp());
        assertEquals(4000, bulbizarreTest.getDust());
        assertEquals(4, bulbizarreTest.getCandy());
        assertEquals(56, bulbizarreTest.getIv());

        // Test sur Aquali (tout autre pokemon)
        Pokemon aqualiTest = pokedex.getPokemon(133);

        assertEquals(133, aqualiTest.getIndex());
        assertEquals("Aquali", aqualiTest.getName());
        assertEquals(186, aqualiTest.getAttack());
        assertEquals(168, aqualiTest.getDefense());
        assertEquals(260, aqualiTest.getStamina());
        assertEquals(2729, aqualiTest.getCp());
        assertEquals(202, aqualiTest.getHp());
        assertEquals(5000, aqualiTest.getDust());
        assertEquals(4, aqualiTest.getCandy());
        assertEquals(100, aqualiTest.getIv());
    }

    @Test
    public void testGetPokemons() {
        assertTrue(pokedex.getPokemons().isEmpty());
        listPokemon.add(bulbizarre);
        assertTrue(pokedex.getPokemons().contains(bulbizarre));
        listPokemon.add(aquali);
        assertTrue(pokedex.getPokemons().containsAll(Arrays.asList(bulbizarre, aquali)));
    }

    @Test
    public void testGetPokemonsWithComparator() {
        listPokemon.add(aquali);
        listPokemon.add(bulbizarre);
        PokemonComparators pokemonComparator = PokemonComparators.INDEX;
        assertEquals(listPokemonTrieeByIndex, pokedex.getPokemons(pokemonComparator));
        pokemonComparator = PokemonComparators.NAME;
        assertEquals(listPokemon, pokedex.getPokemons(pokemonComparator));
        pokemonComparator = PokemonComparators.CP;
        assertEquals(listPokemon, pokedex.getPokemons(pokemonComparator));
    }
}