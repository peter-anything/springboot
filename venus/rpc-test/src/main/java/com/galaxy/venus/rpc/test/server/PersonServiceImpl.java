package com.galaxy.venus.rpc.test.server;

import com.galaxy.venus.rpc.server.RpcService;
import com.galaxy.venus.rpc.test.service.Person;
import com.galaxy.venus.rpc.test.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RpcService(PersonService.class)
public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> GetTestPerson(String name, int num) {
        List<Person> persons = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            persons.add(new Person(Integer.toString(i), name));
        }
        return persons;
    }
}