package com.mertalptekin.springbootrestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommitService {

    // artık primary bakma
    @Qualifier("dbCommit")
    @Autowired
    private ICommit commit;



    public void save() {
        commit.commitChanges();
    }


}
