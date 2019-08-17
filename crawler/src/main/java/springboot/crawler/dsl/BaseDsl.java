package springboot.crawler.dsl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author renhuibo  2019-08-12 23:07:45
 * @Description
 */
public class BaseDsl {
	
	public JPAQueryFactory queryFactory;

    @Autowired
    @PersistenceContext
    public EntityManager entityManager;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
