package cz.czechitas.java2webapps.lekce11.controller;

import cz.czechitas.java2webapps.lekce11.entity.Kniha;
import cz.czechitas.java2webapps.lekce11.service.KnihaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Filip Jirsák
 * Vytvořím si controller, ale dám mu anotaci @RestController. Tím říkám, že to bude controller pro backend. Tzn.
 * nebude vytvářet html stránky ze šablon, ale bude vytvářet JSON objekty a bude je přijímat.
 */
@RestController
@RequestMapping("/api")
public class KnihaController {
  private final KnihaService service;

  @Autowired
  public KnihaController(KnihaService service) {
    this.service = service;
  }

  /**
   * Příklad použití vlastní http metody FIND. Název metody se uvádí do parametru method.
   */
//  @RequestMapping(method = "FIND")

  /**
   * Metoda vrací jen samotná data ve formátu JSON. Nevrací ModelAndView.
   * Metoda může mít další parametry, např. filtr na omezení výběru z databáze.
   */
  @GetMapping("")
  public Page<Kniha> index(Pageable pageable) {
    return service.seznam(pageable);
  }

  @GetMapping(path="", params = "vcetneStornovanych=true")
  public Page<Kniha> vcetneStornovanych(Pageable pageable) {
    return service.seznamVcetneStornovanych(pageable);
  }

  /**
   * Služba vrací objekt typu Kniha, který se převede zpátky na JSON. Dělá se to tak proto, že při zápisu do databáze
   * se údaje můžou pozměnit. Např. se přidělí id a verze. Takže vrácený objekt je trochu jiný. Metoda uloží data do
   * databáze, ale současně vrátí uložený záznam s doplněnými dalšími poli (např. id, version nebo ISBN).
   */
  @PostMapping("")
  public Kniha pridat(@RequestBody Kniha kniha) {
    return service.pridat(kniha);
  }
  /**
   * Spring díky hlavičce v http požadavku, tj. Content-Type: application/json ví, že jsou data ve formátu JSON. A pak
   * si je už správně namapuje do parametru metody, např. List<Kniha>
   * Anotace @RequestBody říká, že data se mají načíst z body (těla) požadavku.
   * Metoda načte z body požadavku seznam knížek. Spring se postará o to, že mi to převede na objekty typu Kniha a na
   * seznam. Pak se volá služba (service), která umí data uložit. Metoda spouští v sample.http a data se předávají jako
   * JSON (tj. pole objektů).
   */
  @PostMapping("batch")
  public List<Kniha> pridatDavkove(@RequestBody List<Kniha> kniha) {
    return service.pridatDavkove(kniha);
  }

  @DeleteMapping("{id}")
  public Kniha smazat(@PathVariable long id) {
    return service.smazat(id);
  }

  //TODO: Dokončit - na obnovení chybí tlačítko. Dá se spustit jen http požadavek
  @PostMapping("{id}/obnovit")
  public Kniha obnovit(@PathVariable long id) {
    return service.obnovit(id);
  }

// TODO: Upravení - tlačítko nefunguje správně, http požadavek nefunguje
//  @PutMapping ("upravit")
//  public Kniha upravit(@RequestBody Kniha kniha) {
//    return service.upravit(kniha);
//  }

  //Detail knížky
  @GetMapping("detail/{id}")
  public Optional<Kniha> detail(@PathVariable long id) {
    return service.detail(id);
  }



}



