package com.example.greetings;

import com.example.greetings.model.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

//import java.awt.*;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

// what we need to do to run the application
// how do we add endpoints
	// what kind of endpoints can we add

// NOTE
// we have added a SQL dependency to our application to connect to a database in teh future
// rn we don't have a database, or want to connect to one
// we need to add a temp annotation to ignore teh dependency

@SpringBootApplication
@ComponentScan({"com.example.greetings"})
public class GreetingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingsApplication.class, args);

	}



}
