package com.spring.repository;

import com.spring.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//Spring e repo classımız olduğunu tanıttık içerisinde @component içeriyor
public class StudentRepoImp implements StudentRepository{

    @Autowired// DB katmanında olduğumuzdan sF bağımlılığımızıı enjekte ettik
    private SessionFactory sessionFactory;


    @Override
    public List<Student> getAll() {
       Session session= sessionFactory.openSession();
       Transaction tx=session.beginTransaction();
           List<Student> list=session.createQuery("FROM Student",Student.class).getResultList();
       //HQL    QUERY YAZDIK
       tx.commit();
       session.close();
       return list;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
       Student student=session.get(Student.class,id);
        Optional<Student> opt;
        opt=Optional.ofNullable(student);
        // eğer id karşılığı Student yok ise bize null göndermiyor içi boş bir student gönderir
        tx.commit();
        session.close();
        return opt;
    }

    @Override
    public void save(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        session.saveOrUpdate(student);
        //dbde böyle bir obje varsa update et yoksa üret
        tx.commit();
        session.close();

    }

    @Override
    public void update(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        session.update(student);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        Student student=session.load(Student.class,id);//boş obje getiriyor LOAD
        session.delete(student);
        tx.commit();
        session.close();

    }
}
