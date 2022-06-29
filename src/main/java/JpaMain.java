import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MemberTest mt = new MemberTest();
            //mt.setId(1L);
            mt.setName("나야나");

            MemberTest mt2 = new MemberTest();
            //mt.setId(1L);
            mt2.setName("나야나1");

            MemberTest mt3 = new MemberTest();
            //mt.setId(1L);
            mt3.setName("나야나2");

            em.persist(mt);
            em.persist(mt2);
            em.persist(mt3);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
            emf.close();
        }
    }
}
