package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
    private Pokedex pokedex;

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        PokemonMetadata pokemonMetadata = pokedex.getPokemonMetadata(index);
        return new Pokemon(pokemonMetadata.getIndex(), pokemonMetadata.getName(), pokemonMetadata.getAttack(), pokemonMetadata.getDefense(),
                pokemonMetadata.getStamina(), cp, hp, dust, candy, 0.);
    }
}
