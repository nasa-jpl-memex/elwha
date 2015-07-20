package gov.nasa.jpl.memex.elwha.impl;

import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;
import gov.nasa.jpl.memex.elwha.Elwha;

@RestLiCollection(name = "elwha", namespace = "gov.nasa.jpl.memex.elwha")
public class ElwhaResource extends CollectionResourceTemplate<Long, Elwha>
{
  @Override
  public Elwha get(Long key)
  {
    return new Elwha().setMessage("Hello, Rest.li!");
  }
}
