package com.mongo.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@SpringBootTest
class MongoSpringbootApplicationTests {


    @Autowired
    private MongoTemplate mongoOps;

    @Test
    public void run(ApplicationArguments applicationArguments) throws Exception {

        Person p = new Person("Joe", 34);

        
        // 插入文档
        mongoOps.insert(p);
        System.out.println("Insert: " + p);

        // 查询文档
        p = mongoOps.findById(p.getId(), Person.class);
        System.out.println("Found: " + p);

        // 更新文档
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        System.out.println("Updated: " + p);

        // 删除文档
        mongoOps.remove(p);

        // Check that deletion worked
        List<Person> people = mongoOps.findAll(Person.class);
        System.out.println("Number of people = : " + people.size());
        mongoOps.dropCollection(Person.class);
    }


}
