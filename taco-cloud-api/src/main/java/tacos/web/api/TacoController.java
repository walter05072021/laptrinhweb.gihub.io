package tacos.web.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Ingredient;
import tacos.Taco;
import tacos.data.TacoRepository;
    @RestController
    @RequestMapping(path = "/tacos", produces = "application/json")
    @CrossOrigin(origins = "*")
    public class TacoController {
        private TacoRepository tacoRepo;
        EntityLinks entityLinks;
        public TacoController(TacoRepository tacoRepo) {
            this.tacoRepo = tacoRepo;
        }
        @GetMapping
        public Iterable<Taco> getAllTacos() {
            return tacoRepo.findAll();
        }
        @GetMapping("/current") 
        public Taco tacoByMaxId() {
        	Taco taco = tacoRepo.findTopByOrderByIdDesc();
        	return taco;
        }
        @GetMapping("/{id}")
        public Taco tacoById(@PathVariable("id") Long id) {
            Optional<Taco> optTaco = tacoRepo.findById(id);
            if (optTaco.isPresent()) {
                return optTaco.get();
            }
            return null;
        }
        
        @PostMapping(consumes = "application/json")
    	@ResponseStatus(HttpStatus.CREATED)
    	public Taco postTaco(@RequestBody Taco taco) {
    		return tacoRepo.save(taco);
    	}
    	
//    	@PutMapping(path = "/{ingredientId}") 
//    	public Taco putTaco(@RequestBody Taco taco) { 
//    		return tacoRepo.save(taco); 
//    	} 
        
    }