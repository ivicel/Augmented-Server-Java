package info.ivicel.augmented.core.result.request.steamrep;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "steamrep")
public class SteamRepReputation {
    @JacksonXmlProperty(isAttribute = true)
    private String status;

    private String steamID32;

    private String steamID64;

    @JacksonXmlProperty(localName = "steamrepurl")
    private String steamRepUrl;

    private Reputation reputation;

    @Data
    @JacksonXmlRootElement(localName = "reputation")
    public static class Reputation {
        private String full;

        private String summary;
    }
}
