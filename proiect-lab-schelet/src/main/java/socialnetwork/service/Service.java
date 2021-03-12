package socialnetwork.service;

import socialnetwork.domain.Nevoie;
import socialnetwork.domain.Persoana;
import socialnetwork.repository.Repository;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private Repository<Long, Persoana> persoanaRepo;
    private Repository<Long, Nevoie> nevoieRepo;


    public Service(Repository<Long, Persoana> persoanaRepo, Repository<Long, Nevoie> nevoieRepo) {
        this.nevoieRepo = nevoieRepo;
        this.persoanaRepo = persoanaRepo;
    }

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    @Override
    public void notifyObservers(){
        observers.stream().forEach(x->x.update());
    }

    public Persoana addPersoana(Persoana p){
        Persoana rez = persoanaRepo.save(p);
        if(rez == null){
            this.notifyObservers();
        }
        return rez;
    }

    public List<Persoana> getAllPersoana(){
        List<Persoana> list = new ArrayList<Persoana>();
        persoanaRepo.findAll().forEach(list::add);
        return list;
    }
    
    public List<Nevoie> getAllNevoie(){
        List<Nevoie> list = new ArrayList<Nevoie>();
        nevoieRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Nevoie> getAllNevoieByOras(Persoana p){
        List<Nevoie> nevoie = new ArrayList<>();

        for(Nevoie n : getAllNevoie()){
            if(persoanaRepo.findOne(n.getOmInNevoie()) != null && persoanaRepo.findOne(n.getOmInNevoie()).getOras().equals(p.getOras()) && !p.getId().equals( n.getOmInNevoie())){
                nevoie.add(n);
            }
        }
        return nevoie;
    }

    public Nevoie updateNevoie(Nevoie n){
        Nevoie rez = nevoieRepo.update(n);
        if(rez == null){
            this.notifyObservers();
        }
        return rez;
    }

    public List<Nevoie> getAllNevoieByPersoana(Persoana persoana) {
        List<Nevoie> nevoie = new ArrayList<>();

        for(Nevoie n : getAllNevoie()){
                if(n.getOmSalvator() != null && n.getOmSalvator().equals(persoana.getId())) {
                    nevoie.add(n);
                }
        }
        return nevoie;

    }

    public Nevoie addNevoie(Nevoie p){
        Nevoie rez = nevoieRepo.save(p);
        if(rez == null){
            this.notifyObservers();
        }
        return rez;
    }
    
/*
    //exp: hotels by location
    //     doi       persoana
    public List<Doi> doiByPersoana(Long ID){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getPersoana_id().equals(ID))
                list.add(e);
        }
        return list;
    }

    public List<Persoana> getAllPersoana(){
        List<Persoana> list = new ArrayList<Persoana>();
        persoanaRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Doi> getAllDoi(){
        List<Doi> list = new ArrayList<Doi>();
        doiRepo.findAll().forEach(list::add);
        return list;
    }

    //exp: hotels by location
    //     doi       persoana
    public List<Doi> doiByPersoanaDates(Long ID, LocalDateTime start, LocalDateTime end){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getPersoana_id().equals(ID) && e.getFirst_date().isAfter(start) && e.getSecond_date().isBefore(end))
                list.add(e);
        }
        return list;
    }

    public List<DoiDTO> personalizate(Persoana entity){
        List<DoiDTO> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(true){//e.getFirst_date().isAfter(LocalDateTime.now()) && e.getPersoana_id().equals(entity.getId())){
                Persoana entitate = persoanaRepo.findOne(e.getPersoana_id());
                DoiDTO dto = new DoiDTO(e.getFirst_myenum(), e.getSecond_myenum(), e.getId(), entitate, e.getFirst_string(), e.getSecond_string(), e.getFirst_Integer(), e.getSecond_Integer(), e.getFirst_integer(), e.getSecond_integer(), e.getFirst_Long(), e.getSecond_Long(), e.getFirst_long(), e.getSecond_long(), e.getFirst_Double(), e.getSecond_Double(), e.getFirst_double(), e.getSecond_double(), e.getFirst_date(), e.getSecond_date());
                list.add(dto);
            }
        }
        return list;
    }

 */

}