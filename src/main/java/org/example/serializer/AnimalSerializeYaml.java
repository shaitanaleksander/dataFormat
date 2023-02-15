package org.example.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalSerializeYaml {

    private final YAMLMapper mapper;

    public AnimalSerializeYaml() {
        this.mapper = new YAMLMapper();
    }

    public void serialize(Animal animal, File file) throws IOException {
        this.mapper.writeValue(file, animal);

    }

    public Animal deserialize(File file) throws IOException {
        return this.mapper.readValue(file, Animal.class);
    }

    public List<Animal> deserializeList(File file) throws IOException {
        return this.mapper.readValue(file, new TypeReference<List<Animal>>(){});
    }
}
