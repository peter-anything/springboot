package com.galaxy.venus.rpc.test.service;

import java.util.List;

public interface PersonService {
    List<Person> GetTestPerson(String name, int num);
}
