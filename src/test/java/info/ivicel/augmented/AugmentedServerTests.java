package info.ivicel.augmented;

import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.repository.SupporterUsersRepository;
import info.ivicel.augmented.utils.Http;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AugmentedServerTests {
    @Autowired
    private SupporterUsersRepository supporterUsersRepository;

    @Autowired
    private AugmentedProperties props;

    @Test
    public void contextLoads() {
        // StreamsEndpoint streams = new StreamsEndpoint(props.getTwitchApiKey());
        // streams.setUserLogin("Asmongold");
        // List<User> users;
        // User user = new User();
        // do {
        //     users = streams.getNextPage();
        //     System.out.println(users);
        //     System.out.println("-------------");
        //     if (!users.isEmpty()) {
        //         user = users.get(users.size() - 1);
        //     }
        // } while (!users.isEmpty());


        // System.out.println(streams.getNextPage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Client-ID", props.getTwitchApiKey());
        HttpEntity entity = new HttpEntity(headers);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("user_login", "Asmongold");
        ResponseEntity<String> resp = Http
                .exchange("https://api.twitch.tv/helix/streams", HttpMethod.GET, entity, String.class, map);

        System.out.println(resp.getBody());
    }
}
