package fr.univavignon.pokedex.api;

/**
 * An IPokemonMetadataProvider aims to provide PokemonMetadata
 * for a given pokemon index.
 * @author fv
 */
public interface IPokemonMetadataProvider {
	/**
	 * Retrieves and returns the metadata for the pokemon
	 * denoted by the given <tt>index</tt>.
	 * @param index Index of the pokemon to retrieve metadata for.
	 * @return Metadata of the pokemon.
	 * @throws PokedexException If the given <tt>index</tt> is not valid.
	 */
	PokemonMetadata getPokemonMetadata(int index) throws PokedexException;
}
