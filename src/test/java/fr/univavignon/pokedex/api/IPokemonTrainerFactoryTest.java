package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IPokemonTrainerFactoryTest {
    IPokedex pokedex = new IPokedex() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public int addPokemon(Pokemon pokemon) {
            return 0;
        }

        @Override
        public Pokemon getPokemon(int id) throws PokedexException {
            return null;
        }

        @Override
        public List<Pokemon> getPokemons() {
            return null;
        }

        @Override
        public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
            return null;
        }

        @Override
        public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
            return null;
        }

        @Override
        public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
            return null;
        }
    };
    PokemonTrainer pokemonTrainer = new PokemonTrainer("Damien", Team.MYSTIC, pokedex);
    IPokemonTrainerFactory pokemonTrainerFactory;
    IPokedexFactory pokedexFactory;

    @BeforeEach
    void setUp() throws PokedexException {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        when(pokemonTrainerFactory.createTrainer(any(String.class), any(Team.class), any(IPokedexFactory.class))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        return pokemonTrainer;
                    }
                }
        );
    }

    @Test
    void createTrainer() {
        PokemonTrainer pokemonTrainerTest = pokemonTrainerFactory.createTrainer("Damien", Team.MYSTIC, pokedexFactory);
        assertEquals("Damien", pokemonTrainerTest.getName());
        assertEquals(Team.MYSTIC, pokemonTrainerTest.getTeam());
        assertEquals(pokedex, pokemonTrainerTest.getPokedex());
    }
}