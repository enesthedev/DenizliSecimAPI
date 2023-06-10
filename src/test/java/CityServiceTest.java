import static org.junit.jupiter.api.Assertions.*;

import tr.edu.medipol.h5210017.domain.*;
import tr.edu.medipol.h5210017.infrastructure.services.*;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityServiceTest {
	CityService cityService;
	
    @BeforeEach
    public void setup() {
        Party party1 = new Party("AK Parti");
        Party party2 = new Party("CHP");
        Party party3 = new Party("MHP");
        Party party4 = new Party("İYİ Parti");

        List<Party> parties = new ArrayList<>();
        parties.add(party1);
        parties.add(party2);
        parties.add(party3);
        parties.add(party4);

        cityService = new CityService("Denizli", parties);
    }

    @Test
    public void testIncrementPartyVoteCount() {
        // Ensure cityService is properly initialized
        Assertions.assertNotNull(cityService);

        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("CHP");

        List<Party> parties = cityService.getParties();

        int akPartiVotes = getVoteCountByName(parties, "AK Parti");
        int chpVotes = getVoteCountByName(parties, "CHP");

        Assertions.assertEquals(1, akPartiVotes);
        Assertions.assertEquals(1, chpVotes);
    }

    @Test
    public void testGetPartyVotes() {
        // Ensure cityService is properly initialized
        Assertions.assertNotNull(cityService);

        List<String> partyVotes = cityService.getPartyVotes();

        Assertions.assertEquals(4, partyVotes.size());
        Assertions.assertTrue(partyVotes.contains("AK Parti: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("CHP: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("MHP: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("İYİ Parti: 0 oy"));
    }

    private int getVoteCountByName(List<Party> parties, String partyName) {
        for (Party party : parties) {
            if (party.getName().equals(partyName)) {
                return party.getVoteCount();
            }
        }
        return 0;
    }

}
