
package acme.features.provider.requests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requests;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestsCreateService implements AbstractCreateService<Provider, Requests> {

	// Internal state ----------------------------------------------------------

	@Autowired
	ProviderRequestsRepository repository;


	// AbstractCreateService<Provider, Requests> interface ---------------------

	@Override
	public boolean authorise(final Request<Requests> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Requests> request, final Requests entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Requests> request, final Requests entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
	}

	@Override
	public Requests instantiate(final Request<Requests> request) {
		// TODO Auto-generated method stub
		Requests result;

		result = new Requests();

		return result;
	}

	@Override
	public void validate(final Request<Requests> request, final Requests entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		Requests existing;
		Calendar calendar;
		Date minimumDeadline;
		boolean isConfirmed;

		if (!errors.hasErrors("ticker")) {
			errors.state(request, !entity.getTicker().equals(null), "ticker", "provider.requests.error.ticker-null");
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "provider.requests.error.duplicated-ticker");
		}

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "provider.requests.error.must-confirm");

		calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		minimumDeadline = calendar.getTime();
		errors.state(request, entity.getDeadline() != null && entity.getDeadline().after(minimumDeadline), "deadline", "provider.requests.error.wrong-deadline");
		//		errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "provider.requests.error.minimum-deadline");

		if (!errors.hasErrors("reward")) {
			errors.state(request, !entity.getReward().equals(null), "reward", "provider.requests.error.reward-null");
			errors.state(request, entity.getReward().getCurrency().equals("€") || entity.getReward().getCurrency().equals("EUR"), "reward", "provider.requests.error.reward-not-euro");
		}

		errors.state(request, !entity.getTitle().equals(null), "title", "provider.requests.error.title-null");
		errors.state(request, !entity.getText().equals(null), "text", "provider.requests.error.text-null");

	}

	@Override
	public void create(final Request<Requests> request, final Requests entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);

	}

}
