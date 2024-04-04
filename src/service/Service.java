package service;
import Domain.Meal;

import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }
    public void add(Meal item){
        repo.add(item);
    }
    public List<Meal> getAll(){
        return repo.getAll();
    }
}
