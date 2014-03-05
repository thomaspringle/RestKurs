package se.altran.restkurs.webapi;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

@Provider
@Singleton  
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {  
  
    static final Logger LOG = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);
      
    @Override  
    public Response toResponse(JsonMappingException e) {  
        LOG.error("Json Parsing Exception.", e);  
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();  
    }  
  
}  