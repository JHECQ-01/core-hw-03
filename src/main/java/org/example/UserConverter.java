package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class UserConverter {

    public static void main(String[] args) {
        String inputFileName = "user.txt";
        String outputFileName = "user.json";
        convertTextToJson(inputFileName, outputFileName);
    }

    public static void convertTextToJson(String inputFileName, String outputFileName) {
        List<User> userList = new ArrayList<>();

        try (InputStream inputStream = UserConverter.class.getClassLoader().getResourceAsStream(inputFileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                userList.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
