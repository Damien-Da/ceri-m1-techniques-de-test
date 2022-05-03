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
//    IPokedex pokedex;
//    List<Pokemon> listPokemon;
//    List<Pokemon> listPokemonTriee;
//    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
//    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
//
//    @Before
//    public void setUp() throws PokedexException {
//        pokedex = Mockito.mock(IPokedex.class);
//        listPokemon = new ArrayList<Pokemon>();
//        listPokemonTriee = new ArrayList<Pokemon>();
//        when(pokedex.size()).thenAnswer(
//                new Answer() {
//                    public Integer answer(InvocationOnMock invocation) {
//                        return listPokemon.size();
//                    }
//                });
//
//        when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(
//                new Answer() {
//                    public Integer answer(InvocationOnMock invocation) {
//                        Object[] args = invocation.getArguments();
//                        Pokemon pokemon = (Pokemon) args[0];
//                        int idPokemon = pokemon.getIndex();
//                        return idPokemon;
//                    }
//                });
//
//        when(pokedex.getPokemon(anyInt())).thenAnswer(
//                new Answer() {
//                    public Object answer(InvocationOnMock invocation) throws PokedexException {
//                        Object[] args = invocation.getArguments();
//                        int idPokemon = (int) args[0];
//                        if (idPokemon < 0 || idPokemon > 150) {
//                            throw new PokedexException("Le pokemon d'id " + idPokemon + " n'existe pas");
//                        }
//                        else if (idPokemon == 0) {
//                            return bulbizarre;
//                        }
//                        else {
//                            return aquali;
//                        }
//                    }
//                }
//        );
//
//        when(pokedex.getPokemons()).thenAnswer(
//                new Answer() {
//                    public Object answer(InvocationOnMock invocation) {
//                        return Collections.unmodifiableList(listPokemon);
//                    }
//                }
//        );
//
//        listPokemonTriee.add(bulbizarre);
//        listPokemonTriee.add(aquali);
//        when(pokedex.getPokemons(any(PokemonComparators.class))).thenAnswer(
//                new Answer() {
//                    public Object answer(InvocationOnMock invocation) {
//                        Object[] args = invocation.getArguments();
//                        PokemonComparators comparator = (PokemonComparators) args[0];
//                        if (comparator.compare(listPokemon.get(0), listPokemon.get(1)) <= 0) {
//                            return Collections.unmodifiableList(listPokemon);
//                        }
//                        else {
//                            return Collections.unmodifiableList(listPokemonTriee);
//                        }
//                    }
//                }
//        );
//
//    }

    Pokedex pokedex;

    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

    @Before
    public void setUp() {
        pokedex = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());
        pokedex.addPokemon(bulbizarre);
        assertEquals(1, pokedex.size());
        pokedex.addPokemon(aquali);
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
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(aquali);

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
        Pokemon aqualiTest = pokedex.getPokemon(1);

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
        pokedex.addPokemon(bulbizarre);
        assertTrue(pokedex.getPokemons().contains(bulbizarre));
        pokedex.addPokemon(aquali);
        assertTrue(pokedex.getPokemons().containsAll(Arrays.asList(bulbizarre, aquali)));
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        Pokemon pokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);

        assertEquals(0, pokemon.getIndex());
        assertEquals("Bulbizarre", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(0, pokemon.getIv());
    }

    @Test
    public void testGetPokemonsWithComparator() {
        pokedex.addPokemon(aquali);
        pokedex.addPokemon(bulbizarre);
        List<Pokemon> listPokemon = new ArrayList<Pokemon>();
        listPokemon.add(aquali);
        listPokemon.add(bulbizarre);
        List<Pokemon> listPokemonTriee = new ArrayList<Pokemon>();
        listPokemonTriee.add(bulbizarre);
        listPokemonTriee.add(aquali);

        PokemonComparators pokemonComparator = PokemonComparators.INDEX;
        assertEquals(listPokemonTriee, pokedex.getPokemons(pokemonComparator));
        pokemonComparator = PokemonComparators.NAME;
        assertEquals(listPokemon, pokedex.getPokemons(pokemonComparator));
        pokemonComparator = PokemonComparators.CP;
        assertEquals(listPokemonTriee, pokedex.getPokemons(pokemonComparator));
    }
}