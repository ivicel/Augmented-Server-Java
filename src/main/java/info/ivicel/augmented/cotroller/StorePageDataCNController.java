package info.ivicel.augmented.cotroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.core.model.entity.Steamcn;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.service.SteamcnService;
import info.ivicel.augmented.utils.Http;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storepagedatacn")
public class StorePageDataCNController {
    private SteamcnService steamcnService;
    private AugmentedProperties props;

    @Autowired
    public StorePageDataCNController(SteamcnService steamcnService, AugmentedProperties props) {
        this.steamcnService = steamcnService;
        this.props = props;
    }

    @GetMapping
    public ResponseEntity index(Integer appid) {
        String json;
        Optional<Steamcn> optionalSteamcn = steamcnService.findByAppid(appid);

        if (optionalSteamcn.isPresent()) {
            Steamcn steamcn = optionalSteamcn.get();
            long currentTime = System.currentTimeMillis();

            if (currentTime - steamcn.getAccessTime().getTime() < 3600) {
                json = steamcn.getJson();
            } else {
                steamcnService.remove(steamcn);
                json = getNewValue(appid);
            }
        } else {
            json = getNewValue(appid);
        }

        try {
            Map map = new ObjectMapper().readValue(json, Map.class);
            return Response.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.success(Collections.EMPTY_MAP);
        }

    }

    private String getNewValue(Integer appid) {
        String url = props.getKeyLOLEndpoint();
        if (!StringUtils.hasText(url)) {
            return null;
        }

        if (!url.endsWith("/")) {
            url += "/";
        }
        url += "appid=" + appid;

        boolean up;
        ResponseEntity<String> resp = Http.exchange(url, HttpMethod.HEAD, null, String.class);
        switch (resp.getStatusCodeValue()) {
            case 500:
            case 429:
            case 404:
                up = false;
                break;

            default:
                up = true;
                break;
        }

        if (up) {
            Steamcn steamcn = new Steamcn();
            steamcn.setAppid(appid);
            steamcn.setJson(resp.getBody());
            steamcnService.insert(steamcn);

            return resp.getBody();
        }

        return null;
    }
}
