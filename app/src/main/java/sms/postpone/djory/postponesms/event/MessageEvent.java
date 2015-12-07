package sms.postpone.djory.postponesms.event;

import java.util.List;

import sms.postpone.djory.postponesms.model.Message;

public class MessageEvent {
    private final List<Message> messages;

    public MessageEvent(List<Message> messages) {
        this.messages= messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
