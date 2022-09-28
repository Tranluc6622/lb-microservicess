package com.elcom.JsonDateSerializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializer) throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        gen.writeString(sdf.format(date));
    }
}
