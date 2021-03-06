package is.bokun.client;

import is.bokun.dtos.CountryDto;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.inject.Inject;
import com.ning.http.client.Response;

/**
 * Client for the country resource.
 *
 * @author Olafur Gauti Gudmundsson
 */
public class CountryClient extends AbstractClient {

    private static final String BASE = "/country.json";

    /**
     * @see AbstractClient#()
     *
     * @param config
     */
    @Inject
    public CountryClient(ClientConfiguration config) {
        super(config);
    }

    /**
     * Get a list of all countries the system knows about.
     * @return a List of country objects, each containing the title and the ISO code for that country.
     */
    public List<CountryDto> findAll() {
        try {
            String uri = BASE + "/findAll";
            Response r = prepareGet(uri).execute().get();
            validateResponse(r);

            return json.readValue(r.getResponseBody("UTF-8"), new TypeReference<List<CountryDto>>(){});

        } catch (Exception e) {
            throw wrapException(e);
        }
    }
}
