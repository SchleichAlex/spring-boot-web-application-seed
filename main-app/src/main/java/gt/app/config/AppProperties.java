package gt.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app-properties", ignoreUnknownFields = false)
@Data
public class AppProperties {

    final FileStorage fileStorage = new FileStorage();
    final JmsProps jms = new JmsProps();

    @Data
    public static class FileStorage {
        String uploadFolder;
    }

    @Data
    public static class JmsProps {
        String profanityCheckerRequestQueue;
        String profanityCheckerCallBackResponseQueue;
    }


}
