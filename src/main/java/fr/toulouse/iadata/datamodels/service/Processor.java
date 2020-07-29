package fr.toulouse.iadata.datamodels.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;
import java.util.List;

@Data
public class Processor
{
    @Id
    private String id;
    String key;
    List<String> customArgs = new ArrayList<>() ;
    List<String> activatedKeys = new ArrayList<>();
    int order;
}
