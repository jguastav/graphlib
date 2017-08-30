
import com.google.gson.Gson;
import com.onelake.api.error.OnelakeException;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonParser<T> {
    private final Logger logger = LoggerFactory.getLogger(JsonParser.class);
    public T parseJson(String json, Class<T> c) throws OnelakeException {
        Objects.requireNonNull(json);
        try {
            Gson gson = new Gson();
            return (T) gson.fromJson(json, c);
        } catch (Exception e) {
            logger.info(WorkflowErrorCode.JsonParserError.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.JsonParserError, e).set("json", json).set("class", c);
        }
    }
}