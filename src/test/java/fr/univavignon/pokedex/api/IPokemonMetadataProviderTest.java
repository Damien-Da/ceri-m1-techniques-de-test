package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IPokemonMetadataProviderTest {
//    IPokemonMetadataProvider metadataProvider;
//
//    @Before
//    public void setUp() throws PokedexException {
//        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
//        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
//        PokemonMetadata aqualiMetadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);
//        when(metadataProvider.getPokemonMetadata(anyInt())).thenAnswer(
//                new Answer() {
//                    public Object answer(InvocationOnMock invocation) throws PokedexException {
//                        Object[] args = invocation.getArguments();
//                        int idPokemon = (int) args[0];
//                        if (idPokemon < 0 || idPokemon > 150) {
//                            throw new PokedexException("Le pokemon d'id " + idPokemon + " n'existe pas");
//                        }
//                        else if (idPokemon == 0) {
//                            return bulbizarreMetadata;
//                        }
//                        else {
//                            return aqualiMetadata;
//                        }
//                    }
//                }
//        );
//    }

    PokemonMetadataProvider metadataProvider;

    @Before
    public void setUp() {
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadataTest() throws PokedexException {
        // Test sur Bulbizarre
        PokemonMetadata bulbizarreMetaDataTest = metadataProvider.getPokemonMetadata(0);

        assertEquals(0, bulbizarreMetaDataTest.getIndex());
        assertEquals("Bulbizarre", bulbizarreMetaDataTest.getName());
        assertEquals(126, bulbizarreMetaDataTest.getAttack());
        assertEquals(126, bulbizarreMetaDataTest.getDefense());
        assertEquals(90, bulbizarreMetaDataTest.getStamina());

        // Test sur Aquali (tout autre pokemon)
        PokemonMetadata aqualiMetaDataTest = metadataProvider.getPokemonMetadata(133);

        assertEquals(133, aqualiMetaDataTest.getIndex());
        assertEquals("Aquali", aqualiMetaDataTest.getName());
        assertEquals(186, aqualiMetaDataTest.getAttack());
        assertEquals(168, aqualiMetaDataTest.getDefense());
        assertEquals(260, aqualiMetaDataTest.getStamina());

        // Test sur des échecs
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-1));
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(151));
    }
}