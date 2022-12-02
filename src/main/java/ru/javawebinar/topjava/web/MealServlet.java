package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private MealDAO mealDAO;
    private static String INSERT_OR_EDIT = "mealForm.jsp";
    private static String LIST_USER = "meals.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        mealDAO = new MealDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        action = action == null ? "all" : action;
        String forward = "";

        switch (action) {
            case "delete": {
                int id = Integer.parseInt(req.getParameter("id"));
                log.debug("delete");
                mealDAO.delete(id);
                forward = LIST_USER;
                log.debug("forward to meals");
                req.setAttribute("mealTos", MealsUtil.getMealTos(mealDAO.getAll()));
                break;
            }
            case "update":
            case "create": {
                forward = INSERT_OR_EDIT;
                String id = req.getParameter("id");
                int mealId = 0;
                if(id!=null) {
                    mealId = Integer.parseInt(id);
                }
                Meal meal = action.equals("create") ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealDAO.getById(mealId);
                MealTo mealTo = MealsUtil.createTo(mealId, meal, false);
                req.setAttribute("mealTo", mealTo);
                log.debug("forward to mealForm");
                break;
            }
            case "all":
                forward = LIST_USER;
                log.debug("forward to meals");
                req.setAttribute("mealTos", MealsUtil.getMealTos(mealDAO.getAll()));
                break;
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
        meal.setDescription(req.getParameter("description"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));

        String id = req.getParameter("id");
        if(id.equals("0")) {
            log.debug("save meal");
            mealDAO.save(meal);
        }
        else {
            log.debug("update meal");
            mealDAO.update(Integer.parseInt(id), meal);
        }

        req.setAttribute("mealTos", MealsUtil.getMealTos(mealDAO.getAll()));
        log.debug("forward to meals");
        req.getRequestDispatcher(LIST_USER).forward(req, resp);
    }
}
