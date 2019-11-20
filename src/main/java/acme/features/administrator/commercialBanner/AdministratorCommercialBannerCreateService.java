
package acme.features.administrator.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCommercialBannerCreateService implements AbstractCreateService<Administrator, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL", "creditCard", "picture");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;

		result = new CommercialBanner();
		result.setCreditCard("0000000000000000");
		result.setPicture("http://localhost:8080/acme-jobs/images/banner.png");
		result.setSlogan("slogan");
		result.setTargetURL("http://localhost:8080/acme-jobs");

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String regexpurl = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
		String regexpcc = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";

		if (!errors.hasErrors("slogan")) {
			errors.state(request, !entity.getSlogan().isEmpty(), "slogan", "javax.validation.constraints.NotBlank.message");
		}
		if (!errors.hasErrors("targetURL")) {
			errors.state(request, !entity.getTargetURL().matches(regexpurl), "targetURL", "javax.validation.constraints.Pattern.message", regexpurl);
		}
		if (!errors.hasErrors("picture")) {
			errors.state(request, !entity.getTargetURL().matches(regexpurl), "picture", "javax.validation.constraints.Pattern.message", regexpurl);
		}
		if (!errors.hasErrors("creditCard")) {
			errors.state(request, !entity.getTargetURL().matches(regexpcc), "creditCard", "javax.validation.constraints.Pattern.message", regexpcc);
		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		this.repository.save(entity);
	}

}
