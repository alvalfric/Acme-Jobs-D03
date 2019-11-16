
package acme.features.administrator.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id=?1")
	Challenge findOneChallengeById(int id);

	@Query("select a from Challenge a  where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Collection<Challenge> findManyAll();

}
