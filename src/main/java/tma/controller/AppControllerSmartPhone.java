package tma.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tma.model.SmartPhone;
import tma.service.SmartPhoneService;

@Controller
public class AppControllerSmartPhone {

	@Autowired
	SmartPhoneService smartPhoneService;
	
	@RequestMapping(value = { "/smartphone" }, method = RequestMethod.GET)
	public @ResponseBody List<SmartPhone> listAllSmartPhone(ModelMap model) {
		return smartPhoneService.getAllSmartPhone();
	}
	
	@RequestMapping(value = { "/smartphone/{idSmartPhone}" }, method = RequestMethod.GET)
    @ResponseBody
	public SmartPhone listSmartPhoneById(@PathVariable String idSmartPhone) {
		 return smartPhoneService.getSmartPhoneById(idSmartPhone);
	}
	
	@RequestMapping(value = { "/smartphone" }, method = RequestMethod.POST)
	@ResponseBody
	public String addSmartPhone(@RequestBody SmartPhone jsonString) {
		smartPhoneService.sendSmartPhone(jsonString);
 		return "add smartphone done!";
	}
	
	@RequestMapping(value = { "/smartphone/{idSmartPhone}" }, method = RequestMethod.PUT)
	@ResponseBody
	public String updateOrderById(@RequestBody SmartPhone jsonString, @PathVariable String idSmartPhone) {	
		
		SmartPhone currentSmartPhone = smartPhoneService.getSmartPhoneById(idSmartPhone);
		currentSmartPhone.setnameSmartPhone(jsonString.getnameSmartPhone());
		currentSmartPhone.setpriceSmartPhone(jsonString.getpriceSmartPhone());
		
		smartPhoneService.updateSmartPhone(currentSmartPhone);
 		return "update smartphone done!";
	}
	
	@RequestMapping(value = { "/smartphone/{idSmartPhone}" }, method = RequestMethod.DELETE)
    @ResponseBody
	public String deleteSmartPhoneById(@PathVariable String idSmartPhone) {
		
		smartPhoneService.deleteById(idSmartPhone);
		return "delete smartphone by id";
	}
	
	@RequestMapping(value = { "/smartphone" }, method = RequestMethod.DELETE)
    @ResponseBody
	public String deleteAllSmartPhone() {
		
		smartPhoneService.deleteAllSmartPhones();
		return "delete all smartphone";
	}
}
