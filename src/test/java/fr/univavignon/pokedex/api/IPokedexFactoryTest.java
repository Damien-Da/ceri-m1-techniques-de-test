package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider pokemonMetadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex pokedex = new IPokedex() {
        List<Pokemon> listPokemon = new ArrayList<Pokemon>();
        @Override
        public int size() {
            return listPokemon.size();
        }

        @Override
        public int addPokemon(Pokemon pokemon) {
            listPokemon.add(pokemon);
            return pokemon.getIndex();
        }

        @Override
        public Pokemon getPokemon(int id) throws PokedexException {
            return listPokemon.get(id);
        }

        @Override
        public List<Pokemon> getPokemons() {
            return listPokemon;
        }

        @Override
        public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
            return listPokemon;
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
    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

    @Before
    public void setUp() throws PokedexException {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        when(pokedexFactory.createPokedex(any(IPokemonMetadataProvider.class), any(IPokemonFactory.class))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        return pokedex;
                    }
                }
        );
    }

    @Test
    public void testCreatePokedex() throws PokedexException {
        IPokedex pokedexTest = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        int id = pokedex.addPokemon(bulbizarre);
        assertEquals(bulbizarre, pokedex.getPokemon(id));
        //J'ai pas eu le temps de le tester autrement.
    }
}