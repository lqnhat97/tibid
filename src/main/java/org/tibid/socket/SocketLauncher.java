package org.tibid.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.tibid.dto.BidOrderDto;

public class SocketLauncher {

    @Value("${socket.hostname}")
    private static String hostName;

    @Value("${socket.port}")
    private static int port;

    public static void start() {
        Configuration config = new Configuration();
        config.setHostname(hostName);
        config.setPort(port);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("ticket_event", BidOrderDto.class, (client, data, ackRequest) -> {
            // broadcast messages to all clients
            server.getBroadcastOperations().sendEvent("order_event", data);
        });

        server.start();
    }
}
