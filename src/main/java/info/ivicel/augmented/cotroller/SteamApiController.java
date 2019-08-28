package info.ivicel.augmented.cotroller;

import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.utils.Http;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/steamapi")
public class SteamApiController {
    private AugmentedProperties props;

    @Autowired
    public SteamApiController(AugmentedProperties props) {
        this.props = props;
    }

    @GetMapping("/GetPlayerSummaries")
    public ResponseEntity getPlayerSummaries(String steamids) {
        if (!StringUtils.hasText(props.getSteamApiKey())) {
            return Response.fail500();
        }

        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" +
                props.getSteamApiKey() + "&steamids=" + steamids + "&format=json";
        try {
            return Http.getForEntity(url, Map.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return Response.fail500();
        }
    }
}
