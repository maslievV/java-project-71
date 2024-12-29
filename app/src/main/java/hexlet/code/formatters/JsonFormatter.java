package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.DiffKeys;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String format(List<Map<DiffKeys, Object>> diffData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffData);
    }

}
