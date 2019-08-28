package info.ivicel.augmented.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "augmented")
public class AugmentedProperties {
    private String SteamApiKey = "";

    private String OpenCriticKey = "";

    private String IsThereAnyDealKey = "";

    private String TwitchApiKey = "";

    private String WSGFEndpoint = "";

    private String KeyLOLEndpoint = "";

    private String SteamSpyEndpoint = "";

    private String SteamToolsApiEndpoint = "";

    private String OpenCriticEndpoint = "";

    private String SteamRepEndpoint = "";

    private String PCGWEndpoint = "";

}
