package DZHibernate;

import org.hibernate.Session;

public class CoursesController {
    private final ConnectorMysql connectorMysql;
    private Course course;



    public CoursesController() {
        this.connectorMysql = new ConnectorMysql();
    }

    public void createData(Course course){
        try (Session session = connectorMysql.getSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            session.close();
        }
    }
    public Course getData(int id){
        try (Session session = connectorMysql.getSession()) {
            session.beginTransaction();
            Course course1 = session.get(Course.class, id);
            session.getTransaction().commit();
            return course1;
        }

    }
    public void updateData(Course course){
        try (Session session = connectorMysql.getSession()) {
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        }

    }

    public void deleteData(Course course){
        try (Session session = connectorMysql.getSession()) {
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
        }

    }

    public ConnectorMysql getConnectorMysql() {
        return connectorMysql;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
