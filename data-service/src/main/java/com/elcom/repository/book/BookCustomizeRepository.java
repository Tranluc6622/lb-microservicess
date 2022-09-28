package com.elcom.repository.book;

import com.elcom.model.Book;
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
public class BookCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCustomizeRepository.class);

    private SessionFactory sessionFactory;

    @Autowired
    public BookCustomizeRepository (EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public Book findById(Integer bookID )
    {
        Session session = sessionFactory.openSession();
        try
        {
            Book book = session.load(Book.class, bookID);
            return book;
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
    public List<Book> findAll()
    {
        Session session = openSession();
        List<Book> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM book",Book.class);

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
