package com.krustyburger.order.backend.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>{
 
	//TODO: I18N
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
 
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
 
        String formattedDate = dateFormat.format(date);
 
        gen.writeString(formattedDate);
    }
 
}
