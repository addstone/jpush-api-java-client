package cn.jpush.api.push.model;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class MessageTests {
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegal() {
        Message.newBuilder().build();
    }
    
    @Test
    public void testMsgContent() {
        Message message = Message.content("msg content");
        
        JsonObject json = new JsonObject();
        json.add("msg_content", new JsonPrimitive("msg content"));
        
        Assert.assertEquals("", json, message.toJSON());

    }

    @Test
    public void testMsgContentAndExtras() {
        Message message = Message.newBuilder()
                .setMsgContent("msgContent")
                .addExtra("key1", "value1")
                .addExtra("key2", 222)
                .addExtra("key3", Boolean.FALSE).build();
        
        JsonObject json = new JsonObject();
        json.add("msg_content", new JsonPrimitive("msgContent"));
        
        JsonObject extras = new JsonObject();
        extras.add("key1", new JsonPrimitive("value1"));
        extras.add("key2", new JsonPrimitive(222));
        extras.add("key3", new JsonPrimitive(Boolean.FALSE));
        
        json.add("extras", extras);
        
        Assert.assertEquals("", json, message.toJSON());

    }

}
