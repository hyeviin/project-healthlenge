package khv.healthlenge.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.faq.FaqListDTO;
import khv.healthlenge.domain.entity.faq.Div;
import khv.healthlenge.domain.entity.faq.FaqEntityRepository;
import khv.healthlenge.service.CustomerService;

@Service
public class CustomerServiceProcess implements CustomerService{
	
	@Autowired
	private FaqEntityRepository faqEntityRepository;

	@Override
	public void list(Model model, String divName) {
		Div divs[]= Div.values();
		Div div= null;
		for(Div d : divs) {
			if(divName.equals(d.getUrl())){
				div= d;
			}
		}
		
		System.out.println(div);
//		faqEntityRepository.findAllByDiv(div).forEach(System.out::println);
//		List<FaqListDTO> result= faqEntityRepository.findAllByDiv(div).stream()
//				.map(FaqListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("faqlist", faqEntityRepository.findAllByDiv(div).stream()
				.map(FaqListDTO::new).collect(Collectors.toList()));
	}

	@Override
	public ModelAndView list(ModelAndView mv, int divNo) {
		Div divs[]= Div.values();
		Div div= divs[divNo];

		System.out.println(div);
//		faqEntityRepository.findAllByDiv(div).forEach(System.out::println);
//		List<FaqListDTO> result= faqEntityRepository.findAllByDiv(div).stream()
//				.map(FaqListDTO::new).collect(Collectors.toList());
		
		mv.addObject("faqlist", faqEntityRepository.findAllByDiv(div).stream()
				.map(FaqListDTO::new).collect(Collectors.toList()));
		mv.setViewName("/my/customer/list");
		
		return mv;
	}

	@Override
	public void list(Model model, int divNo) {
		Div divs[]= Div.values();
		Div div= divs[divNo];

		System.out.println(div);
//		faqEntityRepository.findAllByDiv(div).forEach(System.out::println);
//		List<FaqListDTO> result= faqEntityRepository.findAllByDiv(div).stream()
//				.map(FaqListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("faqlist", faqEntityRepository.findAllByDiv(div).stream()
				.map(FaqListDTO::new).collect(Collectors.toList()));
		
	}
}
