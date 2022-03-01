package ca.sheridancollege.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.beans.Appointment;
import ca.sheridancollege.repositories.AppointmentRepository;

@Controller
public class HomeController {
	@Autowired
	private AppointmentRepository apptRep;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("formAction", "/");
		model.addAttribute("action", "Add");
		model.addAttribute("appt", new Appointment());
		model.addAttribute("apptList", apptRep.findAll());
		return "index";
	}

	@PostMapping("/")
	public String indexPost(Model model, @ModelAttribute("appt") Appointment appt) {
		apptRep.save(appt);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String deleteAppt(@PathVariable("id") String apptId, Model model) {
		Optional<Appointment> appt = apptRep.findById(apptId);
		model.addAttribute("formAction", "/edit/"+apptId);
		model.addAttribute("appt", appt);
		model.addAttribute("apptList", apptRep.findAll());
		model.addAttribute("action", "Edit");
		model.addAttribute("editId", apptId);
		return "index";
	}
	
	@PostMapping("/edit/{id}")
	public String indexPost(@PathVariable("id") String apptId, @ModelAttribute("appt") Appointment appt) {
		apptRep.save(appt);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAppt(Model model, @PathVariable("id") String apptId) {
		apptRep.deleteById(apptId);
		return "redirect:/";
	}
}
