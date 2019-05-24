package com.plf.learn.jetcache.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.plf.learn.jetcache.bean.Person;
import com.plf.learn.jetcache.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2019/5/24
 */
@Service
public class PersonServiceImpl implements PersonService {

    @CreateCache(name = "PersonService.personCache", expire = 3600, cacheType = CacheType.BOTH, localLimit = 50)
    private Cache<Integer, Person> personCache;

    @Override
    public Person getPersonById(Integer id) {
        Person person = personCache.get(id);
        if(person!=null){
            return person;
        }
        person = new Person(id,"Rush",23);
        personCache.put(id,person);
        return person;
    }

    @Override
    public void updatePerson(Person person) {
        personCache.put(person.getId(),person);
    }

    @Override
    public void deletePerson(Integer id) {
        personCache.remove(id);
    }
}
