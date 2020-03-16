/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * @author cu33443
 */
@Data
@Accessors( prefix = {"_","_str"})
@JsonInclude(Include.NON_NULL)
public class DataObject <T extends Member>
{
    
    @Id
    @JsonIgnore
    private String _idTech;
    
    @JsonProperty( "id")
    private URI _uriId;
    
    private String _type;
    
    // COMMON MEMBERS
    private String _createdAt;
    
    private String _modifiedAt;
    
    /////////////////////////////
    // LOCATION FIELDS
    private GeoProperty _location;

    private GeoProperty _observationSpace;
    
    private GeoProperty _operationSpace;
    
    // NESTED FIELDS
    private Map< String, T > _members = new HashMap<>();
    
    // CONTEXT
    @JsonProperty( "@context")
    @JsonDeserialize(using = ContextDeserializer.class)
    @JsonSerialize(using = ContextSerializer.class)
    private List<Context> _contexts;
    
    @JsonTypeInfo(  
    use = JsonTypeInfo.Id.NAME,  
    include = JsonTypeInfo.As.EXISTING_PROPERTY,  
    property = "type",
    visible = true, 
    defaultImpl = PropertyValue.class )  
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Property.class, name = "Property"),  
        @JsonSubTypes.Type(value = PropertyValue.class, name = "PropertyValue"),  
        @JsonSubTypes.Type(value = Relationship.class, name = "Relationship"),  
        @JsonSubTypes.Type(value = GeoProperty.class, name = "GeoProperty")}) 
    @JsonAnySetter
    public void setMember( String name, T value) {
        value.setName( name );
	_members.put(name, value);
    }
    
    @JsonAnyGetter
    public Map<String,T> getMembers( ) {
        return _members;
    }
}
