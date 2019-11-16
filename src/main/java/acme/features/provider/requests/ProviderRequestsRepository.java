
package acme.features.provider.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requests;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestsRepository extends AbstractRepository {

	@Query("select r from Requests r where r.id = ?1 and TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Requests findOneById(int id);

	@Query("select r from Requests r where r.ticker = ?1 and TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Requests findOneByTicker(String ticker);

	@Query("select r from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Collection<Requests> findManyAll();

}
