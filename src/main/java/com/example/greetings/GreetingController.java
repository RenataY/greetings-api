package com.example.greetings;

import com.example.greetings.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin(origins  =  "http://localhost:3001")
public class GreetingController {
    // in order to create an endpoint we need
    // an annotation to detail
    // the location of the endpoint and,
    // the associated HTTP method
    // a method that runs when that endpoint is "hit"/ when a request is made to that endpoint

    // GET all greetings
    // GET a random greeting
    // 'GET a specific greeting"
    // POST a new greeting

    //List<Greeting> greetings = new ArrayList<>();

    //dependency injection
        // avoids us needing to make new instance
    @Autowired
    GreetingRepository repository;

    // path variables & request parameters
    // why would we need these?
    // pass information on GET requests, from the client (REACT) to the server ( SpringBoot)

    // challenge
    // add two more endpoints
    // one for getting a greeting by its id
    // one for saying hello back to someone
    // const getGreeting = ()

    @GetMapping("/greeting/{id}")
    public Optional<Greeting> getGreetingById(@PathVariable int id) {
        // what made up my response
            // status code
            // body - actual greeting
            // headers - additional info re the request and response

        // ResponseEntity
            // we can configure our entire response using this
                // .status() configure the status code we receive
        return Optional.ofNullable((repository.findByid(id)));
        //return ResponseEntity.status(HttpStatus.OK).body((repository.findByid(id)));
        // ...
    }
    // looks for a query sign, an ?, and reads from after that

    //make an endpoint that returns a List of all the greetings
    @GetMapping("/greetings")
    public ResponseEntity<List<Greeting>> getListOfGreetings() {
        //return repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body((repository.findAll()));
    }


	@GetMapping("/randomgreeting")
	public ResponseEntity<Optional<Greeting>> getRandomGreeting() {
		Random RANDOM = new Random();
        Optional<Greeting> randomGreeting = Optional.ofNullable(repository.findByid(RANDOM.nextInt((int) repository.count())));
        //System.out.println(randomGreeting);
        //System.out.println(repository.count());
        //return randomGreeting;
        return ResponseEntity.status(HttpStatus.OK).body((randomGreeting));
		//return greetings.get(RANDOM.nextInt(greetings.size()));


	}

    // CREATE route
    @PostMapping("/greetings")
    public String createGreeting(@RequestBody Greeting greeting) {
        //greeting.setCreatedBy("Rinata");
        //greeting.setId(greetings.size()+1);
        //greeting.setDateCreated(new Timestamp(System.currentTimeMillis()));
        //greetings.add(greeting);
        repository.save(greeting);
        return greeting + " added";
    }
    // DELETE by index
//	@DeleteMapping("/greetings/{id}")
//	public String deleteGreeting(@PathVariable int id) {
//		greetings.remove(id);
//		return "Greeting with id: " + id + " deleted";
//	}

    // DELETE route
    @Transactional
    @DeleteMapping("/greetings/{id}")
    public String deleteGreeting(@PathVariable int id) {
//        greetings.remove(greetings.get(id));
        repository.delete(repository.findByid(id));
        return "Greeting with id: " + id + " deleted.";

    }
    @PutMapping("/greetings/{id}")
    public Greeting updateGreeting(@PathVariable int id, @RequestBody Greeting newGreeting) {
        // change the greeting
        //set the greeting back into the list
        //newGreeting.setId(id);
        newGreeting = repository.findByid(id);
        newGreeting.setGreeting("Hello, its Monday");
        newGreeting.setCreatedBy("Sparky");
        repository.save(newGreeting);

//        newGreeting.setDateCreated(new Timestamp(System.currentTimeMillis()));
//        repository.set();
//        greetings.set(greetings.indexOf(greetings.stream().filter(greeting -> greeting.getId()==id).findFirst().get()),newGreeting);
        return newGreeting;
    }

//    @PutMapping("/greetings/{id}")
//    public Greeting updateGreeting(@PathVariable int id, @RequestBody Greeting newGreeting) {
//        // change the greeting
//        //set the greeting back into the list
//        //newGreeting.setId(id);
//        newGreeting.setCreatedBy("Rinata1");
//        newGreeting.setDateCreated(new Timestamp(System.currentTimeMillis()));
//        greetings.set(greetings.indexOf(greetings.stream().filter(greeting -> greeting.getId()==id).findFirst().get()),newGreeting);
//        return newGreeting;
//    }

}
