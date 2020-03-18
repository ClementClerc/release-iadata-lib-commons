/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.toulouse.iadata.datamodels.models.ngsi;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * This class represents NGSI Property described here
 * <a href = "https://www.etsi.org/deliver/etsi_gs/CIM/001_099/009/01.01.01_60/gs_CIM009v010101p.pdf"/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors( prefix = {"_"})
@NoArgsConstructor
public class Property extends EntityMember
{
    @Builder
    public Property( String name, URI datasetId, String observedAt, Map<String, EntityMember> members, Object value, String unitCode) {
        super(name, datasetId, observedAt, TYPE, members);
        _value = value;
        _datasetId = datasetId;
        _unitCode = unitCode;
    }

    private static final String TYPE = "Property";
    
    Object _value;
    
    private URI _datasetId;
    
    private String _unitCode;

    @Override
    public void setType() {
       _type = TYPE;
    }

    public static class PropertyBuilder
    {
        public PropertyBuilder addMember( EntityMember member) {
            if (members == null) {
                members = new HashMap<>();
            }
            if ( member != null )
            {
                members.put( member.getName(), member);
            }
            return this;
        }
    }
    
}
