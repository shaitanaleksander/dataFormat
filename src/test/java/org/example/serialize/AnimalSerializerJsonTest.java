package org.example.serialize;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Animal;
import org.example.serializer.AnimalSerializerJson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalSerializerJsonTest {

    private final AnimalSerializerJson jsonSerialize = new AnimalSerializerJson();

    @Test
    public void serializeTest() throws IOException {

        Animal chip = Animal.builder()
                .age(2)
                .color("black")
                .name("Chip")
                .gender(true)
                .type("Labrador")
                .build();

        String fileName = "chip.json";
        File animalData = new File("src/main/resources/" + fileName);
        jsonSerialize.serialize(chip,animalData);

        Animal deserializedAnimal = jsonSerialize.deserialize(animalData);

        Assert.assertTrue(animalData.exists());
        Assert.assertEquals(animalData.getPath(),"src/main/resources/" + fileName);
        Assert.assertEquals(chip, deserializedAnimal);
//        Assert.assertTrue(chip.equals(deserializedAnimal));
        animalData.delete();
    }
    @Test
    public void deserializer() throws IOException {

        Animal chip = Animal.builder()
                .age(2)
                .color("black")
                .name("Chip")
                .gender(true)
                .type("Labrador")
                .build();

        String fileName = "chip.json";
        File animalData = new File("src/main/resources/" + fileName);
        this.jsonSerialize.serialize(chip,animalData);

        Animal deserialize = this.jsonSerialize.deserialize(animalData);

        Assert.assertEquals(chip,deserialize);
        animalData.delete();
    }

    @Test
    public void deserializeList() throws IOException {
        List<Animal> animals = List.of(
                Animal.builder()
                        .age(2)
                        .color("black")
                        .name("Chip")
                        .gender(true)
                        .type("Labrador")
                        .build(),

                Animal.builder()
                        .age(1)
                        .color("red")
                        .name("Adolf")
                        .gender(false)
                        .type("German Shepherd")
                        .build()
        );

        String fileName = "animals.json";
        new JsonMapper()
                .writeValue(new File("src/main/resources/"+ fileName),animals);

        List<Animal> animals1 = this.jsonSerialize.deserializeList(new File("src/main/resources/" + fileName));

        Assert.assertEquals(animals, animals1);
        new File("src/main/resources/" + fileName).delete();
    }
}
