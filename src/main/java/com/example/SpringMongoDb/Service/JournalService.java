package com.example.SpringMongoDb.Service;

import com.example.SpringMongoDb.Repo.JournalDB;
import com.example.SpringMongoDb.model.MyJournal;
import com.example.SpringMongoDb.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {


    @Autowired
    private JournalDB repo;

    @Autowired
    private UserService userService;

    public List<MyJournal> getAll() {
       return repo.findAll();
    }


    @Transactional
    public void createEntry(MyJournal journal, String username) {
        try {
            User user = userService.findByUserName(username);
            MyJournal saved =  repo.save(journal);
            user.getJournalEntries().add(saved);
            userService.updateUser(user);

        }
        catch(Exception e){
            throw new RuntimeException("An error occurred while saving entry",e);
        }


    }

    //put   mapping ka h ye (this code is getting confusing man)
    public void createEntry(MyJournal journal) {
        repo.save(journal);

    }



    public Optional<MyJournal> getEntryBYid(ObjectId id) {
        return repo.findById(id);
    }

    public void deletebyId(ObjectId id, String username) {
        try {
            User user = userService.findByUserName(username);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.updateUser(user);
                repo.deleteById(id);
            }
        }
        catch (Exception e){
            throw new RuntimeException("the item did not removed " , e);
        }
    }

    public MyJournal updateEntryById(ObjectId id,MyJournal entry) {
        return repo.save(entry);
    }


}
