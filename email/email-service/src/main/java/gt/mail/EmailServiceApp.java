package gt.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class EmailServiceApp {

    public static void main(String[] args) throws UnknownHostException {

        SpringApplication app = new SpringApplication(EmailServiceApp.class);
        Environment env = app.run(args).getEnvironment();

        log.info("""
                        Access URLs:
                        ----------------------------------------------------------
                        \tLocal: \t\t\thttp://localhost:{}
                        \tExternal: \t\thttp://{}:{}
                        \tEnvironment: \t{}\s
                        ----------------------------------------------------------""",
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            Arrays.toString(env.getActiveProfiles())
        );
    }

}
