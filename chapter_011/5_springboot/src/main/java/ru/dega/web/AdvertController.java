package ru.dega.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dega.domain.Advert;
import ru.dega.service.AdvertService;
import ru.dega.service.CarBrandService;
import ru.dega.service.CarModelService;

/**
 * AdvertController class.
 *
 * @author Denis
 * @since 04.01.2018
 */
@Controller
public class AdvertController {
    /**
     * Advert service.
     */
    private final AdvertService advertService;

    /**
     * Car brand service.
     */
    private final CarBrandService carBrandService;

    /**
     * Car model service.
     */
    private final CarModelService carModelService;

    /**
     * Instantiates a new Advert controller.
     *
     * @param advertService   the advert service
     * @param carBrandService the car brand service
     * @param carModelService the car model service
     */
    @Autowired
    public AdvertController(AdvertService advertService, CarBrandService carBrandService, CarModelService carModelService) {
        this.advertService = advertService;
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
    }

    /**
     * Get request.
     *
     * @return the model and view
     */
    @GetMapping("/adverts")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("adverts");
        model.addObject("adverts", advertService.getAll());

//        Map<Integer, String> carBrands = new HashMap<>();
//        for (CarBrand carBrand : carBrandService.getAll()) {
//            carBrands.put(carBrand.getId(), carBrand.getName());
//        }
        model.addObject("carBrands", carBrandService.getAll());
        model.addObject("carModels", carModelService.getAll());
//        model.addObject("mapCountries", carBrands);


        return model;
    }

    /**
     * Post request.
     *
     * @param advert the advert
     * @return the string
     */
    @PostMapping("/adverts")
    public String add(@ModelAttribute Advert advert) {
        advertService.add(advert);
        return "redirect:adverts";
    }
}