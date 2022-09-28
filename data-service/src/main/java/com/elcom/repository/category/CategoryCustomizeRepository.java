package com.elcom.repository.category;

import com.elcom.model.Category;
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
public class CategoryCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCustomizeRepository.class);

    private SessionFactory sessionFactory;

    @Autowired
    public CategoryCustomizeRepository (EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public Category findById(String categoryID )
    {
        Session session = sessionFactory.openSession();
        try
        {
            Category Category = session.load(Category.class, categoryID);
            return Category;
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

    public List<Category> findAll()
    {
        Session session = openSession();
        List<Category> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM category",Category.class);

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
