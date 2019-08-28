package info.ivicel.augmented.cotroller;

import info.ivicel.augmented.common.API;
import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.core.model.dto.TwitchDTO;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.core.twitch.endpoint.GamesEndpoint;
import info.ivicel.augmented.core.twitch.endpoint.StreamsEndpoint;
import info.ivicel.augmented.core.twitch.response.GamesResponse.Game;
import info.ivicel.augmented.core.twitch.response.StreamsResponse.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API.VERSION_URL + "/twitch")
public class TwitchController {
    private AugmentedProperties props;

    @Autowired
    public TwitchController(AugmentedProperties props) {
        this.props = props;
    }

    @GetMapping("/stream")
    public ResponseEntity stream(String channel) {
        StreamsEndpoint streams = new StreamsEndpoint(props.getTwitchApiKey());
        streams.setUserLogin(channel);
        List<User> users = streams.getNextPage();

        TwitchDTO twitchDTO = new TwitchDTO();

        if (users.isEmpty()) {
            return Response.success(twitchDTO);
        }


        User user = users.get(users.size() - 1);
        GamesEndpoint games = new GamesEndpoint(props.getTwitchApiKey());
        games.setGameId(user.getGameId());
        List<Game> gamesResp = games.getNextPage();

        if (gamesResp.isEmpty()) {
            return Response.success(twitchDTO);
        }

        Game game = gamesResp.get(gamesResp.size() - 1);
        twitchDTO.setUsername(user.getUsername());
        twitchDTO.setTitle(user.getTitle());
        twitchDTO.setThumbnailUrl(user.getThumbnailUrl());
        twitchDTO.setViewerCount(user.getViewerCount());
        twitchDTO.setGame(game.getName());

        return Response.success(twitchDTO);
    }
}
