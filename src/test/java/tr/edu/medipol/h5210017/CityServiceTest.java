package tr.edu.medipol.h5210017;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tr.edu.medipol.h5210017.domain.Party;
import tr.edu.medipol.h5210017.infrastructure.services.CityService;

class CityServiceTest {
	CityService cityService;

	/**
	 * @method setup
	 * @description
	 * 	JUnit'in BeforeEach notasyonunu kullanarak her test metodu çalışmadan önce execute edilmesini sağladım.
	 * 	Bu kod her metod öncesi çalıştırıldığına partileri oluşturup onları private değer olan cityService'e atıyor.
	 */
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

    /**
     * @method testIncrementPartyVoteCount
     * @description
     *	Assertions yardımcısı ile cityService'in null olmadığından emin oldum. Bu sayede bir önceki
     * 	satırda bulunan setup metodunun çalıştığından da emin oldum. Partilere oy verilmesini manipule ettim ve bu oyların artıp
     * 	artmadığıyla alakalı kontrol sağladım.
     */
    @Test
    public void testIncrementPartyVoteCount() {
        Assertions.assertNotNull(cityService);

        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("CHP");

        List<Party> parties = cityService.getParties();

        int akPartiVotes = getVoteCountByName(parties, "AK Parti");
        int chpVotes = getVoteCountByName(parties, "CHP");

        Assertions.assertEquals(1, akPartiVotes);
        Assertions.assertEquals(1, chpVotes);
    }
    
    /**
     * @method testDecrementPartyVoteCount
     * @description
     *	Assertions yardımcısı ile cityService'in null olmadığından emin oldum. Partilere oy verilmesini manipule ettim ve bu oyların azalıp
     * 	azalmadığı ile alakalı kontrol sağladım.
     */
    @Test
    public void testDecrementPartyVoteCount() {
        Assertions.assertNotNull(cityService);

        cityService.decrementPartyVoteCount("AK Parti");
        cityService.decrementPartyVoteCount("CHP");

        List<Party> parties = cityService.getParties();

        int akPartiVotes = getVoteCountByName(parties, "AK Parti");
        int chpVotes = getVoteCountByName(parties, "CHP");

        Assertions.assertEquals(0, akPartiVotes);
        Assertions.assertEquals(0, chpVotes);
    }

    /**
     * @method testGetPartyVotes
     * @description
     *	Eklediğim yardımcı fonksiyonu test ettim.
     */
    @Test
    public void testGetPartyVotes() {
        Assertions.assertNotNull(cityService);

        List<String> partyVotes = cityService.getPartyVotes();

        Assertions.assertEquals(4, partyVotes.size());
        Assertions.assertTrue(partyVotes.contains("AK Parti: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("CHP: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("MHP: 0 oy"));
        Assertions.assertTrue(partyVotes.contains("İYİ Parti: 0 oy"));
    }

    /**
     * @method getVoteCountByName
     * @description
     *	Parti ismine göre oy sayısını getirebilmesi için bir yardımcı metod hazırlamam gerekti.
     *	Bu metodu "DONT REPEAT YOURE SELF" yaklaşımı neticesinde ekledim.
     */
    private int getVoteCountByName(List<Party> parties, String partyName) {
        for (Party party : parties) {
            if (party.getName().equals(partyName)) {
                return party.getVoteCount();
            }
        }
        return 0;
    }

}
