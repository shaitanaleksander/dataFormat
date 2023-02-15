package org.example.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalSerializerJson {

    private final JsonMapper mapper;

    public AnimalSerializerJson() {
        this.mapper = new JsonMapper();
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
