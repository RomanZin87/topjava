package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.ImMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private MealRepository mealRepository;

    @Override
    public void init() {
        mealRepository = new ImMemoryMealRepo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        String action = request.getParameter("action");
        Meal meal;

        switch (action == null ? "showAll" : action.toLowerCase(Locale.ROOT)) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                log.debug("Delete meal with id={} ", id);
                mealRepository.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
                meal = new Meal(LocalDateTime.now(), "description", 0);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("mealForm.jsp").forward(request, response);
                log.debug("forward to mealForm");
                break;
            case "update":
                meal = mealRepository.get(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("mealForm.jsp").forward(request, response);
                log.debug("forward to mealForm");
                break;
            case "showAll":
                log.debug("getAll meals");
                request.setAttribute("mealTos", makeTos());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        meal.setId(id.isEmpty() ? null : Integer.valueOf(id));
        if (meal.hasNoId()) {
            mealRepository.create(meal);
            log.debug("create new meal");
        } else {
            mealRepository.update(meal);
            log.debug("updater meal with id={}",id);
        }
        response.sendRedirect("meals");
    }

    private List<MealTo> makeTos() {
        return MealsUtil.filteredByStreams(mealRepository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
    }
}
