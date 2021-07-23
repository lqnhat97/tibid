package org.tibid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.tibid.socket.SocketLauncher;

@SpringBootApplication
@EnableJpaAuditing
public class TibidApplication {

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TibidApplication.class, args);
        SocketLauncher.start();
    }
}
