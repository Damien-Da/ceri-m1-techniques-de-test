package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private static Map<Integer, PokemonMetadata> metadata = new HashMap<>();

    static {
        metadata.put(0, new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        metadata.put(133, new PokemonMetadata(133, "Aquali", 186, 168, 260));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index > 150) {
            throw new PokedexException("Ce pokemon n'existe pas");
        }
        return metadata.get(index);
    }
}
