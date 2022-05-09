package fr.univavignon.pokedex.api;

/**
 * Pokedex builder
 * @author damien dallon
 */
public class PokedexFactory implements IPokedexFactory {
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
