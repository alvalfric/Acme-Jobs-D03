
package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorAnnouncementRepository repository;


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result = new Announcement();

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "moreInfo");

	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String regexpUrl = "^(https?:\\\\/\\\\/)?(www\\\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\\\.)+[\\\\w]{2,}(\\\\/\\\\S*)?$";
		boolean moreInfoOk = entity.getMoreInfo().matches(regexpUrl);
		errors.state(request, moreInfoOk, "website", "administrator.announcement.error.web", regexpUrl);

		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "administrator.announcement.error.NotBlank");
		}
		if (!errors.hasErrors("moreInfo")) {
			errors.state(request, !entity.getMoreInfo().isEmpty(), "moreInfo", "administrator.announcement.error.NotBlank");
		}
		if (!errors.hasErrors("text")) {
			errors.state(request, !entity.getText().isEmpty(), "text", "administrator.announcement.error.NotBlank");
		}

	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}
}
