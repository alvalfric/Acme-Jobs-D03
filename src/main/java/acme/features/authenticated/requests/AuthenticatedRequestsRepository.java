
package acme.features.authenticated.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requests;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestsRepository extends AbstractRepository {

	@Query("select r from Requests r where r.id = ?1 and TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Requests findOneById(int id);

	@Query("select r from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Collection<Requests> findManyAll();

}
