//package org.tibid.socket;
//
//import com.google.gson.Gson;
//import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaders;
//import org.springframework.messaging.simp.stomp.StompSession;
//import org.springframework.messaging.simp.stomp.StompSessionHandler;
//
//import java.lang.reflect.Type;
//
//public class MyStompSessionHandler implements StompSessionHandler {
//
//    private Logger logger = LoggerFactory.getLogger(MyStompSessionHandler.class);
//
//    @Override
//    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//        logger.info("New session established : " + session.getSessionId());
//        session.subscribe("/topic/order/1", this);
//        logger.info("Subscribed to /topic/order/1");
//    }
//
//    @Override
//    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//        logger.error("Got an exception", exception);
//    }
//
//    @Override
//    public void handleTransportError(StompSession session, Throwable exception) {
//        logger.error("Got an transport error", exception);
//    }
//
//    @Override
//    public Type getPayloadType(StompHeaders headers) {
//        return Message.class;
//    }
//
//    @Override
//    public void handleFrame(StompHeaders headers, Object payload) {
//        Message msg = (Message) payload;
//        logger.info("Received : " + new Gson().toJson(msg.getPayload()) + " from : " + msg.getHeaders());
//    }
//
//}
