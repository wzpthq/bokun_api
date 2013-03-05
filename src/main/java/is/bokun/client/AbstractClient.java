package is.bokun.client;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.ning.http.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import is.bokun.dtos.ApiResponse;
import is.bokun.utils.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Contains common aspects of all the client classes.
 *
 * @author Olafur Gauti Gudmundsson
 */
public abstract class AbstractClient {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    protected static final ObjectMapper json = new ObjectMapper();

    protected final ClientConfiguration config;

    /**
     * The general constructor for the REST clients.
     *
     * @param config The configuration for the client.
     */
    protected AbstractClient(ClientConfiguration config) {
        this.config = config;
    }

    public ClientConfiguration getConfig() {
        return config;
    }

    /**
     * This method appends the Bokun headers and security signature to the request.
     * <br/><br/>
     * X-Bokun-Date         : The date the request was created in UTC, formatted as "yyyy-MM-dd HH:mm:ss"<br/>
     * X-Bokun-AccessKey    : The access key identifying the caller<br/>
     * X-Bokun-Signature    : The HMAC signature to be validated<br/>
     *
     * @param r the request
     * @param method the HTTP method being used
     * @param uri the path + querystring to the REST service being called
     */
    protected void addSecurityHeaders(AsyncHttpClient.BoundRequestBuilder r, String method, String uri) {
        r.addHeader("Content-type", "application/json; charset=utf-8");

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        r.addHeader("X-Bokun-Date", date);
        r.addHeader("X-Bokun-AccessKey", config.getAccessKey());

        StringBuilder signatureInput = new StringBuilder();
        signatureInput.append(date);
        signatureInput.append(config.getAccessKey());
        signatureInput.append(method.toUpperCase());
        signatureInput.append(uri);

        r.addHeader("X-Bokun-Signature", calculateHMAC(config.getSecretKey(), signatureInput.toString()));
    }

    public static class NVP {
        public String name;
        public String value;

        public NVP(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    protected String appendQueryParams(String uri, NVP... params) {
        StringBuilder s = new StringBuilder();
        for (NVP p : params) {
            if ( !StringUtils.isNullOrEmpty(p.value) ) {
                if ( s.length() == 0 ) {
                    s.append('?');
                } else {
                    s.append('&');
                }
                s.append(p.name);
                s.append('=');
                s.append(p.value);
            }
        }
        return uri + s.toString();
    }

    protected String appendLangAndCurrency(String uri, String lang, String currency, NVP... params) {
        List<NVP> list = new ArrayList<NVP>();
        list.add(new NVP("lang",lang));
        list.add(new NVP("currency",currency));
        if ( params != null ) {
            for (NVP p : params) {
                list.add(p);
            }
        }
        return appendQueryParams(uri, list.toArray(new NVP[list.size()]));
    }

    protected String appendLangAndCurrency(String uri, String lang, String currency) {
        return appendLangAndCurrency(uri, lang, currency, null);
    }

    /**
     * Validates the response. If the status code is not OK (200), a RestServiceException
     * will be thrown, containing the API response. The caller must handle this exception.
     *
     * @param r the response to be validated
     */
    protected void validateResponse(Response r) {
        if ( r.getStatusCode() != 200 ) {
            // try parsing an API response
            try {
                ApiResponse ar = json.readValue(r.getResponseBody("UTF-8"), ApiResponse.class);
                throw new RestServiceException(ar);
            } catch (Exception e) {
                ApiResponse ar = new ApiResponse(r.getStatusText());
                throw new RestServiceException(ar, e);
            }
        }
    }

    /**
     * Wraps an exception in an RestServiceException.
     * Generally used to handle exceptions that occur in the client code.
     *
     * @param t the exception that occurred
     * @return a RestServiceException wrapping the original exception
     */
    protected RestServiceException wrapException(Throwable t) {
        ApiResponse ar = new ApiResponse(t.getMessage());
        return new RestServiceException(ar, t);
    }

    /**
     * Calculates the HMAC signature for the data supplied, using the secret key.
     *
     * @param secret the secret key (as specified in the Bokun API key)
     * @param data the input data
     * @return the calculated HMAC
     */
    private String calculateHMAC(String secret, String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(),	HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return new String(Base64.encode(rawHmac));
        } catch (GeneralSecurityException e) {
            //("Unexpected error while creating hash: " + e.getMessage(), e);
            throw new IllegalArgumentException();
        }
    }
}