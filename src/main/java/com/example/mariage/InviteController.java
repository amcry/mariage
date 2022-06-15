package com.example.mariage;

import java.lang.module.ResolutionException;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@OpenAPIDefinition(info = @Info(title = "Mariage API", version = "1.00", description = " Pour organiser mon mariage j’ai réalisé une application mobile et un site web afin de gérer la présence des invités.  Ils vont pouvoir y notifier, modifier, ou supprimer leur présence. Ils peuvent être présent à la cérémonie et/ou au vin d’honneur et/ou au repas. Ils auront le choix entre 3 plats au repas : bœuf, poisson ou végé. Je souhaiterais voir la liste de mes invités, leur présence et le plat qu’ils ont choisi. J’ai besoin d’une API RESTfull pour gérer les données en base. Je veux également une sécurité qui ne me permettrais qu’à moi de communiquer avec l’API."))

@RestController
public class InviteController {

    @Autowired
    private InviteRepository repository;

    @GetMapping(value = "/saveValue")
    @Operation(summary = "Enregistré des invités", description = "Lors de l'execution, on ajoute des données dans la bdd ", responses = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public String getDate() {
        saveInvite();
        System.out.println("invité ajouté");
        return new Date().toString();
    }

    private ResponseEntity<String> erreur(String id) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("aucun invite avec : " + id);
    }

    // @PostMapping(value = "/date1")
    // public String postDate(@RequestBody String body) {
    // return "Received body : " + body;
    // }

    // @PostMapping(value = "/date2")
    // public String postInvite(@RequestBody Invite invite) {
    // return "Nom des invités : " + invite.getNom();
    // }

    // tous les invites dans le terminal
    // @GetMapping(value = "/prenom")
    // public String getPrenom() {
    // showInvite();
    // return toString();
    // }

    @PostMapping(value = "deleteInviteBy")
    @Operation(summary = "Supprimer des invités", description = "Lors de l'execution, on supprime des données dans la bdd par le biais de l'id, avec POST", responses = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })

    public void postDeleteInviteById(String id) {
        repository.deleteById(id);
    }

    @GetMapping(value = "/invites")
    @Operation(summary = "affiche tous les invités", description = "Lors de l'execution, on affiche tous les invités au format Json, des données de la bdd ", responses = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<Iterable<Invite>> getInvites() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/UpdateInvite/{inviteId}")
    public ResponseEntity<String> putInvite(String id, @Valid @RequestBody Invite invite) {

        Optional<Invite> inviteToUpdate = repository.findById(id);

        if (inviteToUpdate.isEmpty()) {
            return erreur(id.toString());
        }
        invite.setPrenom();
        repository.save(invite);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("mise a jour de l'invité : " + id);
    }

    @DeleteMapping("/DeleteInvite/{inviteId}")
    @Operation(summary = "Supprimer des invités", description = "Lors de l'execution, on supprime des données dans la bdd par le biais de l'id, avec DELETE", responses = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<String> deleteInvite(String id) {

        Optional<Invite> inviteToDelete = repository.findById(id);

        if (inviteToDelete.isEmpty()) {
            return erreur(id.toString());
        }

        repository.delete(inviteToDelete.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("supprimer : " + id);

    }

    // essai

    // @PutMapping("/updateInvite/{id}")
    // @Operation(summary = "Mise à jour d'un invités", description = "Lors de
    // l'execution, on met a jour un invité par le biais de son id, des données de
    // la bdd", responses = {
    // @ApiResponse(responseCode = "204", description = "OK"),
    // @ApiResponse(responseCode = "400", description = "Bad Request") })

    // public ResponseEntity<String> updateInvite(String id,
    // @Valid @RequestBody Invite inviteDetails) throws ResolutionException {
    // repository.findById(id);

    // inviteDetails.setPrenom(inviteDetails.getPrenom());
    // inviteDetails.setNom(inviteDetails.getNom());
    // final Invite updatedInvite = repository.save(inviteDetails);
    // return ResponseEntity.ok(updatedInvite);
    // }

    // @DeleteMapping("/deleteInvite/{id}")
    // @Operation(summary = "Supprimer des invités", description = "Lors de
    // l'execution, on supprime des données dans la bdd par le biais de l'id, avec
    // DELETE", responses = {
    // @ApiResponse(responseCode = "204", description = "OK"),
    // @ApiResponse(responseCode = "400", description = "Bad Request")
    // })
    // public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") String
    // inviteId) {
    // // Access the DB and delete the order
    // return ResponseEntity.ok(inviteId);
    // }
    // -------------------------------------------------------------------------------------------------------------------------

    public void saveInvite() {
        // save a couple of customers
        repository.save(new Invite("Bond", "James", "boeuf", false, false, true));
        repository.save(new Invite("Cameron", "James", "vegan", false, false, true));
        repository.save(new Invite("Royal", "Michel", "", true, false, false));
        repository.save(new Invite("Hannibal Smith", "John", "", false, true, false));
        repository.save(new Invite("McClane", "John", "poisson", false, false, true));
        repository.save(new Invite("Douglas", "Micheal", "boeuf", true, true, true));
        repository.save(new Invite("Estwood", "Clint", "vegan", true, false, true));
        repository.save(new Invite("T", "Mr", "", false, false, false));
    }

    public void showInvite() {
        System.out.println("Liste des invités et leurs choix");
        System.out.println("--------------------------------");

        for (Invite invite : repository.findAll()) {
            System.out.println(invite.toString());
        }
    }

    public void StatInvite() {
        // nb invite ?
        // nb plat / nb poisson ,nb boeuf, nb vegan
        // nb au repas
        // nb ceremonie
        // nb vin
    }

    public void deleteInvite() {
        repository.deleteAll();
    }

}
