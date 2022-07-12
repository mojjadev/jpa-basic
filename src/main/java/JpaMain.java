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
            Team team = new Team();

            team.setTeamName("우리 민족");

            em.persist(team);
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getTeamId());

            MemberTest mt = new MemberTest();
            //mt.setId(1L);
            mt.setTeam(findTeam);
            mt.setName("나야나");

            MemberTest mt2 = new MemberTest();
            //mt.setId(1L);
            mt2.setName("나야나1");
            mt2.setTeam(findTeam);

            MemberTest mt3 = new MemberTest();
            //mt.setId(1L);
            mt3.setName("나야나2");
            mt3.setTeam(findTeam);

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
