package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Pokedex.
 * @author damien dallon
 */
public class Pokedex implements IPokedex {

    /** List of Pokemon in Pokedex **/
    private List<Pokemon> pokemons;

    /** Provider of Pokemon metadata **/
    PokemonMetadataProvider metaDataProvider;

    /** Pokemon builder **/
    RocketPokemonFactory pokemonFactory;

    /**
     * Default constructor.
     * @param metadataProvider Pokemon metadata provider.
     * @param pokemonFactory Pokemon builder.
     */
    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metaDataProvider = (PokemonMetadataProvider) metadataProvider;
        this.pokemonFactory = (RocketPokemonFactory) pokemonFactory;
        this.pokemons = new ArrayList<>();

//        this.pokemonFactory.setPokedex(this);
    }

    /**
     * Pokemon list size getter
     * @return size of pokemon list
     */
    @Override
    public int size() {
        return this.pokemons.size();
    }

    /**
     * Add a Pokemon in pokemon list
     * @param pokemon pokemon to add in pokemon list
     **/
    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    /**
     * Pokemon getter
     * @param id Unique pokedex relative identifier.
     * @return Pokemon
     * @throws PokedexException
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        return this.pokemons.get(id);
    }

    /**
     * Pokemon list getter
     * @return unmodifiable Pokemon list
     */
    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(this.pokemons);
    }

    /**
     * Pokemon list ordered getter
     * @param order Comparator instance used for sorting the created view.
     * @return unmodifiable Pokemon list ordered
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> pokemonsSorted = this.pokemons;
        pokemonsSorted.sort(order);
        return Collections.unmodifiableList(pokemonsSorted);
    }

    /**
     * Pokemon creator
     * @param index Pokemon index.
     * @param cp Pokemon CP.
     * @param hp Pokemon HP.
     * @param dust Required dust for upgrading pokemon.
     * @param candy Required candy for upgrading pokemon.
     * @return Pokemon created
     * @throws PokedexException
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    /**
     * Pokemon metadata getter
     * @param index Index of the pokemon to retrieve metadata for.
     * @return PokemonMetadata
     * @throws PokedexException
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.metaDataProvider.getPokemonMetadata(index);
    }
}
