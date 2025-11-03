package com.mertalptekin.springbootrestapp.springContext.commit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommitService {

    // DIP CommitService hignLevel Class ICommit intterfaceden türüyen herhangi bir CacheCommit veya DbCommit lowLevel classlarını bilmemeli.
    // Depency Inversion Principle (DIP) uygulanmalı.
    private final ICommit commit;

    // DI -> @Qualifier("dbCommit") ICommit commit
    public CommitService(@Qualifier("dbCommit") ICommit commit) {
        this.commit = commit;
    }


    public void save() {
        commit.commitChanges();
    }


}
