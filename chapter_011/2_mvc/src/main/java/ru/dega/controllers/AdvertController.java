package ru.dega.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dega.dao.AdvertDAO;
import ru.dega.dao.UserDAO;
import ru.dega.models.Advert;
import ru.dega.models.CarBrand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdvertController class.
 *
 * @author Denis
 * @since 26.12.2017
 */
@Controller
public class AdvertController {
    /**
     * User DAO.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * Advert DAO.
     */
    @Autowired
    private AdvertDAO advertDAO;

    /**
     * Get request.
     *
     * @param model model map
     * @return users jsp
     */
    @RequestMapping(value = "/adverts", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        List<Advert> list = advertDAO.list();
        model.addAttribute("adverts", list);
        Map<Integer, String> carBrands = new HashMap<>();
        for (CarBrand carBrand : userDAO.list()) {
            carBrands.put(carBrand.getId(), carBrand.getName());
        }
        model.addAttribute("carBrands", carBrands);
        return "adverts";
    }

    /**
     * Post request.
     *
     * @param carBrandsId the car brands id
     * @param advert      the advert
     * @return redirect to users.do
     */
    @RequestMapping(value = "/adverts", method = RequestMethod.POST)
    public String addItem(@RequestParam String carBrandsId, @ModelAttribute Advert advert) {
        advertDAO.insert(advert.getDescription(), Integer.parseInt(carBrandsId));
        return "redirect:adverts.do";
    }
}