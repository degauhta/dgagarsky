package ru.dega.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dega.models.Advert;
import ru.dega.models.CarBrand;
import ru.dega.models.CarModel;
import ru.dega.repositories.AdvertRepository;
import ru.dega.repositories.CarBrandRepository;
import ru.dega.repositories.CarModelRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdvertController class.
 *
 * @author Denis
 * @since 28.12.2017
 */
@Controller
public class AdvertController {
    /**
     * Advert repository.
     */
    private final AdvertRepository advertRepository;

    /**
     * Car brand repository.
     */
    private final CarBrandRepository carBrandRepository;

    /**
     * Car model repository.
     */
    private final CarModelRepository carModelRepository;

    /**
     * Default constructor.
     *
     * @param advertRepository   advertRepository
     * @param carBrandRepository carBrandRepository
     * @param carModelRepository carModelRepository
     */
    @Autowired
    public AdvertController(AdvertRepository advertRepository, CarBrandRepository carBrandRepository, CarModelRepository carModelRepository) {
        this.advertRepository = advertRepository;
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
    }

    /**
     * Get request.
     *
     * @param model model map
     * @return users jsp
     */
    @RequestMapping(value = "/adverts", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        List<Advert> list = (List<Advert>) advertRepository.findAll();
        model.addAttribute("adverts", list);
        Map<Integer, String> carBrands = new HashMap<>();
        for (CarBrand carBrand : carBrandRepository.findAll()) {
            carBrands.put(carBrand.getId(), carBrand.getName());
        }
        model.addAttribute("carBrands", carBrands);
        Map<Integer, String> carModels = new HashMap<>();
        for (CarModel carModel : carModelRepository.findAll()) {
            carModels.put(carModel.getId(), carModel.getName());
        }
        model.addAttribute("carModels", carModels);
        return "adverts";
    }

    /**
     * Post request.
     *
     * @param carBrandId the car brands id
     * @param advert     the advert
     * @param carModelId the car model id
     * @return redirect to users.do
     */
    @RequestMapping(value = "/adverts", method = RequestMethod.POST)
    public String addItem(@RequestParam int carBrandId, @RequestParam int carModelId, @ModelAttribute Advert advert) {
        advert.setCarBrand(carBrandRepository.findOne(carBrandId));
        advert.setCarModel(carModelRepository.findOne(carModelId));
        advertRepository.save(advert);
        return "redirect:adverts.do";
    }
}