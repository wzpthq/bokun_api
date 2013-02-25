package is.bokun.client;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import is.bokun.dtos.ItemDto;
import is.bokun.dtos.TagGroupDto;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * Client for the Tag resource.
 *
 * @author Olafur Gauti Gudmundsson
 */
public class TagClient extends AbstractClient {

    private static final String BASE = "/currency.json";

    public TagClient(String host, String accessKey, String secretKey, AsyncHttpClient asyncClient) {
        super(host, accessKey, secretKey, asyncClient);
    }

    /**
     * Get all the available tags.
     *
     * @param lang The language in which to serve the tags.
     * @return a Map containing the Tags, where the key is the Tag ID, and the value is the Tag information
     */
    public Map<Long,ItemDto> findAll(String lang) {
        try {
            String uri = BASE + "/findAll?lang=" + lang;
            AsyncHttpClient.BoundRequestBuilder b = asyncClient.prepareGet(host + uri);
            addSecurityHeaders(b, "GET", uri);

            Response r = b.execute().get();
            validateResponse(r);
            return json.readValue(r.getResponseBody("UTF-8"), new TypeReference<Map<Long,ItemDto>>(){});
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    /**
     * Get all the available groups.
     * @param lang The language in which to serve the groups.
     * @return List of all the tag groups.
     */
    public List<TagGroupDto> getGroups(String lang) {
        try {
            String uri = BASE + "/groups?lang=" + lang;
            AsyncHttpClient.BoundRequestBuilder b = asyncClient.prepareGet(host + uri);
            addSecurityHeaders(b, "GET", uri);

            Response r = b.execute().get();
            validateResponse(r);
            return json.readValue(r.getResponseBody("UTF-8"), new TypeReference<List<TagGroupDto>>(){});
        } catch (Exception e) {
            throw wrapException(e);
        }
    }
}
