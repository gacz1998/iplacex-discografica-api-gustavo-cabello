package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {

    public static final Object Artista = null;
    @Autowired
    private IArtistaRepository artistaRepo;

    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista temp = artistaRepo.insert(artista);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @SuppressWarnings("null")
    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> temp = artistaRepo.findById(id);

        if (!temp.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> HandleGetAllArtistasRequest() {
        List<Artista> artistas = artistaRepo.findAll();
        return new ResponseEntity<>(artistas, null, HttpStatus.OK);
    }

    @SuppressWarnings({ "null", "rawtypes", "unchecked" })
    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(
        @PathVariable("id") String id,
        @RequestBody Artista artista
    ) {
        Optional<Artista> temp = artistaRepo.findById(id);

        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        artista._id = id;
        Artista tem = artistaRepo.save(artista);
        return new ResponseEntity<>(tem, null, HttpStatus.OK);
    }

    @DeleteMapping(
        value = "/artista/{id}"
    )
    public ResponseEntity<Void> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        artistaRepo.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
