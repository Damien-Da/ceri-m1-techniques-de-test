package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

public class Pokedex implements IPokedex {
    private List<Pokemon> pokemons;
    PokemonMetadataProvider metaDataProvider;
    PokemonFactory pokemonFactory;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metaDataProvider = (PokemonMetadataProvider) metadataProvider;
        this.pokemonFactory = (PokemonFactory) pokemonFactory;
        this.pokemons = new ArrayList<>();

        this.pokemonFactory.setPokedex(this);
    }

    @Override
    public int size() {
        return this.pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        return this.pokemons.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(this.pokemons);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> pokemonsSorted = this.pokemons;
        pokemonsSorted.sort(order);
        return Collections.unmodifiableList(pokemonsSorted);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.metaDataProvider.getPokemonMetadata(index);
    }
}
