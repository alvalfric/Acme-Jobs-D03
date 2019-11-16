
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Challenge extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				goal;

	@NotBlank
	private String				reward;


	@Transient
	public String getRewardGoal() {
		StringBuilder result;
		result = new StringBuilder();
		result.append(this.reward);
		result.append(" / ");
		result.append(this.goal);

		return result.toString();

	}

}
