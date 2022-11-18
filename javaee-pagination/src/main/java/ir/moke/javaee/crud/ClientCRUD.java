package ir.moke.javaee.crud;

import ir.moke.javaee.api.Pagination;
import ir.moke.javaee.entity.Client;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

@Singleton
public class ClientCRUD {
    @PersistenceContext(unitName = "db-connection")
    private EntityManager em;

    public void save(Client client) {
        em.persist(client);
    }

    @SuppressWarnings("unchecked")
    public List<Client> find(Long id, String name, String family, Pagination pagination) {
        StringBuilder sb = new StringBuilder("select c from Client c where 1=1 ");
        Optional.ofNullable(id).ifPresent(item -> sb.append("and c.name=:id "));
        Optional.ofNullable(name).ifPresent(item -> sb.append("and c.name=:name "));
        Optional.ofNullable(family).ifPresent(item -> sb.append("and c.name=:family "));
        sb.append("order by ").append(pagination.getOrder()).append(" ").append(pagination.getOrderType().getValue());

        Query query = em.createQuery(sb.toString());
        query.setFirstResult(pagination.getOffset());
        query.setMaxResults(pagination.getSize());

        Optional.ofNullable(id).ifPresent(item -> query.setParameter("id", id));
        Optional.ofNullable(name).ifPresent(item -> query.setParameter("name", name));
        Optional.ofNullable(family).ifPresent(item -> query.setParameter("family", family));
        return query.getResultList();
    }
}
