package com.elcom.repository.author;

import com.elcom.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorCustomizeRepository.class);

    private SessionFactory sessionFactory;

    @Autowired
    public AuthorCustomizeRepository (EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public Author findById(String authorID )
    {
        Session session = sessionFactory.openSession();
        try
        {
            Author author = session.load(Author.class, authorID);
            return author;
        }
        catch (EntityActionVetoException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public List<Author> findAll()
    {
        Session session = openSession();
        List<Author> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM author",Author.class);

            result = query.getResultList();
        }
        catch(NoResultException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally{
            closeSession(session);
        }
        return result;
    }

    private Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    private void closeSession(Session session) {
        if( session.isOpen() ) {
            session.disconnect();
            session.close();
        }
    }
}
