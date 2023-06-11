package tr.edu.medipol.h5210017.presentation.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tr.edu.medipol.h5210017.domain.*;
import tr.edu.medipol.h5210017.infrastructure.services.CityService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

	private CityService cityService;


	/**
	 * @method setup
	 * @description
	 * 	JUnit'in BeforeEach notasyonunu kullanarak her test metodu çalışmadan önce execute edilmesini sağladım.
	 * 	Bu kod her metod öncesi çalıştırıldığına partileri oluşturup onları private değer olan cityService'e atıyor.
	 */
    @BeforeEach
    void setUp() {
        Party party1 = new Party("AK Parti");
        Party party2 = new Party("CHP");
        Party party3 = new Party("MHP");
        Party party4 = new Party("İYİ Parti");

        List<Party> parties = List.of(party1, party2, party3, party4);
        cityService = new CityService("Denizli", parties);
    }


	/**
	 * @method testGetCityName
	 * @description Şehir servisindeki ismi getirme fonksiyonunu kontrol eder.
	 */
    @Test
    void testGetCityName() {
        assertEquals("Denizli", cityService.getCityName());
    }

    /**
     * @method testGetParties
     * @description setup metodunda eklenen partilerin isimlerini kontrol eder. Partilerin başarıyla eklenip eklenmediğinden emin olur.
     */
    @Test
    void testGetParties() {
        List<Party> cityParties = cityService.getParties();
        assertEquals(4, cityParties.size());
        assertEquals("AK Parti", cityParties.get(0).getName());
        assertEquals("CHP", cityParties.get(1).getName());
        assertEquals("MHP", cityParties.get(2).getName());
        assertEquals("İYİ Parti", cityParties.get(3).getName());
    }

    /**
     * @method testIncrementPartyVoteCount
     * @description Partilerin oy sayılarının artıp artmadığını kontrol eder ve aynı zamanda partilerin listelenmesini de kontrol eder.
     */
    @Test
    void testIncrementPartyVoteCount() {
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("AK Parti");
        cityService.incrementPartyVoteCount("CHP");
        cityService.incrementPartyVoteCount("MHP");

        List<String> partyVotes = cityService.getPartyVotes();
        assertEquals(4, partyVotes.size());
        assertEquals("İYİ Parti: 2 oy", partyVotes.get(3));
        assertEquals("CHP: 1 oy", partyVotes.get(1));
        assertEquals("MHP: 1 oy", partyVotes.get(2));
        assertEquals("AK Parti: 3 oy", partyVotes.get(0));
    }
    

    /**
     * @method testDecrementPartyVoteCount
     * @description Partilerin oy aldıktan sonra eksilen oy fonksiyonunu test eder.
     */
    @Test
    void testDecrementPartyVoteCount() {
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.incrementPartyVoteCount("İYİ Parti");
        cityService.decrementPartyVoteCount("İYİ Parti");

        List<String> partyVotes = cityService.getPartyVotes();
        assertEquals(4, partyVotes.size());
        assertEquals("İYİ Parti: 1 oy", partyVotes.get(3));
    }
}
