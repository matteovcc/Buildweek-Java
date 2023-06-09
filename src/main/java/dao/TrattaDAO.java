package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Tratta;

public class TrattaDAO {
	private final EntityManager em;

	public TrattaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tratta e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Tratta getById(UUID uuid) {
		Tratta found = em.find(Tratta.class, uuid);

		if (found != null) {
			System.out.println("Tratta" + " " + uuid + " " + "trovata");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public void delete(Tratta tratta) {
		em.getTransaction().begin();
		tratta = em.merge(tratta);
		em.remove(tratta);
		em.getTransaction().commit();
	}

	public void refresh(Tratta tratta) {
		tratta = em.merge(tratta);
		em.refresh(tratta);
	}

//	public List<Tratta> getNumberTrattaPercorsa(String trattaId) {
//		TypedQuery<Tratta> q = em
//				.createQuery("SELECT m.id,COUNT(t) FROM Mezzo m JOIN m.tratta t WHERE t.id = :trattaId", Tratta.class);
//		q.setParameter("trattaId", trattaId);
//		return q.getResultList();
//	}
//	public Tratta getTimesTrattaPercorsa(String id) {
//		TypedQuery<Tratta> q = em.createQuery("SELECT COUNT(m) FROM Mezzo m WHERE m.id = :id", Tratta.class);
//		q.setParameter("trattaId", UUID.fromString(id));
//		return q.getSingleResult();
//	}

	public long getTimesTrattaPercorsa(String id) {
		Query q = em.createQuery("SELECT COUNT(m) FROM Mezzo m WHERE m.tratta.id = :id");
		q.setParameter("id", UUID.fromString(id));
		return (Long) q.getSingleResult();
	}

	public long getTimesTrattaPercorsaBySingleMezzo(String trattaId, String mezzoId) {
		Query q = em.createQuery("SELECT COUNT(m) FROM Mezzo m WHERE m.tratta.id = :trattaId AND m.id = :mezzoId");
		q.setParameter("trattaId", UUID.fromString(trattaId));
		q.setParameter("mezzoId", UUID.fromString(mezzoId));
		return (Long) q.getSingleResult();
	}

	public void saveTratta(Tratta tratta) {
		em.merge(tratta);

	}

}
