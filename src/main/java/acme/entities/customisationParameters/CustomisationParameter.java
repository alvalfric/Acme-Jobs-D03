
package acme.entities.customisationParameters;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomisationParameter extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	private String				spamWordsEn;

	private String				spamWordsEs;

	private Double				spamThreshold;

}
