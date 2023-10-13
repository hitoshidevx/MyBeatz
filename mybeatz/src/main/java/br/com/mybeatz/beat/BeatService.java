package main.java.br.com.mybeatz.beat;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BeatService {

   @Autowired
    BeatRepository repository;

    public List<Beat> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var beat = repository.findById(id);
        if(beat.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Beat beat) {
        repository.save(beat);
    }

}