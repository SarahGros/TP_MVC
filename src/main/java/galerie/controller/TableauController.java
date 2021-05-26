package galerie.controller;

import galerie.dao.ArtisteRepository;
import galerie.dao.TableauRepository;
import galerie.entity.Tableau;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/tableau")
public class TableauController {

    private TableauRepository tabRepo;
    private ArtisteRepository artRepo;

    // a l'init on recup les repo
    public TableauController(TableauRepository tabRepo, ArtisteRepository artRepo){
        this.tabRepo=tabRepo;
        this.artRepo=artRepo;
    }

    @GetMapping(path = "afficherListeTableaux")
    public String afficherTousLesTableaux(Model model) {
        model.addAttribute("tableaux", tabRepo.findAll());
        return "affichageTableaux";
    }

    @PostMapping(path = "sauvegarderLeTableauSansAuteur")
    public String addTableauSansAuteur(Tableau tableau) {
        tabRepo.save(tableau);
        return "redirect:afficherListeTableaux";
    }

    @PostMapping(path = "sauvegarderLeTableau")
    public String addTableau(Tableau tableau) {
        tabRepo.save(tableau);
        return "redirect:afficherListeTableaux";
    }

    @GetMapping(path = "ajouterTableau")
    public String addTableauForm(@ModelAttribute("tableau") Tableau tableau, Model model) {
        model.addAttribute("artistes", artRepo.findAll());
        return "formAjoutTableau";
    }

    @GetMapping(path = "ajouterTableauSansAuteur")
    public String addTableauFormSansAuteur(@ModelAttribute("tableau") Tableau tableau, Model model) {
        model.addAttribute("artistes", artRepo.findAll());
        return "formAjoutTableauSansAuteur";
    }

    @GetMapping(path = "supprimerTableau")
    public String supprimeUnTableauPuisMontreLaListe(@RequestParam("id") Tableau tableau) {
        tabRepo.delete(tableau);
        return "redirect:afficherListeTableaux";
    }
    
}
